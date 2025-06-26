package com.val.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "session_id", unique = true)
  private String sessionId;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @Column
  private Double total;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "created_at")
  private LocalDateTime updatedAt;
  // WARN: BOM MUDAR ISSO PRA UM ENUM
  @Column
  private String status;

}
