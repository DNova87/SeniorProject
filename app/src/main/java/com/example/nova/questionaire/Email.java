package com.javapapers.android.androidjavamail;


import android.os.Bundle;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.service.textservice.SpellCheckerService;
import android.util.Log;

import com.example.nova.questionaire.R;

public class Email {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);

        final String emailPort = "587";// gmail's smtp port
        final String smtpAuth = "true";
        final String starttls = "true";
        final String emailHost = "smtp.gmail.com";

        String fromEmail;
        String fromPassword;
        List toEmailList;
        String emailSubject;
        String emailBody;

        Properties emailProperties;
        SpellCheckerService.Session mailSession;
        MimeMessage emailMessage;

        String MY_FILE_NAME = "questionairefile.txt";
        // Create a new output file stream
    }

        public Email(String fromEmail, String fromPassword,
                     List toEmailList, String emailSubject, String emailBody) {
            this.fromEmail = fromEmail;
            this.fromPassword = fromPassword;
            this.toEmailList = toEmailList;
            this.emailSubject = emailSubject;
            this.emailBody = emailBody;

            emailProperties = System.getProperties();
            emailProperties.put("mail.smtp.port", emailPort);
            emailProperties.put("mail.smtp.auth", smtpAuth);
            emailProperties.put("mail.smtp.starttls.enable", starttls);
            Log.i("GMail", "Mail server properties set.");
        }

        public MimeMessage createEmailMessage() throws AddressException,
                MessagingException, UnsupportedEncodingException {

            mailSession = Session.getDefaultInstance(emailProperties, null);
            emailMessage = new MimeMessage(mailSession);

            emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
            for (String toEmail : toEmailList) {
                Log.i("GMail", "toEmail: " + toEmail);
                emailMessage.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(toEmail));
            }

            emailMessage.setSubject(emailSubject);
            emailMessage.setContent(emailBody, "text/html");// for a html email
            // emailMessage.setText(emailBody);// for a text email
            Log.i("GMail", "Email Message created.");
            return emailMessage;
        }

        public void sendEmail() throws AddressException, MessagingException {

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(emailHost, fromEmail, fromPassword);
            Log.i("GMail", "allrecipients: " + emailMessage.getAllRecipients());
            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
            transport.close();
            Log.i("GMail", "Email sent successfully.");
        }

    }