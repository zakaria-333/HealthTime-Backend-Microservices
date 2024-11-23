package com.example.auth.configuration;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.auth.entities.*;



public class CustomUserDetails implements UserDetails {

  private String username;
  private String password;
  private User user;

  public CustomUserDetails (User u){
    this.username = u.getUsername();
    this.user = u;
    this.password = u.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public User getUser(){
    return user;
  }

}
