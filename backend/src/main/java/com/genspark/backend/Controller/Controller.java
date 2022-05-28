package com.genspark.backend.Controller;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.UserAccount;
//import com.genspark.backend.Service.EmailService;
import com.genspark.backend.Service.QRCodeGenerator;
import com.genspark.backend.Service.ReservationService;
import com.genspark.backend.Service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api")
public class Controller {

    final Logger logger = LoggerFactory.getLogger(Controller.class);



//    @Autowired
//    private EmailService emailService;
//
//    @GetMapping("/dev/testing/email")
//    public String sendTestEmail(){
//        return emailService.sendEmail("catdogramb@gmail.com",
//                "Test from Restaurant",
//                "this was a test message")
//                ?
//                "Successfully sent email"
//                :
//                "Error while sending email";
//    }
}
