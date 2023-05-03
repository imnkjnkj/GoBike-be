package com.example.bike.controller;

import com.example.bike.dto.BicycleDto;
import com.example.bike.service.BicycleService;
import com.example.bike.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = Constant.VERSION_1 + "/bicycle")
public class BicycleController {
    private final BicycleService bicycleService;

    @GetMapping
    public Page<BicycleDto> getAll(
            @ParameterObject
            @PageableDefault(size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable) {
        return bicycleService.findAll(pageable);
    }
}


