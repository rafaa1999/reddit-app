package com.rafaa.service;

import com.rafaa.dto.RegisterRequest;
import com.rafaa.entity.NotificationEmail;
import com.rafaa.entity.User;
import com.rafaa.entity.VerificationToken;
import com.rafaa.exception.SpringRedditException;
import com.rafaa.repository.UserRepository;
import com.rafaa.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.rafaa.util.Constants.ACTIVATION_EMAIL;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    final private UserRepository userRepository;
    final  private PasswordEncoder passwordEncoder;
    final private VerificationTokenRepository verificationTokenRepository;
    final private  MailContentBuilder mailContentBuilder;
    final private  MailService mailService;

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
        log.info("User is Registered Successfully");
        String token = generateVerificationToken(user);
        String message = mailContentBuilder.build("Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
                + ACTIVATION_EMAIL + "/" + token);
        mailService.sendMail(new NotificationEmail("Please activate your accound", user.getEmail(), message));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .build();
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
    }

    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUserName();
        User user = userRepository.findByUserName(username).orElseThrow(() -> new SpringRedditException("User Not Found with id - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

}
