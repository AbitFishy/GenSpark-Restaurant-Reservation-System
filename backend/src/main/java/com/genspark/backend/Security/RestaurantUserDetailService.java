package com.genspark.backend.Security;

import com.genspark.backend.Controller.Controller;
import com.genspark.backend.Dao.UserAccountDao;
import com.genspark.backend.Entity.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantUserDetailService  implements UserDetailsService {

    final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final UserAccountDao userAccountDao;

    @Autowired
    public RestaurantUserDetailService(UserAccountDao userAccountDao) {
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


}
