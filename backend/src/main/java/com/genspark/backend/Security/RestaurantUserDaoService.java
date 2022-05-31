package com.genspark.backend.Security;

import com.genspark.backend.Entity.UserAccount;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.genspark.backend.Security.ApplicationUserRole.ADMIN;


public class RestaurantUserDaoService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RestaurantUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserAccount> selectApplicationUserByUserName(String username) {
        return Optional.empty();
    }

    private List<UserAccount> getApplicationUsers(){
        List<UserAccount> applicationUsers = Lists.newArrayList(
                new UserAccount(
                        "taekim",
                        "1",
                        passwordEncoder.encode("password"),
                        "taekim@genspark.net",
                        ADMIN,
                        ADMIN.getGrantedAuthorities(),
                        true
                ),
                new UserAccount(
                        "josephtharpe",
                        "2",
                        passwordEncoder.encode("password"),
                        "josephtharpe@genspark.net",
                        ADMIN,
                        ADMIN.getGrantedAuthorities(),
                        true
                ),
                new UserAccount(
                        "robertkowalczyk",
                        "3",
                        passwordEncoder.encode("password"),
                        "robertkowalczyk@genspark.net",
                        ADMIN,
                        ADMIN.getGrantedAuthorities(),
                        true
                ),
                new UserAccount(
                        "kevinlin",
                        "4",
                        passwordEncoder.encode("password"),
                        "kevinlin@genspark.net",
                        ADMIN,
                        ADMIN.getGrantedAuthorities(),
                        true
                )
        );
        return applicationUsers;
    }
}

