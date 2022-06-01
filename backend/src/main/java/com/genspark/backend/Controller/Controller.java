package com.genspark.backend.Controller;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.User;
import com.genspark.backend.Service.EmailService;
import com.genspark.backend.Service.ReservationService;
import com.genspark.backend.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    private UserService userService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String home() {
        return "Hi there.";
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return this.userService.getAllUser();
    }

    @GetMapping("/userp")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "userId") String sortBy)
    {
        List<User> list = userService.getAllUser(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/userAccounts/{userID}")
    public User getUser(@PathVariable String userID) {
        return this.userService.getUserById(Long.parseLong(userID));
    }
    @GetMapping("user/{email}")
    public User getUserEmail(@PathVariable String email){
        return this.userService.getUserByEmail(email);
    }

    @PostMapping("/user")
    ResponseEntity<String> addUser(@Valid @RequestBody User userAccount) {
        return this.userService.addUser(userAccount);
    }

    @PostMapping("/login")
    public User login(@RequestBody User userAccount) {
        return this.userService.login(userAccount);
    }

    @PutMapping("/user/{userID}")
    public User updateUser(@RequestBody User userAccount, @PathVariable Long userID) {
        return this.userService.updateUser(userAccount, userID);
    }

    @DeleteMapping("/user/{userID}")
    public String deleteAccount(@PathVariable String userID)
    {
        return this.userService.deleteUserById(Long.parseLong(userID));
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

/*    @GetMapping("/reservatione/{email}")
    public Reservation getReservationByEmail(@PathVariable String email){
    return this.reservationService.getReservationByEmail(email);
    }*/
    @GetMapping("/reservationse/{email}")
    public List<Reservation> getReservationsByEmail(@PathVariable String email){
        return this.reservationService.getAllReservationsByEmail(email);
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
                "this was a test message")
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
