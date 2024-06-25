package com.ironhack.greetspringweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.greetspringweb.model.CoffeeRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RequiredArgsConstructor
class GreetControllerTest {

    private final WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }



    @Test
    void test_greet_english() throws Exception {
        // Arrange
        String name = "John";
        String language = "en";
        // Act
        mockMvc.perform(get("/greet?name=" + name + "&language=" + language))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString().equals("Hello John");

        // Assert
    }

    @Test
    void test_greet_spanish() throws Exception {
        // Arrange
        String name = "John";
        String language = "jeronimo";
        // Act
        mockMvc.perform(get("/greet?name=" + name + "&language=" + language))
                .andExpect(status().isBadRequest());

        // Assert
    }



    @Test
    void createNewCoffee() throws Exception {
        CoffeeRequest coffeeRequest = new CoffeeRequest("Flat White");

        var body = objectMapper.writeValueAsString(coffeeRequest);
        System.out.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/coffee")
                .content(body)
                .contentType("application/json"))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Flat White"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1"));
    }

}