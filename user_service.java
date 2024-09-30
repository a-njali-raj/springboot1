package com.example.Applicationjava.service;



import com.example.Applicationjava.Entity.Users;
import com.example.Applicationjava.repository.userrepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class user_service {
    @Autowired
    private userrepository userRepository;
    @Autowired

    private JavaMailSender mailSender;


    public Users register(Users user) {

        return userRepository.save(user);
    }

    public List<Users> getAllUsers() {

        return userRepository.findAll();
    }
    public Optional<Users> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Transactional
    public String createPasswordResetToken(String email) {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("User with email " + email + " not found.");
            return null;
        }

        // Generate a random token
        String token = UUID.randomUUID().toString();

        // Set the token and expiry date
        user.setResetPasswordToken(token);
        user.setTokenExpiry(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour
        userRepository.save(user);
        return token;

    }


    public boolean isUserExists(String email) {
        // Query the database to check if the user exists with the provided email address
        Users user = userRepository.findByEmail(email);
        return user != null;
    }

    // Send password reset email
    public void sendPasswordResetEmail(String email) {

        // Check if the user exists in the database
        String token = createPasswordResetToken(email);
        if (token != null)
        {
            String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Reset Request");
            message.setText("To reset your password, click the link below:\n" + resetLink);
            mailSender.send(message);
            System.out.println("Password reset email sent to: " + email); // Log the success
        }
        else
        {
            // Log the message when the user is not found
            System.out.println("Failed to send password reset email. User not found.");
        }
    }

    @Transactional
    // Reset the password
    public void resetPassword(String token, String newPassword) {
        Users user = userRepository.findByResetPasswordToken(token);
        if (user == null || user.getTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invalid or expired token");
        }

        // Set the new password
        user.setPassword(newPassword);

        // Clear the token and expiry time after password reset is complete
        user.setResetPasswordToken(null);
        user.setTokenExpiry(null);
        userRepository.save(user);
    }

}

