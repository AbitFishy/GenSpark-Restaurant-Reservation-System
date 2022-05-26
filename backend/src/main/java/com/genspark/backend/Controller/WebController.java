package com.genspark.backend.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@CrossOrigin(origins = "*")
@Controller
public class WebController {

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
/*    @PostMapping("/login")
    public String auth(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String rememberme)*/
}
