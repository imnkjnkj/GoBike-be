package com.example.bike.mapper;

import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.example.bike.dto.AwsSignInResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CognitoMapper {
    AwsSignInResponseDTO toDto(AuthenticationResultType authenticationResultType);

}