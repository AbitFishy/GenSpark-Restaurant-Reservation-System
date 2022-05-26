package com.genspark.backend.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.stream.Stream;

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
    private String dateTime;

    @Column(nullable = false)
    private int numberOfGuests;

    @Column(name = "status")
    private StatusType type;

    private String resName;

    private String resNumber;

    public enum StatusType {
        TYPE1("PENDING"), TYPE2("CONFIRMED"),
        TYPE3("ARRIVED"), TYPE4("CANCELLED"), TYPE5("COMPLETED");

        private final String code;

        StatusType(String code){
            this.code = code;
        }

        @JsonCreator
        public static StatusType decode(final String code) {
            return Stream.of(StatusType.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
        }

        @JsonValue
        public String getCode() {
            return code;
        }
    }

//    @OneToOne
//    @MapsId
//    private UserAccount userAccount;

}
