package com.example.bike.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AmazonCredentialsProviderConfiguration {
    private final ApplicationProperties properties;

    private AWSCredentialsProvider awsCredentialsProvider(){
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                this.properties.amazon().accessKey(),
                this.properties.amazon().secretKey()
        );
        return new AWSStaticCredentialsProvider(awsCredentials);
    }

    @Bean
    public AWSCognitoIdentityProvider cognitoIdentityProvider(){
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(awsCredentialsProvider())
                .withRegion(this.properties.amazon().region())
                .build();
    }

    @Bean
    public AmazonS3 amazonS3(){
        return AmazonS3ClientBuilder.standard()
                .withRegion(this.properties.amazon().region())
                .withCredentials(awsCredentialsProvider())
                .build();
    }
}
