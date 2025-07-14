package com.val.project.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.val.project.types.UnitEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
  @Column(nullable = true)
  private String description;
  @Column(nullable = false)
  private Double price;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  @Enumerated(EnumType.STRING)
  private UnitEnum unit;
  private Double weight;
  @Column(name = "available_quantity")
  private Integer availableQuantity;
  @ManyToOne
  @JoinColumn(name = "producer_id")
  private Producer producer;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<ProductImage> images;

  @CreatedDate
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  // adicionar uma lista de fotos do produto, ou pode ser uma tabela de foto, com
  public Product(String name, String description, Double price, Category c) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.category = c;
  }
}
