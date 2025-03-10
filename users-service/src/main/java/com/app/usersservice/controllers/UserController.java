package com.app.usersservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.usersservice.models.entities.usuarios.Usuario;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping("/{userId}")
  public ResponseEntity<Usuario> getUsers(@PathVariable Long userId) {
    return ResponseEntity.ok(new Usuario(userId, "John Doe", "123456", null));
  }
}
