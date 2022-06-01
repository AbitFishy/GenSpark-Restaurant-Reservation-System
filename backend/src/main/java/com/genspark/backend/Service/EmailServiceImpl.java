package com.genspark.backend.Service;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.User;
import com.genspark.backend.Repository.UserRepository;
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
    public void sendNewUserWelcomeMessage(User userAccount) {

    }

    @Override
    public boolean sendEmail(String to, String subject, String body, boolean sendAsync) {
        var email = new SimpleMailMessage();
        email.setTo(to);
        email.setFrom("RESTaurantGenSpark@gmail.com");
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
