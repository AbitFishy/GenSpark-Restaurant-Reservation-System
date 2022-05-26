package com.genspark.backend.Security;

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
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.security.config.Customizer.withDefaults;

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
        UserDetails jatinPatelUser = User.builder()
                .username("jatinpatel")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();
        UserDetails kevinLinUser = User.builder()
                .username("kevinlin")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.ADMIN.name())
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
                .antMatchers("/","index","/css/*","/js*","/api/","/api/login*","/api/user/login*"
                , "/login*" )
                .permitAll()
                .antMatchers("/api/users/*").hasRole("USER")
                .antMatchers("/api/admin*/").hasRole("ADMIN")
                //.antMatchers("/api/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/login")
                .successForwardUrl("/success")
                .failureUrl("/fail")

                .and()
                .logout().deleteCookies("JSESSIONID")
                .and().rememberMe()
                .alwaysRemember(true)
                .key("This is a key") //TODO
                        .tokenValiditySeconds(86400)
                .and()
                .httpBasic(withDefaults())
                ;
    }

/*    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices =
                new SpringSessionRememberMeServices();
        // optionally customize
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }*/

}
