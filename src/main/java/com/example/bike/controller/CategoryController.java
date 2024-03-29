package com.example.bike.controller;

import com.example.bike.dto.CategoryDto;
import com.example.bike.service.CategoryService;
import com.example.bike.utils.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Category", description = "Category of news")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.VERSION_1 + "/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Get all category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<CategoryDto> create(@RequestBody @Valid CategoryDto categoryDto){
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


