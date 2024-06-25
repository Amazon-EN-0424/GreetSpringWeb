package com.ironhack.greetspringweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.greetspringweb.service.GreetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetController.class)
class GreetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetService greetService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        when(greetService.greet(anyString())).thenReturn("Hello ");
        when(greetService.greet(anyString(), anyString())).thenReturn("Greeting ");
    }

    @Test
    void test_greet_english() throws Exception {
        when(greetService.greet("John", "en")).thenReturn("Hello John");

        mockMvc.perform(get("/greet")
                        .param("name", "John")
                        .param("language", "en"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello John"));
    }

    @Test
    void test_greet_spanish() throws Exception {
        when(greetService.greet("John", "es")).thenReturn("Hola John");

        mockMvc.perform(get("/greet")
                        .param("name", "John")
                        .param("language", "es"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola John"));
    }

    @Test
    void test_greet_default_language() throws Exception {
        when(greetService.greet("John")).thenReturn("Hello John");

        mockMvc.perform(get("/greet")
                        .param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello John"));
    }

    @Test
    void test_greet_unsupported_language() throws Exception {
        when(greetService.greet("John", "unsupported")).thenThrow(new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Language not supported"));

        mockMvc.perform(get("/greet")
                        .param("name", "John")
                        .param("language", "unsupported"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_greet_missing_name() throws Exception {
        mockMvc.perform(get("/greet"))
                .andExpect(status().isBadRequest());
    }
}