package com.code.onboarding.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.onboarding.service.MailNotificationService;
import com.code.onboarding.util.ApplicationPropertiesUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailNotificationServiceImpl implements MailNotificationService {

  private ApplicationPropertiesUtil yamlUtil;

  @Autowired
  public MailNotificationServiceImpl(ApplicationPropertiesUtil yamlUtil) {
    this.yamlUtil = yamlUtil;
  }

  @Override
  public void sendNotificationForOnboardingInitiation(List<String> recipients) {

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
    props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL
                                                                                  // Factory
                                                                                  // Class
    props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
    props.put("mail.smtp.port", "465"); // SMTP Port

    Authenticator auth = new Authenticator() {
      // override the getPasswordAuthentication method
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(yamlUtil.getSenderAddress(),
            yamlUtil.getPassword());
      }
    };

    // Get the session object
    Session session = Session.getDefaultInstance(props, auth);

    try {
      // Create a default MimeMessage object.
      Message message = new MimeMessage(session);

      // set message headers
      message.addHeader("Content-type", "text/HTML; charset=UTF-8");
      message.addHeader("format", "flowed");
      message.addHeader("Content-Transfer-Encoding", "8bit");

      message.setFrom(
          new InternetAddress(yamlUtil.getSenderAddress(), "NoReply-Onboarding"));

      message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(String.join(",", recipients), false));

      // Set Subject: header field
      message.setSubject("My first message with JavaMail");

      // Put the content of your message
      message.setText("Hi there, this is my first message sent with JavaMail");

      // Send message
      Transport.send(message);

      log.info("Message sent successfully...." + message);

    } catch (MessagingException | UnsupportedEncodingException ex) {
      log.error("Messaging exception occured while sending mail" + ex.getMessage(), ex);
      throw new RuntimeException(ex);
    } catch (Exception ex) {
      log.error("Generic exception occured while sending mail" + ex.getMessage(), ex);
      throw new RuntimeException(ex);
    }
  }

}
