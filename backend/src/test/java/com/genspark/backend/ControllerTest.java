package com.genspark.backend;

import com.genspark.backend.Controller.AuthController;
import com.genspark.backend.Controller.ReservationController;
import com.genspark.backend.Controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ControllerTest {

    @Autowired
    private AuthController authController;

    @Autowired
    private ReservationController reservationController;

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(authController).isNotNull();
        assertThat(reservationController).isNotNull();
        assertThat(userController).isNotNull();
    }
}
