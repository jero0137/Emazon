package com.Emazon.stock_service.Infrastructure.Output.Jpa.Repository;

import com.Emazon.stock_service.Infrastructure.Output.Jpa.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);

    void deleteByName(String name);
}
