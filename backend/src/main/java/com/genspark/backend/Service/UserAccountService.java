package com.genspark.backend.Service;

import com.genspark.backend.Entity.Reservation;
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
    String register(UserAccount userAccount);
    String login(UserAccount userAccount);
    UserAccount authenticateUserAccount(String username, String clearTextPassword);
    String hashNewPassword(String clearTextPassword);
    UserAccount updateTwoFactorAuth(UserAccount userAccount, Long userAccountID);
    String checkPasswordComplexity(String clearTextPassword);

    boolean checkEmailForAtSign(String email);

    boolean checkNumber(String phoneNumber);

    UserAccount getNewGuestAccount(String name, String phone);
}
