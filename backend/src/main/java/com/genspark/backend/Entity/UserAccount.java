package com.genspark.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_userAccounts", uniqueConstraints = {
        @UniqueConstraint(name="user_email_unique", columnNames = "user_email")
})
@NamedQueries({
        @NamedQuery(name = "UserAccount.findUserAccountByEmail",
            query ="SELECT u FROM UserAccount u WHERE u.email = ?1")
})
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "primaryName", nullable = false)
    private String primaryName;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false)
    private String email;

}
