package com.genspark.backend.Security;

import com.genspark.backend.Entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;
    @Bean
    public PasswordConfig getBCrpytPassword(){
        return new PasswordConfig();
    }

    @Override
    @Bean // Adding students
    protected UserDetailsService userDetailsService() {
        UserDetails josephTharpeUser = User.builder()
                .username("josephtharpe")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.DEV.name())
                .build();
        UserDetails taeKimUser = User.builder()
                .username("taekim")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.DEV.name())
                .build();
        UserDetails robertKowalczykUser = User.builder()
                .username("robertkowalczyk")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.DEV.name())
                .build();
        UserDetails jatinPatelUser = User.builder()
                .username("jatinpatel")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.DEV.name())
                .build();
        UserDetails kevinLinUser = User.builder()
                .username("kevinlin")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.DEV.name())
                .build();

        return new InMemoryUserDetailsManager(
                josephTharpeUser,
                taeKimUser,
                robertKowalczykUser,
                jatinPatelUser,
                kevinLinUser
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // WhiteListing URL's below
                .antMatchers("/","index","/css/*","/js*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

}
