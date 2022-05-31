package com.genspark.backend.Service;

import com.genspark.backend.Dao.UserAccountDao;
import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.UserAccount;
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

    @Autowired
    UserAccountDao userAccountDao;

    @Override
    public List<UserAccount> getAllUserAccount() {
        return this.userAccountDao.findAll();
    }

    @Override
    public UserAccount getUserAccountById(Long id) {

        Optional<UserAccount> a = this.userAccountDao.findById(id);

        UserAccount userAccount;

        if (a.isPresent())
        {
            userAccount = a.get();
        } else {
            throw new RuntimeException("Account not found for id : " + id);
        }
        return userAccount;
    }

    @Override
    public ResponseEntity<String> addUserAccount(UserAccount userAccount) {
        this.userAccountDao.save(userAccount);
        return ResponseEntity.ok("valid");
    }

    @Override
    public UserAccount updateUserAccount(UserAccount userAccount, Long userAccountID) {

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

        this.userAccountDao.deleteById(id);

        return "Deleted Successfully";
    }

    @Override
    public UserAccount login(UserAccount userAccount) {

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
}
