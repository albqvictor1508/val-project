package com.val.project.controller;

import com.val.project.entity.Address;
import com.val.project.service.AddressService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adresses")
public class AddressController {
  @Autowired
  private AddressService addressService;

  @PostMapping
  public ResponseEntity<Address> save(@Valid @RequestBody Address a) {
    Address address = addressService.save(a);
    return ResponseEntity.ok(address);
  }
}
