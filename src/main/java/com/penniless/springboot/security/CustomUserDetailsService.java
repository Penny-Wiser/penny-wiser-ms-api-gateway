package com.penniless.springboot.security;

import com.penniless.springboot.service.ms.UserMs;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

  private UserMs userMs;

  public CustomUserDetailsService(UserMs userMs) {
    this.userMs = userMs;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("Getting UserDetails from user service...");
    UserDto userDto = userMs.getUserByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email: %s could not be found.", username)));

    log.info("Current logged in user: {}", userDto);
    return new User(userDto.getEmail(), userDto.getPassword(),true, true, true, true, Collections.emptyList());
  }

  @Getter
  @Setter
  @ToString
  public static class UserDto {
    private String email;
    private String password;
  }
}