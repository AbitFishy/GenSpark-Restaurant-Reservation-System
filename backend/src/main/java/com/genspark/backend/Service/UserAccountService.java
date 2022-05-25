package com.genspark.backend.Service;

import com.genspark.backend.Entity.UserAccount;

import java.util.List;

public interface UserAccountService {
    List<UserAccount> getAllUserAccount();
    UserAccount getUserAccountById(Long id);
    UserAccount addUserAccount(UserAccount userAccount);
    UserAccount addUserAccount(String primaryName,
                               String phoneNumber,
                               String clearPassword,
                               String email);
    UserAccount updateUserAccount(UserAccount userAccount, Long userAccountID);
    String deleteUserAccountById(Long id);
    String login(UserAccount userAccount);
    UserAccount authenticateUserAccount(String username, String clearTextPassword);
    String hashNewPassword(String clearTextPassword);
    String checkPasswordComplexity(String clearTextPassword);

}

