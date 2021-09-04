package com.anytime.studymaker.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class AuthControllerTest {

    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension("custom");
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider documentationContextProvider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(documentationContextProvider))
                .build();
    }

    @Test
    void hello() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/auth").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("index"));
    }

    @Test
    void signUp() {
    }

    @Test
    void signIn() {
    }

    @Test
    void checkEmail() {
    }

    @Test
    void checkNickname() {
    }

    @Test
    void findMyPassword() {
    }

    @Test
    void getInitializationInfo() {
    }

    @Test
    void initializePassword() {
    }

    @Test
    void refreshToken() {
    }
}