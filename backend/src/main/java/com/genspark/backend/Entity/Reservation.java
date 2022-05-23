package com.genspark.backend.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    private String reservationHolder;
    @Column(nullable = false)
    private LocalDate timeDate;
    @Column(nullable = false)
    private int numberOfGuests;
    private String status;

    public Reservation() {
    }

    public Reservation(String reservationHolder, LocalDate timeDate, int numberOfGuests, String status) {
        this.reservationHolder = reservationHolder;
        this.timeDate = timeDate;
        this.numberOfGuests = numberOfGuests;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReservationHolder() {
        return reservationHolder;
    }

    public void setReservationHolder(String reservationHolder) {
        this.reservationHolder = reservationHolder;
    }

    public LocalDate getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(LocalDate timeDate) {
        this.timeDate = timeDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationHolder='" + reservationHolder + '\'' +
                ", timeDate=" + timeDate +
                ", numberOfGuests=" + numberOfGuests +
                ", status='" + status + '\'' +
                '}';
    }
}