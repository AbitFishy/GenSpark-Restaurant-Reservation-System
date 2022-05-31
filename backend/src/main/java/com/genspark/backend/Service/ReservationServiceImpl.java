package com.genspark.backend.Service;

import com.genspark.backend.Dao.ReservationDao;
import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationDao reservationDao;

//    @Autowired
//    private JavaMailSender mailSender;
    @Override
    public List<Reservation> getAllReservation() {
        return this.reservationDao.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {

        Optional<Reservation> r = this.reservationDao.findById(id);
        Reservation reservation;
        if (r.isPresent())
        {
            reservation = r.get();

        } else {
            throw new RuntimeException("Reservation not found for id : " + id);
        }
        return reservation;
    }

/*    @Override
    public Reservation getReservationByEmail(String email) {
        var ress = getAllReservationsByEmail(email);
        return ress.get(0);

    }*/

    @Override
    public List<Reservation> getAllReservationsByEmail(String email) {
      var ress= reservationDao.findAllReservationsByEmail(email);
      ress.sort((l,r) -> l.getDateTime().compareTo(r.getDateTime()));
      return ress;
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return this.reservationDao.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation, Long reservationID) {

        Optional<Reservation> reservationOptional = this.reservationDao.findById(reservationID);
        if(!reservationOptional.isPresent())
        {
            throw new RuntimeException("user with id: " + reservationID + " not found");
        }

        Reservation reservationUpdated = reservationOptional.get();

        if(reservation.getDateTime() != null) {
            reservationUpdated.setDateTime(reservation.getDateTime());
        }
        if(reservation.getType() != null) {
            reservationUpdated.setType(reservation.getType());
        }
        if(reservation.getNumberOfGuests() != 0) {
            reservationUpdated.setNumberOfGuests(reservation.getNumberOfGuests());
        }
        if(reservation.getResName() != null) {
            reservationUpdated.setResName(reservation.getResName());
        }
        if(reservation.getResNumber() != null) {
            reservationUpdated.setResNumber(reservation.getResNumber());
        }

        return this.reservationDao.save(reservationUpdated);
    }

    @Override
    public String deleteReservationById(Long id) {

        this.reservationDao.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public List<Reservation> getAllReservation(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Reservation> pagedResult = reservationDao.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }



//    public void sendEmail(String address, String subject, String body) {
//        String from = "restaurant@restaurant.fake";
//        String to = address;
//
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//
//        mailSender.send(message);
//    }
}
