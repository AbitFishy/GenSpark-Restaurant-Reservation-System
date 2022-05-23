package com.genspark.backend.Dao;

import com.genspark.backend.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDao extends JpaRepository<Reservation, Long> {
}
