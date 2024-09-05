package com.Emazon.stock_service.Infrastructure.Output.jpa.repository;

import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String name);

}
