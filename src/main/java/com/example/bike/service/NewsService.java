package com.example.bike.service;

import com.example.bike.dto.NewsDto;
import com.example.bike.mapper.NewsMapper;
import com.example.bike.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    public Page<NewsDto> findAll(Integer categoryId, Pageable pageable){
        return newsRepository.findAllByCategory(categoryId, pageable)
                .map(newsMapper::toDto);
    }
}
