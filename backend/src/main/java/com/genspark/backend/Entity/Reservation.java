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
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @OneToOne
    @MapsId
    private UserAccount reservationHolder;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(nullable = false)
    private int numberOfGuests;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        Pending, Confirmed, Arrived, Cancelled, Completed
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationHolder=" + reservationHolder +
                ", dateTime=" + dateTime +
                ", numberOfGuests=" + numberOfGuests +
                ", status=" + status +
                '}';
    }
}