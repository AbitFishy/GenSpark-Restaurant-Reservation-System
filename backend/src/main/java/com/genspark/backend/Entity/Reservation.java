package com.genspark.backend.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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

    public Reservation() {
    }

    public Reservation(UserAccount reservationHolder, LocalDateTime dateTime, int numberOfGuests, Status status) {
        this.reservationHolder = reservationHolder;
        this.dateTime = dateTime;
        this.numberOfGuests = numberOfGuests;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserAccount getReservationHolder() {
        return reservationHolder;
    }

    public void setReservationHolder(UserAccount reservationHolder) {
        this.reservationHolder = reservationHolder;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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