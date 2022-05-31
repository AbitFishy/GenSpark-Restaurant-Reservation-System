package com.genspark.backend.Controller;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.UserAccount;
import com.genspark.backend.Service.EmailService;
import com.genspark.backend.Service.ReservationService;
import com.genspark.backend.Service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api")
public class Controller {

    final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String home() {
        return "Hi there.";
    }

    @GetMapping("/user")
    public List<UserAccount> getUserAccounts() {
        return this.userAccountService.getAllUserAccount();
    }

    @GetMapping("/userp")
    public ResponseEntity<List<UserAccount>> getAllUserAccounts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "userId") String sortBy)
    {
        List<UserAccount> list = userAccountService.getAllUserAccount(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/userAccounts/{userID}")
    public UserAccount getUserAccount(@PathVariable String userID) {
        return this.userAccountService.getUserAccountById(Long.parseLong(userID));
    }

    @PostMapping("/user")
    ResponseEntity<String> addUserAccount(@Valid @RequestBody UserAccount userAccount) {
        return this.userAccountService.addUserAccount(userAccount);
    }

    @PostMapping("/login")
    public UserAccount login(@RequestBody UserAccount userAccount) {
        return this.userAccountService.login(userAccount);
    }

    @PutMapping("/user/{userID}")
    public UserAccount updateUserAccount(@RequestBody UserAccount userAccount, @PathVariable Long userID) {
        return this.userAccountService.updateUserAccount(userAccount, userID);
    }

    @DeleteMapping("/user/{userID}")
    public String deleteAccount(@PathVariable String userID)
    {
        return this.userAccountService.deleteUserAccountById(Long.parseLong(userID));
    }

    @GetMapping("/reservation")
    public List<Reservation> getReservations() {
        return this.reservationService.getAllReservation();
    }

    @GetMapping("/reservationp")
    public ResponseEntity<List<Reservation>> getAllReservations(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "resId") String sortBy)
    {
        List<Reservation> list = reservationService.getAllReservation(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
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

    @GetMapping("/dev/testing/email")
    public String sendTestEmail() {
        return emailService.sendEmail("tkim013@gmail.com",
                "Test from Restaurant",
                "this was a test message", true)
                ?
                "Successfully sent email"
                :
                "Error while sending email";
    }

    //When Spring Boot finds an argument annotated with @Valid, it automatically bootstraps the
    //default JSR 380 implementation — Hibernate Validator — and validates the argument.
    //When the target argument fails to pass the validation, Spring Boot throws a MethodArgumentNotValidException exception.
    //The @ExceptionHandler annotation allows us to handle specified types of exceptions through one single method.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
