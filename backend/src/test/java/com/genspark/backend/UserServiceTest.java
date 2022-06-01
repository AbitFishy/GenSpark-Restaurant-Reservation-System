package com.genspark.backend;

import com.genspark.backend.Controller.UserController;
import com.genspark.backend.Entity.User;
import com.genspark.backend.Entity.UserAccount;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.swing.text.IconView;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Test
    public void testUserServiceGetMethod() throws Exception {
        User user = new User("bowwy","password", "k@genspark.net");
        user.setId(1L);

        BDDMockito.given(userService.getUserById(1L))
                .willReturn(user);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/1"))
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