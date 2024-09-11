package com.Emazon.stock_service.Infrastructure.Output.jpa.repository;

import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);

    void deleteByName(String name);
}
