package com.genspark.backend.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genspark.backend.Entity.Reservation;
import com.genspark.backend.Entity.User;
import com.genspark.backend.Repository.ReservationRepository;
import com.genspark.backend.Repository.RoleRepository;
import com.genspark.backend.Repository.UserRepository;
import com.genspark.backend.Service.*;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest({UserController.class, ReservationController.class, ControllersTest.class})
public class ControllersTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserServiceImpl userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    ReservationRepository reservationRepository;

    @MockBean
    ReservationServiceImpl reservationService;

    @MockBean
    RoleRepository roleRepository;

    @MockBean
    EmailServiceImpl emailService;

    User user_1 = new User("bowwy", "k@genspark.net", "password");
    User user_2 = new User("bobby", "r@genspark.net", "password");
    User user_3 = new User("joe", "j@genspark.net", "password");
    User user_4 = new User("tk", "tk@genspark.net", "password");

    Reservation reservation1 = new Reservation
            ("2022-07-21T12:30",1,Reservation.StatusType.TYPE1, "Joey", "02");
    Reservation reservation2 = new Reservation
            ("2023-07-30T10:22",14, Reservation.StatusType.TYPE2, "David", "78");
    Reservation reservation3 = new Reservation
            ("2022-08-16T09:15",10, Reservation.StatusType.TYPE3, "Sarah", "100");
    Reservation reservation4 = new Reservation
            ("2022-04-05T22:55",4, Reservation.StatusType.TYPE4, "Angela", "6");

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> records = new ArrayList<>(Arrays.asList(user_1, user_2, user_3, user_4));

        Mockito.when(userRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("joe")));
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
                .andExpect(status()
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
                .andExpect(status().isCreated());

    }


}
