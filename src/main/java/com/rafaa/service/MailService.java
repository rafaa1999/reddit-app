package com.rafaa.service;

import com.rafaa.entity.NotificationEmail;
import com.rafaa.exception.SpringRedditException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
   final private JavaMailSender mailSender;
   final private MailContentBuilder mailContentBuilder;

   @Async
   void sendMail(NotificationEmail notificationEmail){
       MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
           MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
           messageHelper.setFrom("springreddit@gmail.com");
           messageHelper.setTo(notificationEmail.getRecipient());
           messageHelper.setSubject(notificationEmail.getSubject());
           messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
       };
       try {
           mailSender.send(mimeMessagePreparator);
           log.info("Activation email sent !");
       }catch (MailException m){
           throw new SpringRedditException("Exception occurred when sending mail to " + notificationEmail.getRecipient());
       }
   }
}
