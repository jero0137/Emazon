package com.Emazon.stock_service.Application.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private int quantity;
    @NotNull
    private Long price;
    @NotNull
    private List<Long> categoriesIds;
    @NotNull
    private Long brandId;

}