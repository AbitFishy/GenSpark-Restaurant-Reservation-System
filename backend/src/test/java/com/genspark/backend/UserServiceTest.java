package com.genspark.backend;

import com.genspark.backend.Controller.UserController;
import com.genspark.backend.Entity.User;
import com.genspark.backend.Repository.UserRepository;
import com.genspark.backend.Service.UserService;
import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

}