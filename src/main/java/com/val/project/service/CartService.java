package com.val.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.val.project.dto.cart.AddItemRequest;
import com.val.project.entity.Cart;
import com.val.project.entity.CartItem;
import com.val.project.entity.Product;
import com.val.project.repository.CartItemRepository;
import com.val.project.entity.User;
import com.val.project.repository.CartRepository;
import com.val.project.repository.UserRepository;
import com.val.project.types.CartStatusEnum;

@Service
public class CartService {
  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private CartItemRepository cartItemRepository;
  @Autowired
  private ProductService productService;
  @Autowired
  private UserRepository userRepository;

  public Cart findByUserId(Long userId) {
    return cartRepository.findByUserId(userId)
        .orElseThrow(() -> new RuntimeException("The cart with userId: %s not exists".formatted(userId)));
  }

  public Cart findOrCreateCartForUser(Long userId, String sessionId) {
    Optional<Cart> existingCart = cartRepository.findByUserId(userId);
    if (existingCart.isPresent()) {
      return existingCart.get();
    }
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User with id %s not found".formatted(userId)));
    Cart newCart = new Cart();
    newCart.setUser(user);
    newCart.setSessionId(sessionId);
    newCart.setStatus(CartStatusEnum.OPEN);
    newCart.setCreatedAt(LocalDateTime.now());
    newCart.setUpdatedAt(LocalDateTime.now());
    newCart.setTotal(0.0);

    return cartRepository.save(newCart);
  }

  public Cart save(Cart cart) {
    return cartRepository.save(cart);
  }

  public Cart findById(Long cartId) {
    return cartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("Cart with id %s not exists".formatted(cartId)));
  }

  public List<CartItem> getItemsById(Long cartId) {
    Cart cart = findById(cartId);
    return cart.getItems();
  }

  // TODO: REVISAR ISSO AQUI
  @Transactional
  public CartItem addItem(Long cartId, AddItemRequest request) {
    Cart cart = findById(cartId);
    Product product = productService.findById(request.getProductId());

    Optional<CartItem> existingItemOptional = cart.getItems().stream()
        .filter(item -> item.getProduct().getId().equals(request.getProductId()))
        .findFirst();

    // WARN: RETIRAR ESSE ELSE E USAR EARLY RETURN
    CartItem cartItem;
    if (existingItemOptional.isPresent()) {
      cartItem = existingItemOptional.get();
      cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
      cartItem.setUpdatedAt(LocalDateTime.now());
    } else {
      cartItem = new CartItem();
      cartItem.setCart(cart);
      cartItem.setProduct(product);
      cartItem.setQuantity(request.getQuantity());
      cartItem.setUnitPrice(product.getPrice());
      cartItem.setCreatedAt(LocalDateTime.now());
      cartItem.setUpdatedAt(LocalDateTime.now());
      cart.getItems().add(cartItem);
    }

    cartItemRepository.save(cartItem);

    // Recalculate total and update cart
    double total = cart.getItems().stream()
        .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
        .sum();
    cart.setTotal(total);
    cart.setUpdatedAt(LocalDateTime.now());
    cartRepository.save(cart);

    return cartItem;
  }

  public void deleteCart(Long cartId) {
    cartRepository.deleteById(cartId);
  }
}
