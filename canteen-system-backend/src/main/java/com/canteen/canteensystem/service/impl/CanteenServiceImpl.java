package com.canteen.canteensystem.service.impl;

import com.canteen.canteensystem.dto.CanteenDto;
import com.canteen.canteensystem.mapper.CanteenMapper;
import com.canteen.canteensystem.model.Canteen;
import com.canteen.canteensystem.repository.CanteenRepository;
import com.canteen.canteensystem.service.CanteenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CanteenServiceImpl implements CanteenService {

    private final CanteenRepository canteenRepository;

    @Override
    public CanteenDto addCanteen(CanteenDto dto) {
        Canteen canteen = CanteenMapper.toEntity(dto);
        Canteen saved = canteenRepository.save(canteen);
        return CanteenMapper.toDto(saved);
    }

    @Override
    public List<CanteenDto> getAllCanteens() {
        return canteenRepository.findAll().stream()
                .map(CanteenMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CanteenDto getCanteenById(Long id) {
        Canteen canteen = canteenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Canteen not found with ID: " + id));
        return CanteenMapper.toDto(canteen);
    }
}
