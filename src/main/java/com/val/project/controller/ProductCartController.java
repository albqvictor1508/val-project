package com.val.project.controller;

import com.val.project.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class ProductCartController {
    @Autowired
    private ProductCartService productCartService;

}
