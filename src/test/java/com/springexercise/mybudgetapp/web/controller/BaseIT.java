package com.springexercise.mybudgetapp.web.controller;

import com.springexercise.mybudgetapp.service.CategoryService;
import com.springexercise.mybudgetapp.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public class BaseIT {

    @Autowired
    WebApplicationContext webac;

    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    @MockBean
    ExpenseService expenseService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webac)
                .apply(springSecurity())
                .build();
    }


}
