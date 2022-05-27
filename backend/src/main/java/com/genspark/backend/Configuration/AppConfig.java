/*
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

@Configuration
public class AppConfig {
    @Bean
    public CommandLineRunner loadUserData(UserAccountDao userAccountDao){
        List<UserAccount> USERACCOUNT = Arrays.asList(
                new UserAccount
                        ("ElonMusk","1", "password", "Elon@Tesla.com"),
                new UserAccount
                        ("BillGates","2", "password", "bill@microsoft.com"),
                new UserAccount
                        ("UnderTaker","3", "password", "taker@wwe.com"),
                new UserAccount
                        ("JoeBiden","4", "password", "biden@whitehouse.gov"),
                new UserAccount
                        ("Megatron","5", "password", "Deceptacons@space.com"),
                new UserAccount
                        ("Cthulu","6", "password", "cult@deepsea.com"),
                new UserAccount
                        ("FishMan","7", "password", "joe@fish.com"),
                new UserAccount
                        ("Naruto","7", "password", "ninja@fireofwill.com"),
                new UserAccount
                        ("Luffy","9", "password", "captain@onepiece.com"),
                new UserAccount
                        ("QueenElizabeth","10", "password", "queen@royal.com"),
                new UserAccount
                        ("Kanye","11", "password", "west@yeezy.com")
        );
        return (args) -> {
            userAccountDao.saveAll(USERACCOUNT);
            userAccountDao.save(new UserAccount
                    ("Bob","1", "password", "bob@google.com"));
        };
    }


    @Bean
    public ApplicationRunner loadReservationData(ReservationDao reservationDao) {

        List<Reservation> RESERVATIONS = Arrays.asList(
                new Reservation
                        ("1-12-2022T12:30",1, Reservation.StatusType.TYPE1, "Joey", "02"),
                new Reservation
                        ("2-12-2022T10:22",14, Reservation.StatusType.TYPE2, "David", "78"),
                new Reservation
                        ("3-13-2022T09:15",10, Reservation.StatusType.TYPE3, "Sarah", "100"),
                new Reservation
                        ("4-13-2022T22:55",4, Reservation.StatusType.TYPE4, "Angela", "6"),
                new Reservation
                        ("5-15-2022T11:30",50, Reservation.StatusType.TYPE5, "Michael", "40"),
                new Reservation
                        ("6-17-2022T03:40",100, Reservation.StatusType.TYPE1, "Jim", "52"),
                new Reservation
                        ("7-22-2022T09:30",45, Reservation.StatusType.TYPE2, "Pam", "76"),
                new Reservation
                        ("8-06-2022T05:15",67, Reservation.StatusType.TYPE3, "Stanley", "55"),
                new Reservation
                        ("9-23-2022T12:50",25, Reservation.StatusType.TYPE4, "Dwight", "33"),
                new Reservation
                        ("10-08-2022T01:30",47, Reservation.StatusType.TYPE5, "Cartman", "22"),
                new Reservation
                        ("11-01-2022T06:40",28, Reservation.StatusType.TYPE2, "Jesus", "0000")

        );

        return (args) -> {

            reservationDao.saveAll(RESERVATIONS);
            // load values
            reservationDao.save(new Reservation
                    ("11-01-2022T06:40",1, Reservation.StatusType.TYPE1, "state", "222"));
        };
    }
}
*/
