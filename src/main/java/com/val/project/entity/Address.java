package com.val.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @NotBlank
  @Column(length = 80, nullable = false)
  private String city;
  @NotBlank
  @Column(length = 80, nullable = false)
  private String uf;
  @NotBlank
  @Column(length = 80, nullable = false)
  private String street;
  @Column(length = 10, name = "zip_code", nullable = false)
  private String zipCode; // CEP
  @NotBlank
  @Column(nullable = false)
  private Integer number;
  @Column(name = "is_default", nullable = false)
  private boolean isDefault;
}
