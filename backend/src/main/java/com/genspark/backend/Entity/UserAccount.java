package com.genspark.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userAccounts", uniqueConstraints = {
        @UniqueConstraint(name="user_email_unique", columnNames = "user_email")
})
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "primaryName", nullable = false)
    private String userName;

    @Column(name = "phoneNumber", nullable = false)
    private String userNumber;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Reservation res;

}
