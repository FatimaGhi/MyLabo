package com.example.Mylab.Service;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServerice {

    private JavaMailSender mailSender;

    public  EmailServerice(JavaMailSender mailSender){

        this.mailSender= mailSender;
    }

    @Value("${backend.origin}")
    private String ORIGIN;
    @Value("${emailSender}")
    private String from;

    public void sendAccountCreationEmail(String to,String token){
        String link = ORIGIN + "/auth/SingIn?token=" + token;
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(" Create your Account in My Labo");
        message.setText(
                "Hello,\n" +
                        "\n" +
                        "Thank you for registering in My Lobo!  \n" +
                        "To complete your registration, please verify your email by clicking the link below:\n" +
                        "\n" +
                        " Verify your email:\n" + link +
                        "\n" +
                        "If you did not create an account, please ignore this email.\n" +
                        "\n" +
                        "Best regards,  \n" +
                        "My Lobo Team"
        );
        mailSender.send(message);

    }
}
