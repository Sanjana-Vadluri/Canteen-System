package com.canteen.canteensystem.service.impl;

import com.canteen.canteensystem.dto.CategoryDto;
import com.canteen.canteensystem.mapper.CategoryMapper;
import com.canteen.canteensystem.model.Canteen;
import com.canteen.canteensystem.repository.CanteenRepository;
import com.canteen.canteensystem.repository.CategoryRepository;
import com.canteen.canteensystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;
    private final CanteenRepository canteenRepo;

    @Override
    public CategoryDto create(Long canteenId, CategoryDto dto) {
        Canteen canteen = canteenRepo.findById(canteenId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Canteen " + canteenId + " not found"));

        var saved = categoryRepo.save(CategoryMapper.toEntity(dto, canteen));
        return CategoryMapper.toDto(saved);
    }

    @Override
    public List<CategoryDto> getByCanteen(Long canteenId) {
        return categoryRepo.findByCanteenId(canteenId)
                .stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }
}