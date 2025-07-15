package com.val.project.service;

import com.val.project.entity.Address;
import com.val.project.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
  private AddressRepository addressRepository;
  @Autowired
  private UserService userService;

  public Address save(Address a) {
    userService.findById(a.getUser().getId());
    return addressRepository.save(a);
  }

  public Address findById(Long id) {
    return addressRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Address with id: %s not exists".formatted(id)));
  }
}
