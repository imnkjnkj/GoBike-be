package com.example.bike.repository;

import com.example.bike.entity.Category;
import com.example.bike.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsRepository extends JpaRepository<News, Integer> {
    @EntityGraph(attributePaths = {"user", "category"})
    @Query("select n from News n where n.category.id = :categoryId or :categoryId is null and n.deleted = false")
    Page<News> findAllByCategory(@Param("categoryId") Integer categoryId, Pageable pageable);

    long countByCategory(Category category);
}