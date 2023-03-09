package com.example.bike.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class UserLogin extends User implements OAuth2User, OidcUser {
    private final Integer id;
    private final OidcIdToken idToken;
    private final OidcUserInfo userInfo;
    private final Map<String, Object> attributes;

    public UserLogin(
            Integer userId,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities) {
        this(username, password, authorities, userId, null, null, null);
    }

    public UserLogin(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer id,
                     OidcIdToken idToken, OidcUserInfo userInfo, Map<String, Object> attributes) {
        super(username, password, authorities);
        this.id = id;
        this.idToken = idToken;
        this.userInfo = userInfo;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getClaims() {
        return attributes;
    }

    @Override
    public String getName() {
        return super.getUsername();
    }
}
