package com.example.auth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
  Optional<User> findByUsername(String username);
  List<User> findByRoleId(int id);
  boolean existsByUsername(String username);
}
