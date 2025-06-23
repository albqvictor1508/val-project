package com.val.project.entity;

import org.hibernate.engine.jdbc.cursor.internal.FallbackRefCursorSupport;

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
  @Column(nullable = false)
  private String category;
  // adicionar uma lista de fotos do produto
}
