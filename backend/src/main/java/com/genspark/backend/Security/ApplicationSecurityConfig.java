package com.genspark.backend.Security;

import com.genspark.backend.Controller.Controller;
import com.genspark.backend.Dao.UserAccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    final Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    private UserAccountDao userAccountDao;

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
                .roles(ApplicationUserRole.ADMIN.name())
                .build();
        UserDetails taeKimUser = User.builder()
                .username("taekim")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();
        UserDetails robertKowalczykUser = User.builder()
                .username("robertkowalczyk")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();
        UserDetails kevinLinUser = User.builder()
                .username("kevinlin")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();
        // need a way to add a new user to be built with their usernames/pw/roles
        /*UserAccount newUser = User.builder()
                .username()
                .password()
                .roles()
                .build();*/

        var security = new InMemoryUserDetailsManager(
                josephTharpeUser,
                taeKimUser,
                robertKowalczykUser,
                kevinLinUser
        );

        var userList = userAccountDao.findAll();
        for (var user : userList){
            UserDetails details = User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(ApplicationUserRole.USER.name())
                    .build();
            security.createUser(details);
        }


        return security;
    }
/*
    AuthenticationManager userAuthenticationManager() {
        return authentication -> {
            if (isUser(authentication)) {
                return new UsernamePasswordAuthenticationToken(*//*credentials*//*);
            }
            throw new UsernameNotFoundException(*//*principal name*//*);
        };
    }
    AuthenticationManager employAuthenticationManager() {
        return authentication -> {
            if (isEmploy(authentication)) {
                return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials(),authentication.getAuthorities());
            }
            throw new UsernameNotFoundException(authentication.getPrincipal());
        };
    }
    AuthenticationManager adminAuthenticationManager() {
        return authentication -> {
            if (isAdmin(authentication)) {
                return new UsernamePasswordAuthenticationToken(*//*credentials*//*);
            }
            throw new UsernameNotFoundException(*//*principal name*//*);
        };
    }

    AuthenticationManagerResolver<HttpServletRequest> resolver() {
        return request -> {
            if (request.getPathInfo().startsWith("/api/empl")) {
                return employAuthenticationManager();
            }
            if (request.getPathInfo().startsWith("/api/admin")){
                return adminAuthenticationManager()
            }
            return userAuthenticationManager();
        };
    }

    private AuthenticationFilter authenticationFilter() {
        AuthenticationFilter filter = new AuthenticationFilter(
                resolver(), authenticationConverter());
        filter.setSuccessHandler((request, response, auth) -> {});
        return filter;
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // WhiteListing URL's below
                .antMatchers("/","index","/css/*","/js*","/login*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
/*                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()*/
                .httpBasic();
    }

    @Autowired
    RestaurantUserDetailService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }


}
