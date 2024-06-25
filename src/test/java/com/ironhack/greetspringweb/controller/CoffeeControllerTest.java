package com.ironhack.greetspringweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.greetspringweb.model.Coffee;
import com.ironhack.greetspringweb.model.CoffeeRequest;
import com.ironhack.greetspringweb.service.CoffeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoffeeController.class)
public class CoffeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoffeeService coffeeService;

    @Autowired
    private ObjectMapper objectMapper;


//    https://jsonpath.com/

    @Test
    public void testCreateCoffee() throws Exception {
        CoffeeRequest coffeeRequest = new CoffeeRequest("Espresso");
        Coffee createdCoffee = new Coffee(1L, ZonedDateTime.now(), "Espresso");

        when(coffeeService.createCoffee(any(CoffeeRequest.class))).thenReturn(createdCoffee);

        mockMvc.perform(post("/coffee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coffeeRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Espresso"));
    }

    @Test
    public void testReturnAllCoffees() throws Exception {
        List<Coffee> coffees = Arrays.asList(
                new Coffee(1L, ZonedDateTime.now(), "Espresso"),
                new Coffee(2L, ZonedDateTime.now(), "Latte")
        );

        when(coffeeService.returnAllCoffees()).thenReturn(coffees);

        mockMvc.perform(get("/coffee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Espresso"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Latte"));
    }
}