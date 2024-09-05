package com.Emazon.stock_service.Application.Dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProductDto {
    private String name;
    private String description;
    private int quantity;
    private Long price;
    private List<Long> categoriesIds;
    private Long brandId;

}