package com.genspark.backend.Service;

import com.genspark.backend.Entity.User;
import com.genspark.backend.Repository.ReservationRepository;
import com.genspark.backend.Repository.UserRepository;
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
public class UserServiceImpl implements UserService {

    final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        if (id < 0){
            logger.info("ID out of range: " + id);
        }

        Optional<User> a = this.userRepository.findById(id);
        if (a.isPresent())
        {
            return a.get();
        } else {
            logger.warn("Account not found for id : " + id);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> addUser(User user) {
        var res = validateUserAccount(user);
        if ( res.compareTo("") == 0 || res.compareTo("Guest") == 0 ){
            logger.warn("Trying to add Invalid user");
            return null;
        }
        logger.trace("Added user: " + this.userRepository.save(user));
        return ResponseEntity.ok("valid");
    }

    @Override
    public User updateUser(User user, Long userID) {
        if (user == null){
            logger.warn("UpdateUserAccount with NULL userAccount");
            return null;
        }
        if (userID < 0){
            logger.warn("ID out of Range: " + userID);
            return null;
        }

        Optional<User> o = this.userRepository.findById(userID);

        if(o.isEmpty())
        {
            logger.warn("user with id: " + userID + " not found");
            return null;
        }

        User userUpdated = o.get();

        if(user.getUsername() != null) {
            userUpdated.setUsername(user.getUsername());
        }
        if(user.getPassword() != null) {
            userUpdated.setPassword(user.getPassword());
        }
        if(user.getEmail() != null) {
            userUpdated.setEmail(user.getEmail());
        }

        return this.userRepository.save(userUpdated);
    }

    @Override
    public String deleteUserById(Long id) {

        if (id < 0){
            logger.warn("ID out of Range: " + id);
            return "ID out of range";
        }

        this.userRepository.deleteById(id);
        logger.trace("Deleted User ID: " + id);

        return "Deleted Successfully";
    }

/*    @Override
    public User login(User user) {
        if (user == null){
            logger.warn("NULL userAccount login attempt");
            return null;
        }

        User r = null;

        User u = this.userRepository.findUserByEmail(user.getEmail());

        if (u != null) {
            r = u;
        }

        return r;
    }*/

    @Override
    public List<User> getAllUser(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<User> pagedResult = userRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public User getUserByEmail(String email) {
        if (email == null){
            return null;
        }
        return userRepository.findUserByEmail(email);
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
            return userRepository.findUserByEmail(email) == null;
        }
        return false;
    }

    @Override
    public String validateUserAccount(User user){
        if (user == null){
            logger.warn("Trying to validate NULL useraccount");
            return "Trying to validate NULL useraccount";
        }

        if (user.isGuestAccount()){
            return "Guest";
        }
        if (!checkNumber(user.getPhoneNumber())) {
            String str = "Invalid Phone Number: " + user.getPhoneNumber();
            logger.info(str);
            return str;
        }

        if(!checkEmailForAtSign(user.getEmail())) {
           String str = "Invalid Email: " + user.getEmail();
           logger.info(str);
           return str;
        }

        {
            var res = checkPasswordComplexity(user.getPassword());
            if (!"True".equals(res)) {
                String str = "Password Lacks Complexity: " + res;
                logger.info(str);
                return str;
            }
        }
        return "";
    }
}
