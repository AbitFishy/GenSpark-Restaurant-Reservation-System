package com.genspark.backend.Dao;

import com.genspark.backend.Entity.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

    final Logger logger = LoggerFactory.getLogger(ReservationDao.class);

}
