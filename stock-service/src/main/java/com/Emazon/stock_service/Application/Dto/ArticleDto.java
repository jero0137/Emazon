package com.Emazon.stock_service.Application.Dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ArticleDto {
    private String name;
    private String description;
    private int amount;
    private Long price;
    private List<Long> categoriesIds;
    private Long brandId;

}
