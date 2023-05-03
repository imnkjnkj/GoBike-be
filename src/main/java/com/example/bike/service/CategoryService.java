package com.example.bike.service;

import com.example.bike.dto.CategoryDto;
import com.example.bike.exception.BadRequestException;
import com.example.bike.exception.GenericNotFoundException;
import com.example.bike.mapper.CategoryMapper;
import com.example.bike.repository.CategoryRepository;
import com.example.bike.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> findAll() {
        return categoryRepository.findByDeletedFalse()
                .stream().map(categoryMapper::toDto)
                .toList();
    }

    @Transactional
    public CategoryDto update(Integer id, CategoryDto categoryDto) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryMapper.partialUpdate(categoryDto, category);
                    return categoryDto;
                })
                .orElseThrow(() -> new GenericNotFoundException("Category " + id));
    }


    @Transactional
    public void delete(Integer id) {
        categoryRepository.findCategoryWhichHaveNotNews(id)
                .map(category -> category.setDeleted(true))
                .orElseThrow(() -> new BadRequestException("Category have news can not delete"));
    }

    @Transactional
    public Optional<CategoryDto> create(CategoryDto categoryDto) {
        return Optional.of(categoryMapper.toEntity(categoryDto))
                .map(categoryRepository::persist)
                .map(categoryMapper::toDto);
    }
}
