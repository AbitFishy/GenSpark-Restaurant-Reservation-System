package com.genspark.backend.Service;

import com.genspark.backend.Dao.UserAccountDao;
import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.UserAccount;
import com.genspark.backend.Security.TwoFactorAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

        if (a.isPresent()) {
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
        var user = new UserAccount(primaryName, phoneNumber, this.hashNewPassword(clearPassword), email);
        return userAccountDao.saveAndFlush(user);
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
        UserAccount validAuthAccount = null;
        UserAccount toAuthenticate = new UserAccount();
        toAuthenticate.setEmail(username);
        toAuthenticate.setPassword(clearTextPassword);

/*        Query q = em.createNamedQuery("findUserAccountByEmail");
        q.setParameter(1, toAuthenticate.getEmail());
        UserAccount fromDatabase = (UserAccount) q.getSingleResult();*/
        UserAccount fromDatabase = this.userAccountDao.findUserAccountByEmail(toAuthenticate.getEmail());


        if (fromDatabase != null && BCrypt.checkpw(toAuthenticate.getPassword(), fromDatabase.getPassword())) {
            validAuthAccount = fromDatabase;
        }
        return validAuthAccount;
    }

    SecureRandom secureRandom = new SecureRandom();

    @Override
    public String hashNewPassword(String clearTextPassword) {
        return BCrypt.hashpw(clearTextPassword, BCrypt.gensalt(12, secureRandom));
    }

    @Override
    public String checkPasswordComplexity(String clearTextPassword) {
        if (clearTextPassword.length() < 8) {
            return "Too short";
        }
        if (!clearTextPassword.matches("\\d")) { //contains at least one number
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

    @Override
    public boolean checkEmailForAtSign(String email) {
        return email.matches("[a-zA-Z\\d]+@[a-zA-Z\\d]+\\.[a-zA-Z\\d]+$");
    }


    public String login(UserAccount userAccount) {

        UserAccount u = this.userAccountDao.findUserAccountByEmail(userAccount.getEmail());

        if (u != null) {
            return String.valueOf(u.getUserId());
        }

        return "-1";
    }

    @Override
    public UserAccount updateTwoFactorAuth(UserAccount userAccount, Long userAccountID) {
        TwoFactorAuth token;
        return null;
    }
    @Override
    public boolean checkNumber(String phoneNumber) {
        if (phoneNumber.length() >= 9) {
            //(\+?(1[\s+-]?))? means   '1' (preceded optionally by a '+') followed by either a space, no space, '+', '-' ; OR no '1' with any of that.
            //for country code '1'


            // validate phone numbers of format "1234567890"
            if (phoneNumber.matches("(\\+?(1[\\s+-]?))?\\d{10}")) {
                return true;
            }
            // validating phone number with -, . or spaces
            //[-\s]? separate by space '-' or no space
        /*    else if (phoneNumber.matches("(\\+?(1[\\s+-]?))?\\d{3}[-.\\s]?\\d{3}[-.\\s]?\\d{4}")) {
                return true;
            }*/
            // validating phone number with extension length from 3 to 5
            //(\s(x|(ext))\d{3,5})? checks for extension
            else if (phoneNumber.matches("(\\+?(1[\\s+-]?))?\\d{3}[-\\s]?\\d{3}[-\\s]?\\d{4}(\\s(x|(ext))[-+\\s]?\\d{3,5})?")) {
                return true;
            }
            // validating phone number where area code is in braces ()
            else if (phoneNumber.matches("(\\+?(1[\\s+-]?))?\\(\\d{3}\\)[\\s-]?\\d{3}[-\\s]?\\d{4}(\\s(x|(ext))[-+\\s]?\\d{3,5})?")) {
                return true;
            }
     /*       else if (phoneNumber.matches("(\\+?(1[\\s+-]?))?\\(\\d{3}\\) \\d{3}-\\d{4}")) {
                return true;
            }*/
            // Validation for India numbers
            else if (phoneNumber.matches("\\d{4}[-.\\s]\\d{3}[-.\\s]\\d{3}")) {
                return true;
            } else // return false if nothing matches the input
                if (phoneNumber.matches("\\(\\d{5}\\)-\\d{3}-\\d{3}")) {
                    return true;
                } else return phoneNumber.matches("\\(\\d{4}\\)-\\d{3}-\\d{3}");
        }
        return false;
    }
    private Random ran = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(0)));
    @Override
    public UserAccount getNewGuestAccount(String name, String phone){
        var guest = new UserAccount(name, phone, "aB" + ran.nextInt(), "GUESTACCOUNT"+ran.nextInt()+ "@" + ran.nextInt());
        return userAccountDao.save(guest);
    }
}
