package com.genspark.backend.Controller;

import com.genspark.backend.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class WebController {

    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String goToHomePage(){
        return "home";
    }
    @GetMapping(value = "")
    public String goToHomePage2(){
        return "home";
    }

    @PostMapping("/api/contact")
    public String contactByEmail(@RequestParam String name, @RequestParam String email, @RequestParam String message){
        return String.valueOf(emailService.sendContactEmail(name, email, message));
    }
}
