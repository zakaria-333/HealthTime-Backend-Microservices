package com.example.auth.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.entities.Role;
import com.example.auth.entities.User;
import com.example.auth.repositories.UserRepository;

@Service
public class UserService{
 
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   
   public User update(User user) {
      if (userRepository.existsById(user.getId())) {
         userRepository.save(user);
         return user;
      }
      return user;
   }

   
   public boolean create(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
      return true;
   }

   
   public boolean delete(int id) {
      if (userRepository.existsById((Integer) id)) {
         userRepository.deleteById((Integer) id);
         return true;
      }
      return false;
   }

   
   public List<User> findAll() {
      return userRepository.findAll();
   }

   
   public User getById(int id) {
      Optional<User> user = userRepository.findById(id);
      return user.orElse(null);
   }


   public List<User> findByRoleId(int id) {
      return  userRepository.findByRoleId(id)  ;
    }
}
