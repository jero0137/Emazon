package com.Emazon.stock_service.Infrastructure.Input;

import com.Emazon.stock_service.Application.Dto.CategoryDto;
import com.Emazon.stock_service.Application.Handler.ICategoryHandler;
import com.Emazon.stock_service.Infrastructure.Configuration.Security.JwtConfig.JwtTokenProvider;
import com.Emazon.stock_service.TestSecurityConfig.TestSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {CategoryController.class, TestSecurityConfig.class})
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryHandler categoryHandler;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSaveCategorySuccessfully() throws Exception {
        CategoryDto categoryDto = new CategoryDto("Books", "All kinds of books");
        mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoryDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnBadRequestWhenCategoryDtoIsInvalid() throws Exception {
        CategoryDto categoryDto = new CategoryDto("", "");
        mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoryDto)))
                .andExpect(status().isBadRequest());
    }
}