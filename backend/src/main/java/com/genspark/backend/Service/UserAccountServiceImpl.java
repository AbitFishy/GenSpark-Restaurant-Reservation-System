package com.genspark.backend.Service;

import com.genspark.backend.Dao.UserAccountDao;
import com.genspark.backend.Entity.UserAccount;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserAccountServiceImpl implements UserAccountService, UserDetailsService {
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
    public UserAccount login(UserAccount userAccount) {

        UserAccount r = null;

        UserAccount u = this.userAccountDao.findUserAccountByEmail(userAccount.getEmail());

        if (u != null) {
            r = u;
        }

        return r;
    }

    @Override
    public boolean authenticateUserAccount(String username, String clearTextPassword) {
        return false;
    }

    @Autowired
    public UserAccountServiceImpl(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserAccount customer = userAccountDao.findUserAccountByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException(email);
        }
        return userAccountDao
                .findUserAccountByEmail(email);
    }

      /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDao
                .selectApplicationUserByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username)));
    }*/
}
