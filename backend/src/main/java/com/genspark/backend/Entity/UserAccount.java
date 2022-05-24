package com.genspark.backend.Entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_userAccounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "primaryName", nullable = false)
    private String primaryName;
    private String secondaryName1;
    private String secondaryName2;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    public UserAccount() {
    }

    public UserAccount(String primaryName,
                       String secondaryName1,
                       String secondaryName2,
                       String phoneNumber,
                       String password,
                       String email) {
        this.primaryName = primaryName;
        this.secondaryName1 = secondaryName1;
        this.secondaryName2 = secondaryName2;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getSecondaryName1() {
        return secondaryName1;
    }

    public void setSecondaryName1(String secondaryName1) {
        this.secondaryName1 = secondaryName1;
    }

    public String getSecondaryName2() {
        return secondaryName2;
    }

    public void setSecondaryName2(String secondaryName2) {
        this.secondaryName2 = secondaryName2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", primaryName='" + primaryName + '\'' +
                ", secondaryName1='" + secondaryName1 + '\'' +
                ", secondaryName2='" + secondaryName2 + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}