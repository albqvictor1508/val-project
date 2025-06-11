package com.val.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product_cart")
public class ProductCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;
    @OneToMany(mappedBy = "cart")
    private List<Product> products;
}
