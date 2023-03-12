package com.example.bike.mapper;

import com.example.bike.dto.NewsDto;
import com.example.bike.entity.News;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NewsMapper {
    News toEntity(NewsDto newsDto);

    NewsDto toDto(News news);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    News partialUpdate(NewsDto newsDto, @MappingTarget News news);
}