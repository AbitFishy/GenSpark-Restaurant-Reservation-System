package com.genspark.backend;

import com.genspark.backend.Controller.ControllersTest;
import com.genspark.backend.Controller.ReservationController;
import com.genspark.backend.Controller.UserController;
import com.genspark.backend.Entity.User;
import com.genspark.backend.Repository.UserRepository;
import com.genspark.backend.Service.UserService;
import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(UserController.class)
public class UserServiceTest {

    @Autowired
    private ControllersTest authController;

    @Autowired
    private ReservationController reservationController;

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(authController).isNotNull();
        assertThat(reservationController).isNotNull();
        assertThat(userController).isNotNull();
    }

    @Test
    public void testUserServiceGetMethod() throws Exception {
        User user = new User("bowwy","password", "k@genspark.net");
        user.setId(1L);

        BDDMockito.given(userService.getUserById(1L))
                .willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\n" +
                                "    \"username\": \"bowwy\",\n" +
                                "    \"password\": \"password\",\n" +
                                "    \"email\": \"k@genspark.net\"\n" +
                                "}"));
    }

    @Test
    public void testUserServicePostMethod() throws Exception{
        User user = new User("bowwy","password", "k@genspark.net");
        user.setId(1L);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                "{\n" +
                        "    \"username\": \"bowwy\",\n" +
                        "    \"password\": \"password\",\n" +
                        "    \"email\": \"k@genspark.net\"\n" +
                        "}",
                header,
                HttpStatus.OK
        );

        BDDMockito.given(userService.addUser(user))
                        .willReturn(responseEntity);

        mockMvc.perform(MockMvcRequestBuilders.
                        post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }


}