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

    @GetMapping("/user")
    // using 'ROLE_ --> because of ApplicationUserRole class --> method getGrantedAuthority()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<UserAccount> getUserAccounts() {
        return this.userAccountService.getAllUserAccount();
    }

    @GetMapping("/userAccounts/{userID}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOY', 'ROLE_ADMIN')")
    public UserAccount getUserAccount(@PathVariable String userID) {
        return this.userAccountService.getUserAccountById(Long.parseLong(userID));
    }

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('admin:create')")
    public UserAccount addUserAccount(@RequestBody UserAccount userAccount) {
        return this.userAccountService.addUserAccount(userAccount);
    }

    @PostMapping("/login")
    @PreAuthorize("hasAuthority('admin:create', 'employ_create')")
    public UserAccount login(@RequestBody UserAccount userAccount) {
        return this.userAccountService.login(userAccount);
    }

    @PostMapping("/auth")
    @PreAuthorize("hasAuthority('admin:create', 'employ:create')")
    public String authenticateUserAccount(@RequestBody String email, @RequestBody String clearPassword) {
        return this.userAccountService.authenticateUserAccount(email, clearPassword) ? "true" : "false";
    }

    @PutMapping("/userAccounts/{userID}")
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
    @PreAuthorize("hasAnyRole('ROLE_EMPLOY', 'ROLE_ADMIN', 'ROLE_USER')")
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



}
