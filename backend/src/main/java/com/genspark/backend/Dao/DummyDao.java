package com.genspark.backend.Dao;

import java.util.Optional;

public interface DummyDao {

    Optional<DummyDao> selectApplicationUserByUserName(String username);
}

