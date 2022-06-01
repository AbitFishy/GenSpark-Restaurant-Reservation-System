package com.genspark.backend.Service;

import com.genspark.backend.Entity.User;
import com.genspark.backend.Repository.ReservationRepository;
import com.genspark.backend.Repository.UserRepository;
import com.genspark.backend.Entity.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService{

    final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Override
    public List<Reservation> getAllReservation() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {

        Optional<Reservation> r = this.reservationRepository.findById(id);
        Reservation reservation;
        if (r.isPresent())
        {
            reservation = r.get();
            return reservation;

        } else {
            logger.warn("Reservation not found for id : " + id);
            return null;
        }
    }

    @Override
    public List<Reservation> getAllReservationsByEmail(String email) {
        if (email.length() < 3){
            logger.warn("Email + " + email + " too short to be valid");
            return null;
        }
      var ress= reservationRepository.findAllReservationsByEmail(email);
      if (ress != null) {
          ress.sort(Comparator.comparing(Reservation::getDateTime));
      }
      return ress;
    }

    @Override
    public List<Reservation> getAllReservationsByUserID(Long userID) {
        String email = userService.getUserById(userID).getEmail();
        return getAllReservationsByEmail(email);
    }

    @Override
    public Reservation addReservation(Reservation reservation) {

        if (Objects.equals(validateNewReservation(reservation), "")){
            logger.trace("reservation validated");
            var res =  this.reservationRepository.save(reservation);
            emailService.sendReservationReminder(res);
            logger.trace("reservation add with id " + res.getResId());
        }
        logger.warn("Reservation failed validation");
        return null;

    }

    @Override
    public Reservation updateReservation(Reservation reservation, Long reservationID) {
        if (reservation == null){
            logger.warn("Attempting to update with NULL reservation");
            return null;
        }
        Optional<Reservation> reservationOptional = this.reservationRepository.findById(reservationID);
        if(reservationOptional.isEmpty())
        {
            logger.error("User with id: " + reservationID + " not found during updateReservation");
            return null;
        }

        Reservation reservationUpdated = reservationOptional.get();

        if(reservation.getDateTime() != null) {
            if (checkNewReservationDate(reservation.getDateTime())) {
                reservationUpdated.setDateTime(reservation.getDateTime());
            }
        }
        if(reservation.getType() != null) {
            reservationUpdated.setType(reservation.getType());
        }
        if(reservation.getNumberOfGuests() != 0) {
             if (checkNumGuests(reservation.getNumberOfGuests())) {
                 reservationUpdated.setNumberOfGuests(reservation.getNumberOfGuests());
             }
        }
        if(reservation.getResName() != null) {
            reservationUpdated.setResName(reservation.getResName());
        }
        if(reservation.getResNumber() != null) {
            reservationUpdated.setResNumber(reservation.getResNumber());
        }

        return this.reservationRepository.save(reservationUpdated);
    }

    @Override
    public String deleteReservationById(Long id) {

        this.reservationRepository.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public List<Reservation> getAllReservation(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Reservation> pagedResult = reservationRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean checkValidStatus(String status) {
        return status != null && Set.of("PENDING","CONFIRMED","ARRIVED","CANCELLED","DONE").contains(status);
    }

    @Override
    public boolean checkNewReservationDate(LocalDateTime dateTime){ //range > 1 hour from now,  < 3 months from now
         return (LocalDateTime.now().compareTo(dateTime.plusHours(1)) < 0 )  && (LocalDateTime.now().plusMonths(3).compareTo(dateTime) >0) ;
    }

    @Override
    public boolean checkNumGuests(int numGuests){
        return numGuests >= 1 && numGuests <= 10;
    }

    @Override
    public boolean checkIsUserInDatabase(String email){
        return email != null && userRepository.findUserByEmail(email) != null;
    }

    @Override
    public String validateNewReservation(Reservation reservation) {
        if (reservation == null) {
            String str = "NULL Reservation";
            logger.info(str);
            return str;
        }

        if (reservation.isGuestReservation()) {
            if (reservation.getEmail() != null || reservation.getResNumber() != null) {
                if (!(reservation.getResName() != null && reservation.getResName().length() > 2)) {
                    String str = "Need Name longer than 2 characters: " + reservation.getResName();
                    logger.info(str);
                    return str;
                }
                if (!checkNumGuests(reservation.getNumberOfGuests())) {
                    String str = "Number of Guest Invalid: " + reservation.getNumberOfGuests();
                    logger.info(str);
                    return str;
                }
                if (!checkNewReservationDate(reservation.getDateTime())) {
                    String str = "Date out of Valid Range: " + reservation.getDateTime().toString();
                    logger.info(str);
                    return str;
                }

                if (!
                        userService.checkEmailForAtSign(reservation.getEmail())) {

                    String str = "Invalid Email: " + reservation.getEmail();
                    logger.info(str);
                    return str;
                }

                if (!userService.checkNumber(reservation.getResNumber())) {
                    String str = "Invalid number: " + reservation.getResNumber();
                    logger.info(str);
                    return str;
                }

            } else {
                String str = "Need either an Email or Phone number";
                logger.info(str);
                return str;
            }
        } else if (this.checkIsUserInDatabase(reservation.getEmail())) {
            var user = userRepository.findUserByEmail(reservation.getEmail());
            if (reservation.getResName() == null) {
                reservation.setResName(user.getUsername());
            }
            if (reservation.getResName().length() > 2) {
                String str = "Need Name longer than 2 characters: " + reservation.getResName();
                logger.info(str);
                return str;
            }
            if (!checkNumGuests(reservation.getNumberOfGuests())) {
                String str = "Number of Guest Invalid: " + reservation.getNumberOfGuests();
                logger.info(str);
                return str;
            }
            if (!checkNewReservationDate(reservation.getDateTime())) {
                String str = "Date out of Valid Range: " + reservation.getDateTime().toString();
                logger.info(str);
                return str;
            }
            if (reservation.getResNumber() == null) {
                reservation.setResNumber(user.getUsername());
            }
            if (!userService.checkNumber(reservation.getResNumber())) {
                String str = "Invalid number: " + reservation.getResNumber();
                logger.info(str);
                return str;
            }

        }
        return "";
    }

}
