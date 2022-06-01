package com.genspark.backend.Repository;

import com.genspark.backend.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Query("SELECT u FROM UserAccount u WHERE u.email = ?1")
    User findUserAccountByEmail(String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
