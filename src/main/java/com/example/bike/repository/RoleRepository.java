package com.example.bike.repository;

import com.example.bike.entity.Role;
import com.example.bike.enumeration.RoleName;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

public interface RoleRepository extends BaseJpaRepository<Role, Integer> {
    String ROLE_BY_NAME_CACHE = "roleByName";

    @Cacheable(cacheNames = ROLE_BY_NAME_CACHE)
    Optional<Role> findByName(RoleName name);

}