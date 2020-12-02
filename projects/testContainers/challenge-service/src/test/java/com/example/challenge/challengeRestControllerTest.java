package com.example.challenge;

import org.junit.Assert;
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
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Ehsan Sh
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class challengeRestControllerTest {

    @MockBean
    private ChallengeRepository challengeRepository ;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void challengeByIdShouldReceiveNotFound() throws Exception{

        MvcResult result = mockMvc.perform(get("/challenges/1"))
                .andExpect(status().is4xxClientError())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),"Nothing was found with this id:1");

    }

    @Test
    public void challengeByIdShouldReturnAJsonChallenge() throws Exception {

        Mockito
                .when(this.challengeRepository.findById(1L))
                .thenReturn(Optional.of(new Challenge(1L, "Ehsan's Challenge")));

        this.mockMvc.perform(get("/challenges/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("@.id").value(1L))
                .andReturn();
    }


    @Test
    public void challengesShouldReturnAllChallenges() throws Exception {

        List<Challenge> challengeList = Arrays.asList(
                new Challenge(1L, "Ehsan's Challenge"),
                new Challenge(2L, "Ehsan's Challenge"));

        Mockito.when(this.challengeRepository.findAll()).thenReturn(challengeList);

        this.mockMvc
                .perform(get("/challenges"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("@.[0].id").value(1L))
                .andExpect(jsonPath("@.[0].name").value("Ehsan's Challenge"))
                .andExpect(jsonPath("@.[1].id").value(2L));

    }
}   
