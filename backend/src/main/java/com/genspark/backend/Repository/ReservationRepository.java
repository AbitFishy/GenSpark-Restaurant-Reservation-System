package com.genspark.backend.Repository;

import com.genspark.backend.Entity.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Logger logger = LoggerFactory.getLogger(ReservationRepository.class);

    @Query("SELECT res FROM Reservation res WHERE res.email = ?1")
    List<Reservation> findAllReservationsByEmail(String email);
}
