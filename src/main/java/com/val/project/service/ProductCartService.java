package com.val.project.service;

import java.util.List;
import com.val.project.entity.Product;
import com.val.project.entity.ProductCart;
import com.val.project.repository.ProductCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCartService {
  private ProductCartRepository productCartRepository;

  @Autowired
  private ProductService productService;

  public ProductCart save(ProductCart productCart) {
    return productCartRepository.save(productCart);
  }

  public ProductCart findById(Long cartId) {
    return productCartRepository.findById(cartId)
        .orElseThrow(() -> new RuntimeException("product cart with id %s not exists".formatted(cartId)));
  }

  // TODO: trocar esse Product por um DTO
  public void addProduct(Product p) {
    Product product = productService.findById(p.getId());

    final Long cartId = p.getCart().getId();
    ProductCart cart = findById(cartId);

    cart.getProducts().add(product);
    productCartRepository.save(cart);
  }

  // TODO: trocar esse Product por um DTO
  public void deleteProduct(Product p) {
    Product product = productService.findById(p.getId());

    if (product == null)
      throw new RuntimeException("product not exists");

    final Long cartId = p.getCart().getId();
    var cart = findById(cartId);

    if (cart == null)
      throw new RuntimeException("cart not exists");

    cart.getProducts().remove(product);
    productCartRepository.save(cart);
  }

  public List<Product> getProducts(Long cartId) {
    var cart = findById(cartId);
    return cart.getProducts();
  }

  private void calculateTotal(Long cartId) {
    var cart = findById(cartId);
    Double total = cart.getProducts().stream().mapToDouble(Product::getPrice).sum();
    System.out.println(total);
  }
}
