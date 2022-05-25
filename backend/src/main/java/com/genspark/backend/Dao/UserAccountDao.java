package com.genspark.backend.Dao;

import com.genspark.backend.Entity.UserAccount;
import org.hibernate.annotations.NamedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAccountDao extends JpaRepository<UserAccount, Long> {

    final Logger logger = LoggerFactory.getLogger(UserAccountDao.class);


    UserAccount findUserAccountByEmail(String email);
}
