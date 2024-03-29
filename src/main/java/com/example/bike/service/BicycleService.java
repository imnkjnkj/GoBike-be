package com.example.bike.service;

import com.example.bike.dto.BicycleDto;
import com.example.bike.mapper.BicycleMapper;
import com.example.bike.repository.BicycleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BicycleService {
    private final BicycleRepository bicycleRepository;
    private final BicycleMapper bicycleMapper;

    public Page<BicycleDto> findAll(Pageable pageable) {
        return bicycleRepository.findByDeletedFalse(pageable)
                .map(bicycleMapper::toDto);
    }
}
