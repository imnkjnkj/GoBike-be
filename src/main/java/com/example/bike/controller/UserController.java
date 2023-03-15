package com.example.bike.controller;

import com.example.bike.dto.*;
import com.example.bike.enumeration.RoleName;
import com.example.bike.security.UserLogin;
import com.example.bike.service.AmazonCognitoService;
import com.example.bike.service.SocialLoginService;
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
    private final SocialLoginService socialLoginService;
    private final AmazonCognitoService amazonCognitoService;

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

    @PostMapping("/login")
    public AwsSignInResponseDTO login(@RequestBody AwsSignInRequestDTO requestDTO) {
        return amazonCognitoService.login(requestDTO);
    }
}


