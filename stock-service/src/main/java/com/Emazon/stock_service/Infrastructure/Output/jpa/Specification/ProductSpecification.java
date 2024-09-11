package com.Emazon.stock_service.Infrastructure.Output.jpa.Specification;

import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.ProductEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<ProductEntity> hasCategory(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            if (categoryName == null || categoryName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Object, Object> categories = root.join("categories");
            return criteriaBuilder.like(criteriaBuilder.lower(categories.get("name")), categoryName.toLowerCase());
        };
    }
    public static Specification<ProductEntity> hasBrand(String brandName) {
        return (root, query, criteriaBuilder) -> {
            if (brandName == null || brandName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("brand").get("name")), brandName.toLowerCase());
        };
    }
}
