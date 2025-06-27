package com.val.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.val.project.types.UserRole;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  @Email
  @Column(unique = true, length = 120, nullable = false)
  private String email;
  @NotBlank
  @Column(length = 80, nullable = false, unique = true)
  private String username;
  @NotBlank
  @Column(length = 100, nullable = false)
  private String password;
  @CPF
  @NotBlank
  @Column(length = 11, nullable = false, unique = true)
  private String cpf;
  @OneToMany(mappedBy = "user")
  private List<Address> addresses;
  @NotBlank
  @Column(nullable = false)
  private UserRole role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }
}
