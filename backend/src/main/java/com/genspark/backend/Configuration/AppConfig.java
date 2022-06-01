
package com.genspark.backend.Configuration;

import com.genspark.backend.Repository.ReservationRepository;
import com.genspark.backend.Repository.RoleRepository;
import com.genspark.backend.Entity.ERole;
import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.User;
import com.genspark.backend.Entity.Role;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner loadRoleData(RoleRepository roleRepository){
        List<Role> role = Arrays.asList(
                new Role(ERole.ROLE_USER),
                new Role(ERole.ROLE_MODERATOR),
                new Role(ERole.ROLE_ADMIN)
        );
        return (args) -> {
            roleRepository.saveAll(role);
        };
    }

    @Bean
    public CommandLineRunner loadUserData(UserRepository userRepository){
        List<User> USERACCOUNT = Arrays.asList(
                new User
                        ("ElonMusk","1", "password", "Elon@Tesla.com"),
                new User
                        ("BillGates","2", "password", "bill@microsoft.com"),
                new User
                        ("UnderTaker","3", "password", "taker@wwe.com"),
                new User
                        ("JoeBiden","4", "password", "biden@whitehouse.gov"),
                new User
                        ("Megatron","5", "password", "Deceptacons@space.com"),
                new User
                        ("Cthulu","6", "password", "cult@deepsea.com"),
                new User
                        ("FishMan","7", "password", "joe@fish.com"),
                new User
                        ("Naruto","7", "password", "ninja@fireofwill.com"),
                new User
                        ("Luffy","9", "password", "captain@onepiece.com"),
                new User
                        ("QueenElizabeth","10", "password", "queen@royal.com"),
                new User
                        ("Kanye","11", "password", "west@yeezy.com")
        );
        return (args) -> {
            userRepository.saveAll(USERACCOUNT);
            userRepository.save(new User
                    ("Bob","1", "password", "bob@google.com"));
        };
    }


    @Bean
    public ApplicationRunner loadReservationData(ReservationRepository reservationRepository) {

        List<Reservation> RESERVATIONS = Arrays.asList(
                new Reservation
                        ("2022-07-21T12:30:00", 1, Reservation.StatusType.TYPE1, "Joey", "02", "joey@fake.com"),
                new Reservation
                        ("2023-07-30T10:22:00", 14, Reservation.StatusType.TYPE2, "David", "78", "Sarah@fake.com"),
                new Reservation
                        ("2022-08-16T09:15:00", 10, Reservation.StatusType.TYPE3, "Sarah", "100", "Sarah@fake.com"),
                new Reservation
                        ("2022-04-05T22:55:00", 4, Reservation.StatusType.TYPE4, "Angela", "6", "Angela@fake.com"),
                new Reservation
                        ("2022-12-12T11:30:00", 50, Reservation.StatusType.TYPE5, "Michael", "40", "Michael@fake.com"),
                new Reservation
                        ("2022-11-09T03:40:00", 100, Reservation.StatusType.TYPE1, "Jim", "52", "Jim@fake.com"),
                new Reservation
                        ("2022-09-05T09:30:00", 45, Reservation.StatusType.TYPE2, "Pam", "76", "Pam@fake.com"),
                new Reservation
                        ("2023-01-23T05:15:00", 67, Reservation.StatusType.TYPE3, "Stanley", "55", "Stanley@fake.com"),
                new Reservation
                        ("2023-06-24T12:50:00", 25, Reservation.StatusType.TYPE4, "Dwight", "33", "v@fake.com"),
                new Reservation
                        ("2022-08-30T01:30:00", 47, Reservation.StatusType.TYPE5, "Cartman", "22", "Cartman@fake.com"),
                new Reservation
                        ("2022-09-24T06:40:00", 28, Reservation.StatusType.TYPE2, "Jesus", "0000", "Jesus@fake.com")

        );

        return (args) -> {

            reservationRepository.saveAll(RESERVATIONS);
        };
    }
}

