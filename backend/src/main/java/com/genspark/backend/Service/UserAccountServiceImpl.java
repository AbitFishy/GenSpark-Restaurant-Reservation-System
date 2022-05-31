package com.genspark.backend.Service;

import com.genspark.backend.Dao.ReservationDao;
import com.genspark.backend.Dao.UserAccountDao;
import com.genspark.backend.Entity.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    final Logger logger = LoggerFactory.getLogger(ReservationDao.class);

/*    public UserAccountServiceImpl(){
        var res = userAccountDao.findUserAccountByEmail(UserAccount.guestAccount.getEmail());
        UserAccount guest = UserAccount.guestAccount;
        if (res == null){
            guest = userAccountDao.saveAndFlush(UserAccount.guestAccount);
        }
        guestAccountID = guest.getUserId();
    }*/


    @Autowired
    UserAccountDao userAccountDao;

    @Override
    public List<UserAccount> getAllUserAccount() {
        return this.userAccountDao.findAll();
    }

    @Override
    public UserAccount getUserAccountById(Long id) {

        if (id < 0){
            logger.info("ID out of range: " + id);
        }

        Optional<UserAccount> a = this.userAccountDao.findById(id);

        UserAccount userAccount;

        if (a.isPresent())
        {
            return a.get();
        } else {
            logger.warn("Account not found for id : " + id);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> addUserAccount(UserAccount userAccount) {
        if (userAccount == null){
            logger.warn("Trying to save NULL UserAccount");
            return null;
        }
        this.userAccountDao.save(userAccount);
        return ResponseEntity.ok("valid");
    }

    @Override
    public UserAccount updateUserAccount(UserAccount userAccount, Long userAccountID) {
        if (userAccount == null){
            logger.warn("UpdateUserAccount with NULL userAccount");
            return null;
        }
        if (userAccountID < 0){
            logger.warn("ID out of Range: " + userAccountID);
            return null;
        }

        Optional<UserAccount> o = this.userAccountDao.findById(userAccountID);

        if(!o.isPresent())
        {
            throw new RuntimeException("user with id: " + userAccountID + " not found");
        }

        UserAccount userAccountUpdated = o.get();

        if(userAccount.getUserName() != null) {
            userAccountUpdated.setUserName(userAccount.getUserName());
        }
        if(userAccount.getUserNumber() != null) {
            userAccountUpdated.setUserNumber(userAccount.getUserNumber());
        }
        if(userAccount.getPassword() != null) {
            userAccountUpdated.setPassword(userAccount.getPassword());
        }
        if(userAccount.getEmail() != null) {
            userAccountUpdated.setEmail(userAccount.getEmail());
        }

        return this.userAccountDao.save(userAccountUpdated);
    }

    @Override
    public String deleteUserAccountById(Long id) {

        if (id < 0){
            logger.warn("ID out of Range: " + id);
            return "ID out of range";
        }

        this.userAccountDao.deleteById(id);

        return "Deleted Successfully";
    }

    @Override
    public UserAccount login(UserAccount userAccount) {
        if (userAccount == null){
            logger.warn("NULL userAccount login attempt");
            return null;
        }

        UserAccount r = null;

        UserAccount u = this.userAccountDao.findUserAccountByEmail(userAccount.getEmail());

        if (u != null) {
            r = u;
        }

        return r;
    }

    @Override
    public List<UserAccount> getAllUserAccount(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<UserAccount> pagedResult = userAccountDao.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public UserAccount getUserAccountByEmail(String email) {
        if (email == null){
            return null;
        }
        return userAccountDao.findUserAccountByEmail(email);
    }
    @Override
    public String checkPasswordComplexity(String clearTextPassword) {
        if (clearTextPassword == null){
            return "Password Required";
        }
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
        if (email != null && email.length() > 3) {
            return email.matches("[a-zA-Z\\d]+@[a-zA-Z\\d]+\\.[a-zA-Z\\d]+$");
        }
        return false;
    }
    @Override
    public boolean checkNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() >= 9) {
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
    @Override
    public boolean checkIsDuplicateEmail(String email){
        if (email != null && email.length() >3) {
            return userAccountDao.findUserAccountByEmail(email) == null;
        }
        return false;
    }

    @Override
    public String validateUserAccount(UserAccount userAccount){
        if (userAccount == null){
            logger.warn("Try to validate NULL useraccount");
            return "Try to validate NULL useraccount";
        }

        if (userAccount.isGuestAccount()){
            return "Guest";
        }
        if (!checkNumber(userAccount.getUserNumber())) {
            String str = "Invalid Phone Number: " + userAccount.getUserNumber();
            logger.info(str);
            return str;
        }

        if(!checkEmailForAtSign(userAccount.getEmail())) {
           String str = "Invalid Email: " + userAccount.getEmail();
           logger.info(str);
           return str;
        }

        {
            var res = checkPasswordComplexity(userAccount.getPassword());
            if (!"True".equals(res)) {
                String str = "Password Lacks Complexity: " + res;
                logger.info(str);
                return str;
            }
        }
        return "";
    }
}
