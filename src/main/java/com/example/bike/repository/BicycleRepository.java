package com.example.bike.repository;

import com.example.bike.entity.Bicycle;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BicycleRepository extends BaseJpaRepository<Bicycle, Integer>, PagingAndSortingRepository<Bicycle, Integer> {
    Page<Bicycle> findByDeletedFalse(Pageable pageable);
}