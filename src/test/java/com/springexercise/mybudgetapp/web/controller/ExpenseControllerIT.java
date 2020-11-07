package com.springexercise.mybudgetapp.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerIT extends BaseIT {
    @Test
    void getAllExpenses() throws Exception {
        mockMvc.perform(get("/api/v1/category/1/expenses").with(httpBasic("user", "spring")))
                .andExpect(status().isOk());
    }
}
