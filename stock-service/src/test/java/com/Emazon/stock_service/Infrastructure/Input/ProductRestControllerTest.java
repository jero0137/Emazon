package com.Emazon.stock_service.Infrastructure.Input;

import com.Emazon.stock_service.Application.Dto.ProductDto;
import com.Emazon.stock_service.Application.Dto.SupplyDto;
import com.Emazon.stock_service.Application.Handler.ICategoryHandler;
import com.Emazon.stock_service.Application.Handler.IProductHandler;
import com.Emazon.stock_service.Infrastructure.Configuration.Security.JwtConfig.JwtTokenProvider;
import com.Emazon.stock_service.TestSecurityConfig.TestSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductRestController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ProductRestController.class, TestSecurityConfig.class})
class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductHandler productHandler;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSaveProductSuccessfully() throws Exception {
        ProductDto productDto = new ProductDto("ProductName", "ProductDescription", 10, 100L, List.of(1L, 2L), 1L);
        mockMvc.perform(post("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnBadRequestWhenProductDtoIsInvalid() throws Exception {
        ProductDto productDto = new ProductDto("", "", -1, -100L, null, null);
        mockMvc.perform(post("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldSupplyProductSuccessfully() throws Exception {
        SupplyDto supplyDto = new SupplyDto(1L, 10);
        mockMvc.perform(patch("/product/supply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplyDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenSupplyDtoIsInvalid() throws Exception {
        SupplyDto supplyDto = new SupplyDto(1L, -10);
        mockMvc.perform(patch("/product/supply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplyDto)))
                .andExpect(status().isBadRequest());
    }
}