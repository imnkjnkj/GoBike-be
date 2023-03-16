package com.example.bike.service;


import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.example.bike.configuration.ApplicationProperties;
import com.example.bike.dto.AwsSignInRequestDTO;
import com.example.bike.dto.AwsSignInResponseDTO;
import com.example.bike.exception.BadRequestException;
import com.example.bike.mapper.CognitoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class AmazonCognitoService {
    private final ApplicationProperties properties;
    private final AWSCognitoIdentityProvider cognitoIdentityProvider;
    private final CognitoMapper cognitoMapper;
    private final JwtDecoder jwtDecoder;


    public AwsSignInResponseDTO login(AwsSignInRequestDTO awsSignInRequestDTO) {
        String secretHash = calculateSecretHash(this.properties.amazon().clientId(),
                this.properties.amazon().clientSecret(), awsSignInRequestDTO.email());
        AdminInitiateAuthRequest adminInitiateAuthRequest = new AdminInitiateAuthRequest()
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withClientId(this.properties.amazon().clientId())
                .withUserPoolId(this.properties.amazon().userPoolId())
                .addAuthParametersEntry("USERNAME", awsSignInRequestDTO.email())
                .addAuthParametersEntry("PASSWORD", awsSignInRequestDTO.password())
                .addAuthParametersEntry("SECRET_HASH", secretHash);

        try {
            AdminInitiateAuthResult adminInitiateAuthResult = this.cognitoIdentityProvider.adminInitiateAuth(adminInitiateAuthRequest);
            log.info("Challenge Name is {}", adminInitiateAuthResult.getChallengeName());
            Jwt decode = jwtDecoder.decode(adminInitiateAuthResult.getAuthenticationResult().getIdToken());
            Jwt decode1 = jwtDecoder.decode(adminInitiateAuthResult.getAuthenticationResult().getAccessToken());
            return cognitoMapper.toDto(adminInitiateAuthResult.getAuthenticationResult());
        } catch (AWSCognitoIdentityProviderException e) {
            throw new BadRequestException("Login cognito fail:" + e.getMessage());
        }
    }

    public static String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating ");
        }
    }
}
