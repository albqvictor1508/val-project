package com.val.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 120, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ProductCart cart;
    @Column(nullable = true)
    private String description;
    //adicionar uma lista de fotos do produto
}
