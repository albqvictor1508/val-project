package com.val.project.service;

import com.val.project.entity.User;
import com.val.project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private UserRepository userRepository;

  private User save(User u) {
    userRepository.findByEmail(u.getEmail());
    userRepository.findByUsername(u.getEmail());
    return userRepository.save(u);
  }

  private User findById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("user with id %s not exists".formatted(userId)));
  }

  private User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("user with id %s not exists".formatted(email)));
  }

  private User findByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("user with id %s not exists".formatted(username)));
  }

  private void delete(Long userId) {
    User u = findById(userId);
    userRepository.delete(u);
  }
}
