package com.genspark.backend.Controller;

import com.genspark.backend.Entity.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@Controller
public class WebController {

    final Logger logger = LoggerFactory.getLogger(com.genspark.backend.Controller.Controller.class);

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
}

