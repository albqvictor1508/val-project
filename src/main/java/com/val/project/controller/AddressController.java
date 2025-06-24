package com.val.project.controller;

import com.val.project.entity.Address;
import com.val.project.service.AddressService;
import com.val.project.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adresses")
public class AddressController {
  @Autowired
  private AddressService addressService;
  @Autowired
  private UserService userService;

  public ResponseEntity<Address> save(Adress a) {
    User u = userService;
  }
}
