package com.genspark.backend.Service;

import com.genspark.backend.Entity.UserAccount;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserAccountService {
    List<UserAccount> getAllUserAccount();
    UserAccount getUserAccountById(Long id);
    ResponseEntity<String> addUserAccount(UserAccount userAccount);
    UserAccount updateUserAccount(UserAccount userAccount, Long userAccountID);
    String deleteUserAccountById(Long id);
    UserAccount login(UserAccount userAccount);
    List<UserAccount> getAllUserAccount(Integer pageNo, Integer pageSize, String sortBy);
}
