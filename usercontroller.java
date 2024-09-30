package com.example.Applicationjava.controller;

import com.example.Applicationjava.Entity.Users;

import com.example.Applicationjava.dto.ResetPassword;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class usercontroller {

    @Autowired
    private com.example.Applicationjava.service.user_service user_service;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody Users user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Users createdUser = user_service.register(user);
        return ResponseEntity.ok("Data Inserted Successfully :)");
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = user_service.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Users user) {
        Optional<Users> existingUser = user_service.login(user.getEmail(), user.getPassword());

        if (existingUser.isPresent()) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        boolean userExists = user_service.isUserExists(email);
        if (!userExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with the provided email address.");
        }
        user_service.sendPasswordResetEmail(email);
        return ResponseEntity.ok("If the email is associated with an account, a password reset link has been sent.");
    }

    @PostMapping("/resetpassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPassword request) {
        try {
           user_service.resetPassword(request.getResetPasswordToken(), request.getNewPassword());
            return ResponseEntity.ok("Password successfully reset");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }}