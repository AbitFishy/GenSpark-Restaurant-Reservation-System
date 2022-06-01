package com.genspark.backend.Service;

import com.genspark.backend.Entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservation();
    Reservation getReservationById(Long id);

    //Reservation getReservationByEmail(String Email) ;

    List<Reservation> getAllReservationsByEmail(String Email);
    List<Reservation> getAllReservationsByUserID(Long userID);
    Reservation addReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation, Long reservationID);
    String deleteReservationById(Long id);
    List<Reservation> getAllReservation(Integer pageNo, Integer pageSize, String sortBy);

    boolean checkValidStatus(String status);

    boolean checkNewReservationDate(LocalDateTime dateTime);

    boolean checkNumGuests(int numGuests);

    boolean checkIsUserInDatabase(String email);

    String validateNewReservation(Reservation reservation);
}
