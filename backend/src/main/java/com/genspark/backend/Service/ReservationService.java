package com.genspark.backend.Service;

import com.genspark.backend.Entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservation();
    Reservation getReservationById(Long id);
    Reservation addReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation, Long reservationID);
    String deleteReservationById(Long id);

    boolean checkValidStatus(String status);

    String addReservation(String name, String phone, String time, String numGuests, String status);


    String updateReservation(String reservationID, String time, String status);
}
