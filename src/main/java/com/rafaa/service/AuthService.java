package com.rafaa.service;

import com.rafaa.dto.RegisterRequest;
import com.rafaa.entity.User;
import com.rafaa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    final private UserRepository userRepository;
    final  private PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = User.builder()
                .userName(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .created(Instant.now())
                .enabled(false)
                .build();
        userRepository.save(user);
        log.info("User is Registered under the name of {}", user.getUserName());
    }

}
