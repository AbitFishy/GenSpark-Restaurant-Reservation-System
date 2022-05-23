package com.genspark.backend.Dao;

import com.genspark.backend.Entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountDao extends JpaRepository<UserAccount, Long> {
}
