package com.genspark.backend.Controller;

import com.genspark.backend.Entity.User;
import com.genspark.backend.Service.EmailService;
import com.genspark.backend.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api")
public class UserController {

    final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/user")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<User> getUsers() {
        return this.userService.getAllUser();
    }

    @GetMapping("/userp")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUser(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "userId") String sortBy)
    {
        List<User> list = userService.getAllUser(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/user/{userID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User getUserById(@Valid @PathVariable Long userID) {
        try {
           if(Objects.equals(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), userID)) {
               return this.userService.getUserById(userID);
           }
        }
        catch (Exception ignored){

        }
        logger.warn("Cannot get userID: " + userID);
        return null;
    }

/*    @PostMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<String> addUser(@Valid @RequestBody User user) {
        return this.userService.addUser(user);
    }*/

    @PutMapping("/user/{userID}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User updateUser(@RequestBody User user, @PathVariable Long userID) {
        if(Objects.equals(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), userID)) {
            return this.userService.updateUser(user, userID);
        }
        logger.warn("Cannot update userID: " +  userID);
        return null;
    }

    @DeleteMapping("/user/{userID}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUserById(@PathVariable String userID)
    {
        return this.userService.deleteUserById(Long.parseLong(userID));
    }



    @GetMapping("/dev/testing/email")
    @PreAuthorize("hasRole('ADMIN')")
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
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}
