package com.rafaa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long userId;
   @NotBlank(message = "Username is required")
   private String userName;
   @NotBlank(message = "Password is required")
   private String password;
   @Email
   @NotEmpty(message = "Email is required")
   private String email;
   private Instant created;
   private boolean enabled;
}
