
package java.com.genspark.backend.Configuration;

import com.genspark.backend.Repository.ReservationRepository;
import com.genspark.backend.Repository.RoleRepository;
import com.genspark.backend.Entity.ERole;
import com.genspark.backend.Entity.Reservation;
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

//                new UserAccount
//                        ("BillGates","2", "password", "bill@microsoft.com"),
//                new UserAccount
//                        ("UnderTaker","3", "password", "taker@wwe.com"),
//                new UserAccount
//                        ("JoeBiden","4", "password", "biden@whitehouse.gov"),
//                new UserAccount
//                        ("Megatron","5", "password", "Deceptacons@space.com"),
//                new UserAccount
//                        ("Cthulu","6", "password", "cult@deepsea.com"),
//                new UserAccount
//                        ("FishMan","7", "password", "joe@fish.com"),
//                new UserAccount
//                        ("Naruto","7", "password", "ninja@fireofwill.com"),
//                new UserAccount
//                        ("Luffy","9", "password", "captain@onepiece.com"),
//                new UserAccount
//                        ("QueenElizabeth","10", "password", "queen@royal.com"),
//                new UserAccount
//                        ("Kanye","11", "password", "west@yeezy.com")
        );
        return (args) -> {
            roleRepository.saveAll(role);
//            userAccountDao.save(new UserAccount
//                    ("Bob","1", "password", "bob@google.com"));
        };
    }


    @Bean
    public ApplicationRunner loadReservationData(ReservationRepository reservationRepository) {

        List<Reservation> RESERVATIONS = Arrays.asList(
                new Reservation
                        ("2022-07-21T12:30",1, Reservation.StatusType.TYPE1, "Joey", "02"),
                new Reservation
                        ("2023-07-30T10:22",14, Reservation.StatusType.TYPE2, "David", "78"),
                new Reservation
                        ("2022-08-16T09:15",10, Reservation.StatusType.TYPE3, "Sarah", "100"),
                new Reservation
                        ("2022-04-05T22:55",4, Reservation.StatusType.TYPE4, "Angela", "6"),
                new Reservation
                        ("2022-12-12T11:30",50, Reservation.StatusType.TYPE5, "Michael", "40"),
                new Reservation
                        ("2022-11-09T03:40",100, Reservation.StatusType.TYPE1, "Jim", "52"),
                new Reservation
                        ("2022-09-05T09:30",45, Reservation.StatusType.TYPE2, "Pam", "76"),
                new Reservation
                        ("2023-01-23T05:15",67, Reservation.StatusType.TYPE3, "Stanley", "55"),
                new Reservation
                        ("2023-06-24T12:50",25, Reservation.StatusType.TYPE4, "Dwight", "33"),
                new Reservation
                        ("2022-08-30T01:30",47, Reservation.StatusType.TYPE5, "Cartman", "22"),
                new Reservation
                        ("2022-09-24T06:40",28, Reservation.StatusType.TYPE2, "Jesus", "0000")

        );

        return (args) -> {

            reservationRepository.saveAll(RESERVATIONS);
        };
    }
}

