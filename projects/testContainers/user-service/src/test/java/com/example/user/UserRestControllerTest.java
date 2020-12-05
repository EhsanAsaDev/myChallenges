package com.example.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

/**
 * @author Ehsan Sh
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class UserRestControllerTest {


    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void userShouldGetById() throws Exception {

        Mockito
                .when(userRepository.findById(1L))
                .thenReturn(Optional.of(new User(1L, "Ehsan", "Shakeri", "EhsanAsaDev@hotmail.com", "@EhsanAsaDev")));

        mockMvc
                .perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("@.id").value(1L))
                .andReturn();

    }

    @Test
    public void userShouldBeSaved() throws Exception {

        Mockito
                .when(userRepository.save(new User(null, "Ehsan", "Shakeri", "EhsanAsaDev@hotmail.com", "@EhsanAsaDev")))
                .thenReturn(new User(1L, "Ehsan", "Shakeri", "EhsanAsaDev@hotmail.com", "@EhsanAsaDev"));

        mockMvc
                .perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new User(null, "Ehsan", "Shakeri", "EhsanAsaDev@hotmail.com", "@EhsanAsaDev"))))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("@.id").isNotEmpty())
                .andReturn();

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}   
