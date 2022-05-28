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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api")
public class Controller {

    final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ReservationService reservationService;

//    @Autowired
//    private EmailService emailService;

    @GetMapping("/")
    public String home() {
        return "Hi there.";
    }

    @GetMapping("/user")
    public List<UserAccount> getUserAccounts() {
        return this.userAccountService.getAllUserAccount();
    }

    @GetMapping("/userAccounts/{userID}")
    public UserAccount getUserAccount(@PathVariable String userID) {
        return this.userAccountService.getUserAccountById(Long.parseLong(userID));
    }

    @PostMapping("/user")
    public UserAccount addUserAccount(@RequestBody UserAccount userAccount) {
        return this.userAccountService.addUserAccount(userAccount);
    }

    @PostMapping("/login")
    public UserAccount login(@RequestBody UserAccount userAccount) {
        return this.userAccountService.login(userAccount);
    }

    @PostMapping("/auth")
    public String authenticateUserAccount(@RequestBody String email, @RequestBody String clearPassword) {
        return this.userAccountService.authenticateUserAccount(email, clearPassword) ? "true" : "false";
    }

    @PutMapping("/userAccounts/{userID}")
    public UserAccount updateUserAccount(@RequestBody UserAccount userAccount, @PathVariable Long userID) {
        return this.userAccountService.updateUserAccount(userAccount, userID);
    }

    @DeleteMapping("/userAccounts/{userID}")
    public String deleteAccount(@PathVariable String userID)
    {
        return this.userAccountService.deleteUserAccountById(Long.parseLong(userID));
    }

    @GetMapping("/reservation")
    public List<Reservation> getReservations() {
        return this.reservationService.getAllReservation();
    }

    @GetMapping("/reservation/{reservationID}")
    public Reservation getReservation(@PathVariable String reservationID) {
        return this.reservationService.getReservationById(Long.parseLong(reservationID));
    }

    @PostMapping("/reservation")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return this.reservationService.addReservation(reservation);
    }

    @PutMapping("/reservation/{reservationID}")
    public Reservation updateReservation(@RequestBody Reservation reservation, @PathVariable Long reservationID) {
        return this.reservationService.updateReservation(reservation, reservationID);
    }

    @DeleteMapping("/reservation/{reservationID}")
    public String deleteReservation(@PathVariable String reservationID)
    {
        return this.reservationService.deleteReservationById(Long.parseLong(reservationID));
    }

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

    // 2fa
    // http://localhost:8080/generateQRCode/code/350/350
    @GetMapping(value = "/generateQRCode/{codeText}/{width}/{height}")
    public ResponseEntity<byte[]> generateQRCode(
            @PathVariable("codeText") String codeText,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, width, height));
    }
    // http://localhost:8080/generateAndDownloadQRCode/code/350/350
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";
    @GetMapping(value = "/generateAndDownloadQRCode/{codeText}/{width}/{height}")
    public void download(
            @PathVariable("codeText") String codeText,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        QRCodeGenerator.generateQRCodeImage(codeText, width, height, QR_CODE_IMAGE_PATH);
    }
}
