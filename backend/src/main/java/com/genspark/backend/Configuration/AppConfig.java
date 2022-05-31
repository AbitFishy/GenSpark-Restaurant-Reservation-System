
package com.genspark.backend.Configuration;

import com.genspark.backend.Dao.ReservationDao;
import com.genspark.backend.Dao.UserAccountDao;
import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.UserAccount;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;
import java.util.List;

import static com.genspark.backend.Security.ApplicationUserRole.*;

@Configuration
public class AppConfig {
    @Bean
    public CommandLineRunner loadUserData(UserAccountDao userAccountDao){
        List<UserAccount> USERACCOUNT = Arrays.asList(
                new UserAccount
                        ("ElonMusk","1", "password", "Elon@Tesla.com", USER, USER.getGrantedAuthorities(),true),
                new UserAccount
                        ("BillGates","2", "password", "bill@microsoft.com",  EMPLOY, USER.getGrantedAuthorities(),true),
                new UserAccount
                        ("UnderTaker","3", "password", "taker@wwe.com",  USER, USER.getGrantedAuthorities(),false),
                new UserAccount
                        ("JoeBiden","4", "password", "biden@whitehouse.gov",  ADMIN, USER.getGrantedAuthorities(),true),
                new UserAccount
                        ("Megatron","5", "password", "Deceptacons@space.com",  USER, USER.getGrantedAuthorities(),false),
                new UserAccount
                        ("Cthulu","6", "password", "cult@deepsea.com",  EMPLOY, USER.getGrantedAuthorities(),true),
                new UserAccount
                        ("FishMan","7", "password", "joe@fish.com",  USER, USER.getGrantedAuthorities(),false),
                new UserAccount
                        ("Naruto","7", "password", "ninja@fireofwill.com",  USER, USER.getGrantedAuthorities(),true),
                new UserAccount
                        ("Luffy","9", "password", "captain@onepiece.com",  USER, USER.getGrantedAuthorities(),true),
                new UserAccount
                        ("QueenElizabeth","10", "password", "queen@royal.com",  USER, USER.getGrantedAuthorities(),true),
                new UserAccount
                        ("Kanye","11", "password", "west@yeezy.com",  USER, USER.getGrantedAuthorities(),true)
        );
        return (args) -> {
            userAccountDao.saveAll(USERACCOUNT);
            userAccountDao.save(new UserAccount
                    ("Bob","1", "password", "bob@google.com", USER, USER.getGrantedAuthorities(),true));
        };
    }


    @Bean
    public ApplicationRunner loadReservationData(ReservationDao reservationDao) {

        List<Reservation> RESERVATIONS = Arrays.asList(
                new Reservation
                        ("2022-07-21T12:30",1, Reservation.StatusType.PENDING, "Joey", "02"),
                new Reservation
                        ("2023-07-30T10:22",14, Reservation.StatusType.ARRIVED, "David", "78"),
                new Reservation
                        ("2022-08-16T09:15",10, Reservation.StatusType.PENDING, "Sarah", "100"),
                new Reservation
                        ("2022-04-05T22:55",4, Reservation.StatusType.COMPLETED, "Angela", "6"),
                new Reservation
                        ("2022-12-12T11:30",50, Reservation.StatusType.PENDING, "Michael", "40"),
                new Reservation
                        ("2022-11-09T03:40",100, Reservation.StatusType.ARRIVED, "Jim", "52"),
                new Reservation
                        ("2022-09-05T09:30",45, Reservation.StatusType.CANCELLED, "Pam", "76"),
                new Reservation
                        ("2023-01-23T05:15",67, Reservation.StatusType.CONFIRMED, "Stanley", "55"),
                new Reservation
                        ("2023-06-24T12:50",25, Reservation.StatusType.ARRIVED, "Dwight", "33"),
                new Reservation
                        ("2022-08-30T01:30",47, Reservation.StatusType.CONFIRMED, "Cartman", "22"),
                new Reservation
                        ("2022-09-24T06:40",28, Reservation.StatusType.CANCELLED, "Jesus", "0000")

        );

        return (args) -> {

            reservationDao.saveAll(RESERVATIONS);
            // load values
            reservationDao.save(new Reservation
                    ("11-01-2022T06:40",1, Reservation.StatusType.CANCELLED, "state", "222"));
        };
    }
}

