package com.genspark.backend.Controller;

import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/reservations")
public class ReservationManagementController {

    final Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservation")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOY', 'ROLE_ADMIN')")
    public List<Reservation> getReservations() {
        return this.reservationService.getAllReservation();
    }

    @GetMapping("/reservation/{reservationID}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOY', 'ROLE_ADMIN', ROLE_USER)")
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
}
