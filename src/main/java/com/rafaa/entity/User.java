package com.rafaa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;

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
