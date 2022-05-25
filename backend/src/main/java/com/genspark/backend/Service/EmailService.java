package com.genspark.backend.Service;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.UserAccount;
import org.springframework.stereotype.Service;


public interface EmailService {

    void sendReservationReminder(Reservation reservation);
    void sendNewUserWelcomeMessage(UserAccount userAccount);
    boolean sendEmail(String to, String subject, String body);
}
