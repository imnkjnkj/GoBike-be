package com.example.bike.mapper;

import com.example.bike.entity.Bicycle;
import com.example.bike.dto.BicycleDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BicycleMapper {
    Bicycle toEntity(BicycleDto bicycleDto);

    BicycleDto toDto(Bicycle bicycle);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Bicycle partialUpdate(BicycleDto bicycleDto, @MappingTarget Bicycle bicycle);
}