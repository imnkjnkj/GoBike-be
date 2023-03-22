package com.example.bike.service;

import com.example.bike.dto.NewsDto;
import com.example.bike.entity.News;
import com.example.bike.entity.User;
import com.example.bike.exception.GenericNotFoundException;
import com.example.bike.mapper.NewsMapper;
import com.example.bike.repository.CategoryRepository;
import com.example.bike.repository.NewsRepository;
import com.example.bike.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final NewsMapper newsMapper;

    public Page<NewsDto> findAll(Integer categoryId, Pageable pageable) {
        return newsRepository.findAllByCategory(categoryId, pageable)
                .map(newsMapper::toDto);
    }

    public NewsDto create(NewsDto newsDto, Integer userId) {
        var news = newsMapper.toEntity(newsDto)
                .setCategory(categoryRepository.findById(newsDto.category().id())
                        .orElseThrow(() -> new GenericNotFoundException("Category " + newsDto.category().id())))
                .setUser(userRepository.findById(userId)
                        .orElseThrow(() -> new GenericNotFoundException("User " + userId)));
        return newsMapper.toDto(newsRepository.save(news));
    }

    public NewsDto update(Integer id, NewsDto newsDto) {
        var news = newsMapper.partialUpdate(newsDto,
                        newsRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("News " + id)))
                .setCategory(categoryRepository.findById(newsDto.category().id())
                                .orElseThrow(() -> new GenericNotFoundException("Category " + newsDto.category().id())));
        return newsMapper.toDto(newsRepository.save(news));
    }

    public void delete(Integer id) {
        newsRepository.findById(id)
                .ifPresent(news -> {
                    news.setDeleted(true);
                    newsRepository.save(news);
                });
    }
}
