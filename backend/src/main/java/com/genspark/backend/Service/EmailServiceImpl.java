package com.genspark.backend.Service;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;

@Service
public class EmailServiceImpl implements EmailService{

    private final String companyEmail = "RESTaurantGenSpark@gmail.com";
    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private MailSender mailSender;
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendReservationReminder(Reservation reservation) {

        LocalDateTime timeDate = LocalDateTime.from(reservation.getDateTime());
        String sb = "Hello " + reservation.getResName() + ". You have a reservation at Restaurant " +
                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(timeDate) + " at " +
                DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(timeDate) +
                "\n\nIf you need to change your reservation or cancel, please visit our website at localhost:8080/reservations" +
                "or call us at 555-555-5555.\n\nThank you for eating at RESTaurant!";
        String reservationReminderTitle = "A Reminder About Your Reservation at Restaurant";
        sendEmail(reservation.getEmail(), reservationReminderTitle, sb, true);
    }

    @Override
    public void sendNewUserWelcomeMessage(User user) {
        var email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setFrom(companyEmail);
        email.setSubject("Welcome " + user.getUsername() + " to RESTaurant!");

        email.setText("Welcome " + user.getUsername() + " to RESTaurant!");
        new Thread(() -> {
            var res = sendEmailInternal(email);
        }).start();
    }

    @Override
    public boolean sendEmail(String to, String subject, String body, boolean sendAsync) {
        var email = new SimpleMailMessage();
        email.setTo(to);
        email.setFrom(companyEmail);
        email.setSubject(subject);
        email.setText(body);

        if (sendAsync) {
            new Thread(() -> {
                    var res = sendEmailInternal(email);
                }).start();
            return true;
        }
        else{
            return sendEmailInternal(email);

        }
    }

    @Override
    public boolean sendContactEmail(String name, String replyEmail, String message) {
        var email = new SimpleMailMessage();
        email.setTo(companyEmail);
        email.setFrom(companyEmail);
        email.setSubject("Contact Message from " + name);
        email.setText("Name: " +name + "\nEmail: " + replyEmail + "\nMessage:\n"+ message);
        return sendEmailInternal(email);
    }

    @Override
    public void sendNewUserConfirmationEmail(User user) {
        var email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setFrom(companyEmail);
        email.setSubject("Please Confirm your account at RESTaurant!");

        email.setText("Welcome " + user.getUsername() + "!\n + Please click here or copy this" +
                " link and paste in your browswer to confirm your account at RESTaurant!"+
                " LINK GOES HERE");
        new Thread(() -> {
            var res = sendEmailInternal(email);
        }).start();
    }

    private boolean sendEmailInternal(SimpleMailMessage email) {
        try {
            this.mailSender.send(email);
            logger.info("Email sent to " + Arrays.toString(email.getTo()) + " successfully");
            return true;
        } catch (MailException me) {
            logger.warn("Did not send email: " +  email.toString());
            me.printStackTrace();
            return false;
        }
    }
}
