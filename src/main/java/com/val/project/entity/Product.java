package com.val.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
  @Column(nullable = false)
  private Double price;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  // adicionar uma lista de fotos do produto, ou pode ser uma tabela de foto, com
}
