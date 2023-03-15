package com.example.bike.repository;

import com.example.bike.entity.Role;
import com.example.bike.enumeration.RoleName;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    String ROLE_BY_NAME_CACHE = "roleByName";

    @Cacheable(cacheNames = ROLE_BY_NAME_CACHE)
    Optional<Role> findByName(RoleName name);

}