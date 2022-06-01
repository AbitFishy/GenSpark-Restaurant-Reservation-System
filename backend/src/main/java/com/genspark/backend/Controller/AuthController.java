package com.genspark.backend.Controller;

import com.genspark.backend.Entity.ERole;
import com.genspark.backend.Entity.Role;
import com.genspark.backend.Entity.User;
import com.genspark.backend.Payload.request.LoginRequest;
import com.genspark.backend.Payload.request.SignupRequest;
import com.genspark.backend.Payload.response.MessageResponse;
import com.genspark.backend.Payload.response.UserInfoResponse;
import com.genspark.backend.Repository.RoleRepository;
import com.genspark.backend.Repository.UserRepository;
import com.genspark.backend.Security.jwt.JwtUtils;
import com.genspark.backend.Security.services.UserDetailsImpl;
import com.genspark.backend.Service.EmailService;
import com.genspark.backend.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  final Logger logger = LoggerFactory.getLogger(AuthController.class);
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  UserService userService;

  @Autowired
  EmailService emailService;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    try {

      Authentication authentication = authenticationManager
              .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

      ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

      List<String> roles = userDetails.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.toList());

      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
              .body(new UserInfoResponse(userDetails.getId(),
                      userDetails.getUsername(),
                      userDetails.getEmail(),
                      roles));
    }
    catch (Exception e){
      logger.error("Authentication Error for " + loginRequest.getUsername());
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }


    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(), signUpRequest.getPhoneNumber(),
            signUpRequest.getPassword());

    var res = userService.validateUserAccount(user);
    if (res.equals("")){
      var role = new Role(ERole.ROLE_USER);
      user.setPassword(encoder.encode(user.getPassword()));
      Set<Role> roles = new HashSet<>();
      roles.add(role);
      user.setRoles(roles);
      userRepository.save(user);
      emailService.sendNewUserWelcomeMessage(user);
      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    return ResponseEntity.ok(new MessageResponse(res));

/*  //Other roles should be accessed by different login urls that are admin only
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "ADMIN":
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;
          case "MOD":
            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);

            break;
          default:
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));*/

    //return ResponseEntity.badRequest().build();
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new MessageResponse("You've been signed out!"));
  }
}