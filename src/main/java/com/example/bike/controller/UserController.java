package com.example.bike.controller;

import com.example.bike.dto.JwtTokenDTO;
import com.example.bike.dto.RoleDto;
import com.example.bike.dto.UserDto;
import com.example.bike.enumeration.RoleName;
import com.example.bike.repository.RoleRepository;
import com.example.bike.repository.UserRepository;
import com.example.bike.security.UserLogin;
import com.example.bike.service.SocialLoginService;
import com.example.bike.service.UserService;
import com.example.bike.utils.Constant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.VERSION_1 + "/user")
public class UserController {
    private final UserService userService;
    private final SocialLoginService socialLoginService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenDTO> socialLogin(@RequestBody @Valid JwtTokenDTO payload) {
        return socialLoginService.authenticate(payload.accessToken());
    }

    @GetMapping("/info")
    public UserDto userinfo(@AuthenticationPrincipal UserLogin userLogin) {
        return new UserDto(
                userLogin.getId(),
                userLogin.getName(),
                userLogin.getName(),
                userLogin.getAuthorities()
                        .stream().map(a -> new RoleDto(RoleName.valueOf(a.getAuthority())))
                        .collect(Collectors.toSet())
        );
    }
}


