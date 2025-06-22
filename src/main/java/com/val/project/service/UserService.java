package com.val.project.service;

import com.val.project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    private User save(User u) {
      return userRepository.save(u);
    }
}
