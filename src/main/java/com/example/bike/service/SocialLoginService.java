package com.example.bike.service;

import com.example.bike.dto.JwtTokenDTO;
import com.example.bike.entity.Role;
import com.example.bike.entity.User;
import com.example.bike.enumeration.RoleName;
import com.example.bike.exception.BadRequestException;
import com.example.bike.exception.GenericNotFoundException;
import com.example.bike.repository.RoleRepository;
import com.example.bike.repository.UserRepository;
import com.example.bike.security.UserLogin;
import com.example.bike.security.jwt.JWTFilter;
import com.example.bike.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SocialLoginService {
    private static final String GOOGLE = "google";
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final WebClient.Builder oauth2WebClient;
    private final PasswordEncoder passwordEncoder;

    @SuppressWarnings (value="unchecked")
    public ResponseEntity<JwtTokenDTO> authenticate(String token) {
        ClientRegistration googleClient = clientRegistrationRepository.findByRegistrationId(GOOGLE);
        Map<String, Object> attributes = oauth2WebClient.build()
                .get()
                .uri(googleClient.getProviderDetails().getUserInfoEndpoint().getUri())
                .headers(headers -> headers.setBearerAuth(token))
                .retrieve()
                .onStatus(HttpStatusCode::isError, res -> res.bodyToMono(Map.class)
                        .flatMap(error -> Mono.error(new BadRequestException(error.toString()))))
                .bodyToMono(Map.class)
                .block();
        if (attributes == null || attributes.isEmpty()) throw new BadRequestException("Invalid token");
        String email = Optional.ofNullable((String) attributes.get("email"))
                .orElseThrow(() -> new GenericNotFoundException("Email not found"));
        Optional<User> userOptional = userRepository.findOneByEmail(email);
        UserLogin userLogin;
        if (userOptional.isEmpty()) {
            userLogin = executeUserRegistration(email);
        } else {
            userLogin = userOptional.get().toUserLogin();
        }
        UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken
                .authenticated(userLogin, "", userLogin.getAuthorities());
        String jwt = "Bearer " + tokenProvider.createToken(authentication);
        return ResponseEntity.ok()
                .header(JWTFilter.AUTHORIZATION_HEADER, jwt)
                .body(new JwtTokenDTO(jwt));
    }

    public UserLogin processUserLogin(Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        String email = (String) attributes.get("email");
        if (StringUtils.isEmpty(email)) {
            throw new GenericNotFoundException("Email ");
        }
        Optional<User> userOptional = userRepository.findOneByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get().toUserLogin(idToken, userInfo, attributes);
        } else {
            return executeUserRegistration(attributes, idToken, userInfo, email);
        }
    }

    public UserLogin executeUserRegistration(String email) {
        return executeUserRegistration(null, null, null, email);
    }

    private UserLogin executeUserRegistration(Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo, String email) {
        Role roleUser = roleRepository.findByName(RoleName.USER)
                .orElseThrow(() -> new GenericNotFoundException("Role " + RoleName.USER));
        return userRepository.persist(
                        User.builder()
                                .username(email)
                                .email(email)
                                .password(passwordEncoder.encode(email))
                                .roles(Set.of(roleUser))
                                .build()
                )
                .toUserLogin(idToken, userInfo, attributes);
    }
}
