package com.val.project.service;

import com.val.project.entity.User;
import com.val.project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private UserRepository userRepository;

  private User save(User u) {
    return userRepository.save(u);
  }

  private User findById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("user with id %s not exists".formatted(userId)));
  }
}
