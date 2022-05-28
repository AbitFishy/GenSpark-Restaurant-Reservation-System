package com.genspark.backend.Security;

import com.genspark.backend.Controller.Controller;
import com.genspark.backend.Dao.UserAccountDao;
import com.genspark.backend.Entity.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantUserDetailService  implements UserDetailsService {
    @Autowired
    UserAccountDao userAccountDao;
    final Logger logger = LoggerFactory.getLogger(Controller.class);
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserAccount customer = userAccountDao.findUserAccountByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException(email);
        }
        UserDetails user = User.builder().username(customer.getEmail())
                .password(customer.getPassword())
                .authorities("USER").build();
        return user;
    }
}
