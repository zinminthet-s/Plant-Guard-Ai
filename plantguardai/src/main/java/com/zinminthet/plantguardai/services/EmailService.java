package com.zinminthet.plantguardai.services;


import com.zinminthet.plantguardai.exceptions.EmailServiceErrorException;
import com.zinminthet.plantguardai.utils.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private String sender = "cvnxsolution@gmail.com";

    public void sendSimpleMail(EmailDetails details)
    {

        try {

            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);

            System.out.println("mail sending successfully");
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            throw new EmailServiceErrorException(e.getMessage());
        }


    }

}
