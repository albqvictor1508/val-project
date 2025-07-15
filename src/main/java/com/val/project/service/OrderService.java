package com.val.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.val.project.dto.order.OrderResponse;
import com.val.project.entity.Address;
import com.val.project.entity.Cart;
import com.val.project.entity.Order;
import com.val.project.entity.OrderItem;
import com.val.project.exception.AddressIdNotFoundException;
import com.val.project.repository.OrderRepository;
import com.val.project.types.CartStatusEnum;
import com.val.project.types.OrderStatus;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private CartService cartService;

  @Autowired
  private AddressService addressService;

  public Order save(Order o) {
    return orderRepository.save(o);
  }

  @Transactional
  public OrderResponse checkout(Long cartId, Long shippingAddressId) {
    Cart cart = cartService.findById(cartId);

    // WARN: better error handling here!
    if (cart.getItems().isEmpty())
      throw new RuntimeException("You cant do it with a empty cart");

    Address shippingAddress = addressService.findById(shippingAddressId);
    List<Address> userAddresses = cart.getUser().getAddresses();

    Optional<Address> addressOpt = userAddresses.stream()
        .filter(address -> address.getId().equals(shippingAddress.getId()))
        .findFirst();

    if (!addressOpt.isPresent())
      throw new AddressIdNotFoundException("The address with id: %s not exists".formatted(shippingAddressId));

    Address address = addressOpt.get();
    Order order = new Order();
    order.setUser(cart.getUser());
    order.setShippingAddress(address);
    order.setStatus(OrderStatus.PENDING);
    order.setTotalPrice(cart.getTotal());

    List<OrderItem> orderItems = cart.getItems().stream()
        .map(cartItem -> {
          OrderItem orderItem = new OrderItem();
          orderItem.setOrder(order);
          orderItem.setProduct(cartItem.getProduct());
          orderItem.setQuantity(cartItem.getQuantity());
          orderItem.setUnitPrice(cartItem.getUnitPrice());
          return orderItem;
        })
        .collect(Collectors.toList());

    order.setOrderItems(orderItems);
    orderRepository.save(order);

    cart.setStatus(CartStatusEnum.CLOSED);
    cartService.save(cart);

    return new OrderResponse(order);
  }

  public void delete(Long orderId) {
    orderRepository.deleteById(orderId);
  }
}
