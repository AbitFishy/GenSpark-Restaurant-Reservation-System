package com.genspark.backend.Service;

import com.genspark.backend.Entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUserById(Long id);
    ResponseEntity<String> addUser(User userAccount);
    User updateUser(User user, Long userID);
    String deleteUserById(Long id);
//    User login(User user);
    List<User> getAllUser(Integer pageNo, Integer pageSize, String sortBy);
    User getUserByEmail(String email);
    String checkPasswordComplexity(String clearTextPassword);
    boolean checkEmailForAtSign(String email);
    boolean checkNumber(String phoneNumber);
    boolean checkIsDuplicateEmail(String email);
    String validateUserAccount(User user);
}
