package com.genspark.backend.Entity;;

import com.genspark.backend.Security.ApplicationUserRole;
import org.hibernate.annotations.Filter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "user_accounts", uniqueConstraints = {
        @UniqueConstraint(name="user_email_unique", columnNames = "user_email")})

public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_number", nullable = false)
    private String userNumber;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole role;
    @ElementCollection(targetClass=GrantedAuthority.class)
    private Set<? extends GrantedAuthority> grantedAuthority;
    private boolean isAccountNotExpired;
    private boolean isAccountNotLocked;
    private boolean isCredentialsNotExpired;
    private boolean isEnabled;

    public UserAccount() {
    }

    public UserAccount(String userName, String userNumber, String password, String email, ApplicationUserRole role, Set<? extends GrantedAuthority> grantedAuthority, boolean isEnabled) {
        this.userName = userName;
        this.userNumber = userNumber;
        this.password = password;
        this.email = email;
        this.role = role;
        this.grantedAuthority = grantedAuthority;
        this.isEnabled = isEnabled;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
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
    public ApplicationUserRole getRole() {
        return role;
    }

    public void setRole(ApplicationUserRole role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthority;
    }
    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNotExpired;
    }
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
