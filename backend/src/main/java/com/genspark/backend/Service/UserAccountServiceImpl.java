package com.genspark.backend.Service;

import com.genspark.backend.Dao.UserAccountDao;
import com.genspark.backend.Entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountDao userAccountDao;

    @Override
    public List<UserAccount> getAllUserAccount() {
        return this.userAccountDao.findAll();
    }

    @Override
    public UserAccount getUserAccountById(Long id) {

        Optional<UserAccount> a = this.userAccountDao.findById(id);

        UserAccount userAccount = null;

        if (a.isPresent())
        {
            userAccount = a.get();
        } else {
            throw new RuntimeException("Account not found for id : " + id);
        }
        return userAccount;
    }

    @Override
    public UserAccount addUserAccount(UserAccount userAccount) {
        return this.userAccountDao.save(userAccount);
    }

    @Override
    public UserAccount updateUserAccount(UserAccount userAccount, Long userAccountID) {

        UserAccount u;

        Optional<UserAccount> o = userAccountDao.findById(userAccountID);

        u = o.orElse(userAccount);

        return this.userAccountDao.save(u);
    }

    @Override
    public String deleteUserAccountById(Long id) {

        this.userAccountDao.deleteById(id);

        return "Deleted Successfully";
    }

    @Override
    public String register(UserAccount userAccount) {
        return null;
    }

    @Override
    public UserAccount authenticateUserAccount(String username, String clearTextPassword) {
        UserAccount validAuthAccount=  null;
        UserAccount toAuthenticate = new UserAccount();
        toAuthenticate.setEmail(username);
        toAuthenticate.setPassword(clearTextPassword);

        UserAccount fromDatabase = this.userAccountDao.findAccountByUsername(toAuthenticate.getEmail());

        if (fromDatabase != null && BCrypt.checkpw(toAuthenticate.getPassword(), fromDatabase.getPassword())){
            validAuthAccount = fromDatabase;
        }
        return validAuthAccount;
    }

    SecureRandom secureRandom = new SecureRandom();
    @Override
    public String hashNewPassword(String clearTextPassword){
        return BCrypt.hashpw(clearTextPassword, BCrypt.gensalt(10000, secureRandom));
    }

    @Override
    public boolean checkPasswordComplexity(String clearTextPassword){
        if ( clearTextPassword.length() < 8){
            return false;
        }
        if (!clearTextPassword.matches("\\d")){ //contains at least one number
            return false;
        }
        if (!clearTextPassword.matches("[a-z]]")) {//contains at least one lowercase letter
            return false;
        }
        if (!clearTextPassword.matches("[A-Z]")) { //contains at least one uppercase letter
            return false;
        }
        return true;
    }
    public UserAccount login(UserAccount userAccount) {

        UserAccount r = null;

        UserAccount u = this.userAccountDao.findUserAccountByEmail(userAccount.getEmail());

        if (u != null) {
            r = u;
        }

        return r;
    }
}
