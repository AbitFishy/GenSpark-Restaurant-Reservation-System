package com.genspark.backend.Service;

import com.genspark.backend.Entity.UserAccount;
import org.springframework.web.bind.annotation.RequestBody;

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
    UserAccount updateTwoFactorAuth(UserAccount userAccount, Long userAccountID);
    UserAccount login(UserAccount userAccount);
}
