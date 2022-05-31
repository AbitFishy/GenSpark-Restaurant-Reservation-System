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

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ReservationService reservationService;

    final Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/index")
    public String home(){
        return "Welcome to the Home Page." + "\n" +
                "Directory:" + "\n" +
                "/api/user " + "/api/user/{userid}" + "\n" +
                "/api/reservation " + "/api/reservation/{reservationid}";
    }
    @GetMapping(path="/user")
    // using 'ROLE_ --> because of ApplicationUserRole class --> method getGrantedAuthority()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOY')")
    public List<UserAccount> getUserAccounts() {
        return this.userAccountService.getAllUserAccount();
    }

    @GetMapping("/user/{userID}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOY', 'ROLE_ADMIN')")
    public UserAccount getUserAccount(@PathVariable String userID) {
        return this.userAccountService.getUserAccountById(Long.parseLong(userID));
    }

    @PostMapping(path="/user")
    @PreAuthorize("hasAuthority('admin:create')")
    public UserAccount addUserAccount(@RequestBody UserAccount userAccount) {
        return this.userAccountService.addUserAccount(userAccount);
    }

    @PostMapping("/login")
    public UserAccount login(@RequestBody UserAccount userAccount) {
        return this.userAccountService.login(userAccount);
    }

    @PostMapping("/auth")
    @PreAuthorize("hasAuthority('admin:create', 'employ:create')")
    public String authenticateUserAccount(@RequestBody String email, @RequestBody String clearPassword) {
        return this.userAccountService.authenticateUserAccount(email, clearPassword) ? "true" : "false";
    }

    @PutMapping(path ="{userID}")
    @PreAuthorize("hasAuthority('admin:edit')")
    public UserAccount updateUserAccount(@RequestBody UserAccount userAccount, @PathVariable Long userID) {
        return this.userAccountService.updateUserAccount(userAccount, userID);
    }

    @DeleteMapping(path="{userID}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public String deleteAccount(@PathVariable String userID)
    {
        return this.userAccountService.deleteUserAccountById(Long.parseLong(userID));
    }

//--------------------------------------------------------------------------------------------------------------------//
    @GetMapping("/reservation")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOY', 'ROLE_ADMIN')")
    public List<Reservation> getReservations() {
        return this.reservationService.getAllReservation();
    }

    @GetMapping("/reservation/{reservationID}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOY', 'ROLE_ADMIN')")
    public Reservation getReservation(@PathVariable String reservationID) {
        return this.reservationService.getReservationById(Long.parseLong(reservationID));
    }

    @PostMapping("/reservation")
    @PreAuthorize("hasAuthority('admin:create', 'employ:create')")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return this.reservationService.addReservation(reservation);
    }

    @PutMapping("/reservation/{reservationID}")
    @PreAuthorize("hasAuthority('admin:edit', 'employ:edit')")
    public Reservation updateReservation(@RequestBody Reservation reservation, @PathVariable Long reservationID) {
        return this.reservationService.updateReservation(reservation, reservationID);
    }

    @DeleteMapping("/reservation/{reservationID}")
    @PreAuthorize("hasAuthority('admin:delete', 'employ:delete')")
    public String deleteReservation(@PathVariable String reservationID)
    {
        return this.reservationService.deleteReservationById(Long.parseLong(reservationID));
    }

//--------------------------------------------------------------------------------------------------------------------//
    // Extras
    // 2fa
    // http://localhost:8080/api/user/{userid}/generateQRCode/code/350/350
    @GetMapping(value = "/user/{userID}/generateQRCode/{width}/{height}")
    public ResponseEntity<byte[]> generateQRCode(
            @PathVariable("codeText") String codeText,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height)
            throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(QRCodeGenerator.getQRCodeImage(codeText, width, height));
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

}
