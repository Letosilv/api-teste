package com.example.api_teste.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SaudacaoController.class)
public class SaudacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaudacaoEndpoint() throws Exception {
        mockMvc.perform(get("/saudacao"))
                .andExpect(status().isOk())
                .andExpect(content().string("Olá, seja bem-vindo!"));
    }
}
