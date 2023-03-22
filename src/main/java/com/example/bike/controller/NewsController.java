package com.example.bike.controller;

import com.example.bike.dto.NewsDto;
import com.example.bike.security.UserLogin;
import com.example.bike.service.NewsService;
import com.example.bike.utils.Constant;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.VERSION_1 + "/news")
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public Page<NewsDto> getAll(
            @ParameterObject
            @PageableDefault(page = 0, size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable,
            @RequestParam(required = false) Integer categoryId) {
        return newsService.findAll(categoryId, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDto create(@Parameter(name = "status", required = true) @RequestBody @Valid NewsDto newsDto,
                          @AuthenticationPrincipal UserLogin userLogin) {
        return newsService.create(newsDto, userLogin.getId());
    }

    @PutMapping("/{id:\\d+}")
    public NewsDto create(@PathVariable Integer id, @RequestBody @Valid NewsDto newsDto) {
        return newsService.update(id, newsDto);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable Integer id) {
        newsService.delete(id);
    }
}


