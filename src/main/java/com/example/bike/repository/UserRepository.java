package com.example.bike.repository;

import com.example.bike.entity.User;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface UserRepository extends BaseJpaRepository<User, Integer> {
    String USER_BY_EMAIL_CACHE = "usersByEmail";

    @EntityGraph(attributePaths = "roles")
    @Cacheable(cacheNames = USER_BY_EMAIL_CACHE)
    Optional<User> findOneByEmail(String email);
}