package com.genspark.backend.Dao;

import com.genspark.backend.Entity.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAccountDao extends JpaRepository<UserAccount, Long> {

    Logger logger = LoggerFactory.getLogger(UserAccountDao.class);

    @Query("SELECT u FROM UserAccount u WHERE u.email = ?1")
    UserAccount findUserAccountByEmail(String email);
}
