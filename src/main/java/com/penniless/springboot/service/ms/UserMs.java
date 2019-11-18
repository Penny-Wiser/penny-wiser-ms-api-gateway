package com.penniless.springboot.service.ms;

import com.penniless.springboot.security.CustomUserDetailsService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(url="http://localhost:9091", name="userms")
public interface UserMs {

  @GetMapping(value="/api/v1/user/load/{email}")
  Optional<CustomUserDetailsService.UserDto> getUserByEmail(@PathVariable("email") String email);
}
