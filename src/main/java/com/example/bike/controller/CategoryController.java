package com.example.bike.controller;

import com.example.bike.dto.CategoryDto;
import com.example.bike.service.CategoryService;
import com.example.bike.utils.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category", description = "Category of news")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.VERSION_1 + "/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Get all news")
    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@RequestBody @Valid CategoryDto categoryDto){
        return categoryService.create(categoryDto);
    }

    @PutMapping("/{id:\\d+}")
    public CategoryDto update(@PathVariable Integer id, @RequestBody @Valid CategoryDto categoryDto) {
        return categoryService.update(id, categoryDto);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }
}


