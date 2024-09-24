package com.Emazon.stock_service.Infrastructure.Input;


import com.Emazon.stock_service.Application.Dto.BrandDto;
import com.Emazon.stock_service.Application.Handler.IBrandHandler;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandRestController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {BrandRestController.class, TestSecurityConfig.class})
class BrandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBrandHandler brandHandler;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSaveBrandSuccessfully() throws Exception {
        BrandDto brandDto = new BrandDto("BrandName", "BrandDescription");
        mockMvc.perform(post("/brand/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnBadRequestWhenBrandDtoIsInvalid() throws Exception {
        BrandDto brandDto = new BrandDto("", "");
        mockMvc.perform(post("/brand/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brandDto)))
                .andExpect(status().isBadRequest());
    }

}