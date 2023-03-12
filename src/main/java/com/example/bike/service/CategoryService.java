package com.example.bike.service;

import com.example.bike.dto.CategoryDto;
import com.example.bike.entity.Category;
import com.example.bike.exception.BadRequestException;
import com.example.bike.exception.GenericNotFoundException;
import com.example.bike.mapper.CategoryMapper;
import com.example.bike.repository.CategoryRepository;
import com.example.bike.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> findAll() {
        return categoryRepository.findByDeletedFalse()
                .stream().map(categoryMapper::toDto)
                .toList();
    }

    public CategoryDto update(Integer id, CategoryDto categoryDto) {
        return categoryRepository.findById(id)
                .map(category -> categoryMapper.toDto(
                        categoryRepository.save(categoryMapper.partialUpdate(categoryDto, category))
                ))
                .orElseThrow(() -> new GenericNotFoundException("Category " + id));
    }


    public void delete(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Category " + id));
        if (newsRepository.countByCategory(category) > 1)
            throw new BadRequestException("Category have news can not delete");
        category.setDeleted(true);
        categoryRepository.save(category);
    }

    public CategoryDto create(CategoryDto categoryDto) {
        return categoryMapper.toDto(
                categoryRepository.save(
                        categoryMapper.toEntity(categoryDto)
                )
        );
    }
}
