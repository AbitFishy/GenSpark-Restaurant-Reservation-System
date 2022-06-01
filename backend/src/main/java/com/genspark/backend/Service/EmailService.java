package com.genspark.backend.Service;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.User;


public interface EmailService {

    void sendReservationReminder(Reservation reservation);
    void sendNewUserWelcomeMessage(User userAccount);

    boolean sendEmail(String to, String subject, String body, boolean sendAsync);
    boolean sendContactEmail(String name, String replyEmail, String message);

    void sendNewUserConfirmationEmail(User userAccount);
}
