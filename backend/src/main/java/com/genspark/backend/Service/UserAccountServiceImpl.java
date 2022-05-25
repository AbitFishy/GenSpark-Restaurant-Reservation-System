package com.genspark.backend.Service;

import com.genspark.backend.Dao.UserAccountDao;
import com.genspark.backend.Entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountDao userAccountDao;

    @PersistenceContext
    private EntityManager em;

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
    public UserAccount addUserAccount(String primaryName, String phoneNumber, String clearPassword, String email) {
        var user = new UserAccount(0, primaryName, phoneNumber, this.hashNewPassword(clearPassword), email);
        return  userAccountDao.saveAndFlush(user);
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
    public UserAccount authenticateUserAccount(String username, String clearTextPassword) {
        UserAccount validAuthAccount=  null;
        UserAccount toAuthenticate = new UserAccount();
        toAuthenticate.setEmail(username);
        toAuthenticate.setPassword(clearTextPassword);

/*        Query q = em.createNamedQuery("findUserAccountByEmail");
        q.setParameter(1, toAuthenticate.getEmail());
        UserAccount fromDatabase = (UserAccount) q.getSingleResult();*/
        UserAccount fromDatabase = this.userAccountDao.findUserAccountByEmail(toAuthenticate.getEmail());


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
    public String checkPasswordComplexity(String clearTextPassword){
        if ( clearTextPassword.length() < 8){
            return "Too short";
        }
        if (!clearTextPassword.matches("\\d")){ //contains at least one number
            return "Must contain at least one number";
        }
        if (!clearTextPassword.matches("[a-z]]")) {//contains at least one lowercase letter
            return "Must contain at least one lowercase letter";
        }
        if (!clearTextPassword.matches("[A-Z]")) { //contains at least one uppercase letter
            return "Must contain at least one uppercase letter";
        }
        return "True";
    }
    public String login(UserAccount userAccount) {

        UserAccount u = this.userAccountDao.findUserAccountByEmail(userAccount.getEmail());

        if (u != null) {
            return String.valueOf(u.getUserId());
        }

        return "-1";
    }
}
