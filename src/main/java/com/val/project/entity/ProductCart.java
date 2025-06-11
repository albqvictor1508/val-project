package com.val.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product_cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;
    @OneToMany(mappedBy = "cart")
    private List<Product> products;
}
