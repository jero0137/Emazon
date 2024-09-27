package com.Emazon.stock_service.Infrastructure.Output.jpa.repository;

import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    Optional<ProductEntity> findByName(String name);
    boolean existsById(Long id);
}
