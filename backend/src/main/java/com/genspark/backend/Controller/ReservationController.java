package com.genspark.backend.Controller;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.User;
import com.genspark.backend.Service.EmailService;
import com.genspark.backend.Service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ReservationController {

  final Logger logger = LoggerFactory.getLogger(ReservationController.class);

  @Autowired
  private ReservationService reservationService;

  @Autowired
  private EmailService emailService;

  @GetMapping("/reservation")
  @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
  public List<Reservation> getReservations() {
    return this.reservationService.getAllReservation();
  }

  @GetMapping("/reservationsuser/{userID}")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public List<Reservation> getReservationsForUser(@Valid @PathVariable Long userID){
    if(Objects.equals(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), userID)) {
      return reservationService.getAllReservationsByUserID(userID);
    }
    logger.warn("Could not retrieve reservations by userID: " + userID);
    return null;
  }

  @GetMapping("/usertest")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }

//  @GetMapping("/reservationp")
//  public ResponseEntity<List<Reservation>> getAllReservations(
//          @RequestParam(defaultValue = "0") Integer pageNo,
//          @RequestParam(defaultValue = "10") Integer pageSize,
//          @RequestParam(defaultValue = "resId") String sortBy)
//  {
//    List<Reservation> list = reservationService.getAllReservation(pageNo, pageSize, sortBy);
//    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
//  }

  @GetMapping("/reservation/{reservationID}")
  @PreAuthorize("hasRole('ADMIN')")
  public Reservation getReservation(@Valid @PathVariable String reservationID) {
    try {
      return this.reservationService.getReservationById(Long.parseLong(reservationID));
    }
    catch (Exception e){
      logger.warn("ReservationID not valid ID: "+ reservationID);
    }
    return null;
  }

  @PostMapping("/reservation/add")
//  @PostAuthorize("hasRole('USER')")
  public Reservation addReservation(@Valid @RequestBody Reservation reservation) {
    return this.reservationService.addReservation(reservation);
  }

  @PutMapping("/reservation/{reservationID}")
  @PostAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public Reservation updateReservation(@RequestBody Reservation reservation, @PathVariable Long reservationID) {
    return this.reservationService.updateReservation(reservation, reservationID);
  }

  @DeleteMapping("/reservation/{reservationID}")
  @PostAuthorize("hasRole('ADMIN')")
  public String deleteReservation(@Valid @PathVariable String reservationID)
  {
    return this.reservationService.deleteReservationById(Long.parseLong(reservationID));
  }

  @GetMapping("/reservationse/{email}")
  public List<Reservation> getReservationsByEmail(@Valid @PathVariable String email){
    return this.reservationService.getAllReservationsByEmail(email);
  }
}
