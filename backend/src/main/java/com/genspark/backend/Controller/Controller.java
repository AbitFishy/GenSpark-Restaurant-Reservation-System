package com.genspark.backend.Controller;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.UserAccount;
import com.genspark.backend.Service.ReservationService;
import com.genspark.backend.Service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public String home() {
        return "Hi there.";
    }

    @GetMapping("/userAccounts")
    public List<UserAccount> getUserAccounts() {
        return this.userAccountService.getAllUserAccount();
    }

    @GetMapping("/userAccounts/{userID}")
    public UserAccount getUserAccount(@PathVariable String userID) {
        return this.userAccountService.getUserAccountById(Long.parseLong(userID));
    }

    @PostMapping("/userAccounts")
    public UserAccount addUserAccount(@RequestBody UserAccount userAccount) {
        return this.userAccountService.addUserAccount(userAccount);
    }

    @PostMapping("/newUserAccounts")
    public UserAccount addUserAccount(@RequestBody String primaryName,
                                      @RequestBody String phoneNumber,
                                      @RequestBody String clearPassword,
                                      @RequestBody String email){
        return this.userAccountService.addUserAccount(primaryName,phoneNumber,clearPassword,email);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserAccount userAccount) {
        return this.userAccountService.login(userAccount);
    }

    @PostMapping("/auth")
    public UserAccount authenticateUserAccount(@RequestBody String email, @RequestBody String clearPassword) {
        return this.userAccountService.authenticateUserAccount(email, clearPassword);
    }

    @GetMapping("/auth/chk")
    public String checkPWDReqs(@RequestBody String clearPassword){
        return this.userAccountService.checkPasswordComplexity(clearPassword);
    }

    @PutMapping("/userAccounts")
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

    @GetMapping("/reservations/{reservationID}")
    public Reservation getReservation(@PathVariable String reservationID) {
        return this.reservationService.getReservationById(Long.parseLong(reservationID));
    }

    @PostMapping("/reservations")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return this.reservationService.addReservation(reservation);
    }

    @PutMapping("/reservations")
    public Reservation updateReservation(@RequestBody Reservation reservation, @PathVariable Long reservationID) {
        return this.reservationService.updateReservation(reservation, reservationID);
    }

    @DeleteMapping("/reservations/{reservationID}")
    public String deleteReservation(@PathVariable String reservationID)
    {
        return this.reservationService.deleteReservationById(Long.parseLong(reservationID));
    }
}
