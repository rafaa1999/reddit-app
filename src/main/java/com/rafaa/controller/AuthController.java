package com.rafaa.controller;

import com.rafaa.dto.RegisterRequest;
import com.rafaa.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

   @GetMapping("/accountVerification/{token}")
   public ResponseEntity<String> verifyAccount(@PathVariable String token){
       authService.verifyAccount(token);
       return ResponseEntity.ok("Account Activated Successfully");
   }
}
