package com.val.project.controller;

import com.val.project.entity.User;
import com.val.project.service.UserService;
import com.val.project.types.UserRole;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping
  public User save(@Valid @RequestBody User u) {
    try {
      UserRole role = UserRole.valueOf(u.getRole());
      u.setRole(role.getLabel());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid role", e);
    }
    return userService.save(u);
  }

  @DeleteMapping("/:userId")
  public void delete(@PathVariable Long userId) {
    userService.delete(userId);
  }
}
