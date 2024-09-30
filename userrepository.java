package com.example.Applicationjava.repository;

import com.example.Applicationjava.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userrepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmailAndPassword(String email, String password);
    public Users findByEmail(String email);
    public Users findByResetPasswordToken(String token);


}
