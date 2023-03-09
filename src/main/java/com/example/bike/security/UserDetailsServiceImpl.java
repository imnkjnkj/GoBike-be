package com.example.bike.security;

import com.example.bike.entity.User;
import com.example.bike.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findOneByEmail(email)
                .map(User::toUserLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " was not found in the database"));
    }

}
