package com.Emazon.stock_service.Infrastructure.Output.jpa.repository;

import com.Emazon.stock_service.Infrastructure.Output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findByName(String name);

}
