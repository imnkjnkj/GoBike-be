package com.example.bike.repository;

import com.example.bike.entity.Category;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends BaseJpaRepository<Category, Integer> {
    List<Category> findByDeletedFalse();

    @Query("select c from Category c " +
            "where not exists (select 1 from Category c1 join News n on c1 = n.category and c1 = c)")
    Optional<Category> findCategoryWhichHaveNotNews(Integer id);

}