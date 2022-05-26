package com.genspark.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false)
    private long reservationId;


    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private int numberOfGuests;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @MapsId
    private UserAccount userAccount;

}
