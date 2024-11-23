package com.example.auth.controllers;

import com.example.auth.configuration.CustomUserDetails;
import com.example.auth.dto.AuthRequestDto;
import com.example.auth.dto.AuthResponseDto;
import com.example.auth.entities.User;
import com.example.auth.repositories.UserRepository;
import com.example.auth.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/login")
  public AuthResponseDto login(@RequestBody AuthRequestDto authRequestDto) throws Exception {
    User user = null;
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));

    if (authentication.isAuthenticated()) {
      CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
      user = userDetails.getUser();
      String token = authService.generateToken(authRequestDto.getUsername(), user.getRole().getName());
      return new AuthResponseDto(user, token);
    } else {
      throw new Exception("Invalid Access");
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody User user) {
    if (userRepository.existsByUsername(user.getUsername())) {
      return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
  }

}
