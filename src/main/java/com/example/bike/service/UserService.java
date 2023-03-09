package com.example.bike.service;

import com.example.bike.entity.User;
import com.example.bike.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {
    private final CacheManager cacheManager;

    private void clearUserCaches(User user) {
        Objects.requireNonNull(cacheManager.getCache(UserRepository.USER_BY_EMAIL_CACHE)).evict(user.getEmail());
    }
}
