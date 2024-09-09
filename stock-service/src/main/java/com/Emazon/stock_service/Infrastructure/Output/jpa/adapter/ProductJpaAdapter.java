package com.Emazon.stock_service.Infrastructure.Output.jpa.adapter;

import com.Emazon.stock_service.Domain.Model.PageCustom;
import com.Emazon.stock_service.Domain.Model.Pagination;
import com.Emazon.stock_service.Domain.Model.Product;
import com.Emazon.stock_service.Domain.SPI.IProductPersistencePort;
import com.Emazon.stock_service.Infrastructure.Exception.BrandNotFoundException;
import com.Emazon.stock_service.Infrastructure.Exception.ProductAlreadyExistsException;
import com.Emazon.stock_service.Infrastructure.Exception.CategoryNotFoundException;
import com.Emazon.stock_service.Infrastructure.Output.jpa.Specification.ProductSpecification;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.ProductEntity;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.BrandEntity;
import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.CategoryEntity;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.PageEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.mapper.ProductEntityMapper;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.IProductRepository;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.IBrandRepository;
import com.Emazon.stock_service.Infrastructure.Output.jpa.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@AllArgsConstructor
public class ProductJpaAdapter implements IProductPersistencePort {

    private final IProductRepository articleRepository;
    private final ICategoryRepository categoryRepository;
    private final IBrandRepository brandRepository;
    private final ProductEntityMapper productEntityMapper;
    private final PageEntityMapper pageEntityMapper;


    @Override
    public void saveArticle(Product product) {
        if(articleRepository.findByName(product.getName()).isPresent()){
            throw new ProductAlreadyExistsException("Ya existe un articulo con ese nombre");
        }


        List<CategoryEntity> categoryEntities = product.getCategories().stream()
                .map(category -> categoryRepository.findById(category.getId()).orElseThrow(CategoryNotFoundException::new))
                .toList();

        BrandEntity brandEntity = brandRepository.findById(product.getBrand().getId())
                .orElseThrow(BrandNotFoundException::new);

        ProductEntity productEntity = productEntityMapper.toProductEntity(product);

        productEntity.setCategories(categoryEntities);
        productEntity.setBrand(brandEntity);

        articleRepository.save(productEntity);

    }

    @Override
    public Product getArticle(Long id) {
        return productEntityMapper.toProduct(articleRepository.findById(id).orElse(null));
    }

    @Override
    public PageCustom<Product> getProducts(Pagination pagination, String category, String brand) {

        Specification<ProductEntity> spec = Specification.where(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.hasBrand(brand));

        PageRequest pageRequest = PageRequest.of(pagination.getPage(), pagination.getSize(), Sort.by(pagination.getDirection(), pagination.getSort()));
        Page<ProductEntity> productEntityPage = articleRepository.findAll(spec, pageRequest);

        return pageEntityMapper.toProductDtoPageCustom(productEntityPage);
    }
}
