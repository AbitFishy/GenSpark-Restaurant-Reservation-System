package com.genspark.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_userAccounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "primaryName", nullable = false)
    private String primaryName;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", primaryName='" + primaryName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
