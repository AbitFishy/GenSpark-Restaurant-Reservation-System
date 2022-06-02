package com.genspark.backend.Security;

import com.genspark.backend.Entity.User;
import com.genspark.backend.Payload.request.SignupRequest;
import com.genspark.backend.Repository.RoleRepository;
import com.genspark.backend.Repository.UserRepository;
import com.genspark.backend.Security.jwt.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationTest {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @Test
    public void testUserSignUpCreation(){
        SignupRequest signUpRequest = new SignupRequest();
        signUpRequest.setUsername("bowwy");
        signUpRequest.setPassword("password");
        signUpRequest.setEmail("k@genspark.net");
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));


    }
}
