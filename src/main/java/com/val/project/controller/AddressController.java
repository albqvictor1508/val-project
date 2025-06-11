package com.val.project.controller;

import com.val.project.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adresses")
public class AddressController {
    @Autowired
    private AddressService addressService;
}
