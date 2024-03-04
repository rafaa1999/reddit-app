package com.rafaa.controller;

import com.rafaa.dto.RegisterRequest;
import com.rafaa.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
   private final AuthService authService;

   @PostMapping("/signup")
   public ResponseEntity signup(@RequestBody RegisterRequest registerRequest){
       authService.signup(registerRequest);
       return ResponseEntity.ok("User is registered");
   }
}
