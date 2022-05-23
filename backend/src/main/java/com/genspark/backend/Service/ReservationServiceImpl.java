package com.genspark.backend.Service;

import com.genspark.backend.Dao.ReservationDao;
import com.genspark.backend.Entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationDao reservationDao;
    @Override
    public List<Reservation> getAllReservation() {
        return this.reservationDao.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {

        Optional<Reservation> r = this.reservationDao.findById(id);
        Reservation reservation = null;
        if (r.isPresent())
        {
            reservation = r.get();

        } else {
            throw new RuntimeException(" Course not found for id : " + id);
        }
        return reservation;
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return this.reservationDao.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation, Long reservationID) {

        Reservation r;

        Optional<Reservation> o = reservationDao.findById(reservationID);

        if (o.isPresent()) {
            r = o.get();
        } else {
            r = reservation;
        }

        return this.reservationDao.save(r);
    }

    @Override
    public String deleteReservationById(Long id) {

        this.reservationDao.deleteById(id);
        return "Deleted Successfully";
    }
}
