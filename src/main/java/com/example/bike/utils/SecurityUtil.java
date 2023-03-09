package com.example.bike.utils;

import com.example.bike.security.UserLogin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public final class SecurityUtil {
    public static Optional<String> getCurrentUserName() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public static Optional<Integer> getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof UserLogin)
            return Optional.ofNullable(((UserLogin) authentication.getPrincipal()).getId());
        return Optional.empty();
    }

}
