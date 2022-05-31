package com.genspark.backend.Dao;

import com.genspark.backend.Entity.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

    final Logger logger = LoggerFactory.getLogger(ReservationDao.class);

    @Query("SELECT res FROM Reservation res WHERE res.email = ?1")
    List<Reservation> findAllReservationsByEmail(String email);

/*    @Query("SELECT u FROM Reservation u WHERE u.email and max(u.date) = ?1")
    Reservation findMostRecentReservationEmail(String email);*/
}
