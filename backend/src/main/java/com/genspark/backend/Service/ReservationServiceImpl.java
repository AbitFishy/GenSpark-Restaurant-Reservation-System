package com.genspark.backend.Service;

import com.genspark.backend.Dao.ReservationDao;
import com.genspark.backend.Entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    ReservationDao reservationDao;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private JavaMailSender mailSender;
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

    @Override
    public Reservation addReservation(Reservation reservation) {
        return this.reservationDao.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation, Long reservationID) {

        Reservation r;

        Optional<Reservation> o = reservationDao.findById(reservationID);

        r = o.orElse(reservation);

        return this.reservationDao.save(r);
    }

    @Override
    public String deleteReservationById(Long id) {

        this.reservationDao.deleteById(id);
        return "Deleted Successfully";
    }

    public void sendEmail(String address, String subject, String body) {
        String from = "restaurant@restaurant.fake";
        String to = address;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    @Override
    public boolean checkValidStatus(String status) {
        return Set.of("PENDING","CONFIRMED","ARRIVED","CANCELLED","DONE").contains(status);
    }

    @Override
    public String addReservation(String name, String phone, String time, String numGuests, String status) {
        var res = new Reservation(-1, LocalDateTime.parse(time), Integer.parseInt(numGuests), status,
                userAccountService.getNewGuestAccount(name,phone));
        res = reservationDao.save(res);
        var id = res.getReservationId();
        if (id > 0){
            return String.valueOf(id);
        }
        else {
            return "Reservation Failed";
        }
    }

    @Override
    public String updateReservation(String reservationID, String time, String status) {
        var optreser = reservationDao.findById(Long.parseLong(reservationID));
        if (optreser.isEmpty()){
            return "Reservation Not Found";
        }
        var reser = optreser.get();
        if (time != null && time.length() != 0){
            reser.setDateTime(LocalDateTime.parse(time));
        }
        if (status != null && status.length() != 0){
            reser.setStatus(status);
        }
        try {
            reservationDao.save(reser);
            return "Successfully Updated Reservation";
        }catch(Exception e){
            e.printStackTrace();
            return "Error Updating Reservation";
        }
    }


}
