package com.example.bike.service;

import com.example.bike.dto.NewsDto;
import com.example.bike.exception.GenericNotFoundException;
import com.example.bike.mapper.NewsMapper;
import com.example.bike.repository.CategoryRepository;
import com.example.bike.repository.NewsRepository;
import com.example.bike.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final NewsMapper newsMapper;

    public Page<NewsDto> findAll(Integer categoryId, Pageable pageable) {
        return newsRepository.findAllByCategory(categoryId, pageable)
                .map(newsMapper::toDto);
    }

    @Transactional
    public NewsDto create(NewsDto newsDto, Integer userId) {
        var news = newsMapper.toEntity(newsDto)
                .setCategory(categoryRepository.getReferenceById(newsDto.category().id()))
                .setUser(userRepository.getReferenceById(userId));
        return newsMapper.toDto(newsRepository.persist(news));
    }


    @Transactional
    public NewsDto update(Integer id, NewsDto newsDto) {
        return newsRepository.findById(id)
                .map(news -> {
                    newsMapper
                            .partialUpdate(newsDto, news)
                            .setCategory(categoryRepository.getReferenceById(newsDto.category().id()));
                    return newsDto;
                })
                .orElseThrow(() -> new GenericNotFoundException("News " + id));
    }

    public void delete(Integer id) {
        newsRepository.findById(id).ifPresent(news -> news.setDeleted(true));
    }
}
