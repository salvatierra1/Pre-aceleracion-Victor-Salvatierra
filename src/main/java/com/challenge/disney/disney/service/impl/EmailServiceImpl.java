package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    //Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private Environment env;

    public EmailServiceImpl(@Autowired @Lazy Environment env) {
        this.env = env;
    }

    @Value("${pelicula.pelicula.email.sender}")
    private String emailSender;

    @Value("${pelicula.pelicula.email.enabled}")
    private boolean enabled;

    @Override
    public void sendWelcomeEmailTo(String to) {
        if(!enabled) {
            return;
        }

        String apiKey = env.getProperty("EMAIL_API_KEY");

        // Email Data:
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                "text/plain",
                "Bienvenido a Alkemy Disney"
        );
        String subject = "Alkemy Disney";


        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error Trying To Send EMAIL");
        }
    }
}



