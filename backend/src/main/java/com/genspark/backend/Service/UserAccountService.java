package com.genspark.backend.Service;

import com.genspark.backend.Entity.UserAccount;

import java.util.List;

public interface UserAccountService {
    List<UserAccount> getAllUserAccount();
    UserAccount getUserAccountById(Long id);
    UserAccount addUserAccount(UserAccount userAccount);
    UserAccount updateUserAccount(UserAccount userAccount, Long userAccountID);
    String deleteUserAccountById(Long id);
    String login(UserAccount userAccount);
    UserAccount updateTwoFactorAuth(UserAccount userAccount, Long userAccountID);
}
