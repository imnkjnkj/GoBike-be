package com.example.bike.repository;

import com.example.bike.entity.Role;
import com.example.bike.enumeration.RoleName;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    String ROLE_BY_NAME_CACHE = "roleByName";
    @EntityGraph(attributePaths = "roles")
    @Cacheable(cacheNames = ROLE_BY_NAME_CACHE)
    Optional<Role> findByName(RoleName name);

}