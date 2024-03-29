package com.rafaa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailContentBuilder {
   final private TemplateEngine templateEngine;

   String build(String message){
       Context context = new Context();
       context.setVariable("message", message);
       return templateEngine.process("mailTemplate", context);
   }

}
