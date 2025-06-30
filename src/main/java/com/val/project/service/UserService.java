package com.val.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.val.project.entity.User;
import com.val.project.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public User save(User u) {
    Boolean existsByEmail = userRepository.existsByEmail(u.getEmail());
    Boolean existsByUsername = userRepository.existsByUsername(u.getUsername());
    if (existsByEmail)
      throw new RuntimeException("user with email: %s already exists".formatted(u.getEmail()));
    if (existsByUsername)
      throw new RuntimeException("user with username: %s already exists".formatted(u.getUsername()));

    try {
      String encodedPassword = passwordEncoder.encode(u.getPassword());
      u.setPassword(encodedPassword);
    } catch (Exception e) {
      throw new RuntimeException("Failed to encrypt password");
    }

    return userRepository.save(u);
  }

  public User findById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("user with id %s not exists".formatted(userId)));
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("user with id %s not exists".formatted(email)));
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("user with id %s not exists".formatted(username)));
  }

  public Boolean existByUsername(String name) {
    return userRepository.existsByUsername(name);
  }

  public Boolean existByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  public void delete(Long userId) {
    User u = findById(userId);
    userRepository.delete(u);
  }
}
