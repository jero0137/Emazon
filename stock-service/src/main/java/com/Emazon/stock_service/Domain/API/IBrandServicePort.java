package com.Emazon.stock_service.Domain.API;

import com.Emazon.stock_service.Domain.Model.Brand;
import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    Brand getBrand(Long id);
    void updateBrand(Brand brand);
    void deleteBrand(Long id);
    PageCustom<Brand> getBrands(Pagination pagination);
}
