package com.springexercise.mybudgetapp.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.springframework.security.test.context.support.WithMockUser;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
public class CategoryControllerIT extends BaseIT {

    //@WithMockUser("user") //forcing user credentials into Spring context, not going through the authentication manager.
    @Test
    void getAllCategories() throws Exception {
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk());
    }

//    @Test
//    void getAllCategoriesWithHttpBasic() throws Exception {
//        mockMvc.perform(get("/api/v1/categories").with(httpBasic("user", "spring")))
//                .andExpect(status().isOk());
//    }

//    @Test
//    void getAllCategoriesWithHttpBasicWithExampleUser() throws Exception {
//        mockMvc.perform(get("/api/v1/categories").with(httpBasic("example", "password")))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getAllCategoriesWithHttpBasicWithScottUser() throws Exception {
//        mockMvc.perform(get("/api/v1/categories").with(httpBasic("scott", "tiger")))
//                .andExpect(status().isOk());
//    }

    @Test
    void testGetToTheRootOfTheApplication() throws Exception {
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllCategoriesPermitAll() throws Exception {
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk());
    }

    @Test
    void getCategoryById() throws Exception {
        mockMvc.perform(get("/api/v1/category/123487349"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCategory() throws Exception {
        mockMvc.perform(delete("/api/v1/category/123487349")
                .header("Api-Key", "user")
                .header("Api-Secret", "spring"))
                .andExpect(status().isOk());
    }

//    @Test
//    void deleteCategoryHttpBasic() throws Exception {
//        mockMvc.perform(delete("/api/v1/category/123487349")
//                .with(httpBasic("user", "spring")))
//                .andExpect(status().is2xxSuccessful());
//    }

    @Test
    void deleteCategoryNoAuth() throws Exception {
        mockMvc.perform(delete("/api/v1/category/123487349"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteCategoryBadCredentials() throws Exception {
        mockMvc.perform(delete("/api/v1/category/123487349")
                .header("Api-Key", "user")
                .header("Api-Secret", "springframework"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteCategoryUrl() throws Exception {
        mockMvc.perform(delete("/api/v1/category/123487349")
                .param("apiKey", "user")
                .param("apiSecret", "spring"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCategoryBadCredsUrl() throws Exception {
        mockMvc.perform(delete("/api/v1/category/123487349")
                .param("apiKey", "user")
                .param("apiSecret", "springframework"))
                .andExpect(status().isUnauthorized());
    }
}

