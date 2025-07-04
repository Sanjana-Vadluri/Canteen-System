package com.canteen.canteensystem.service.impl;

import com.canteen.canteensystem.dto.ItemDto;
import com.canteen.canteensystem.mapper.ItemMapper;
import com.canteen.canteensystem.model.Category;
import com.canteen.canteensystem.model.Item;
import com.canteen.canteensystem.repository.CategoryRepository;
import com.canteen.canteensystem.repository.ItemRepository;
import com.canteen.canteensystem.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepo;
    private final CategoryRepository categoryRepo;

    @Override
    public ItemDto create(Long categoryId, ItemDto dto) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category " + categoryId + " not found"));

        Item saved = itemRepo.save(ItemMapper.toEntity(dto, category));
        return ItemMapper.toDto(saved);
    }

    @Override
    public List<ItemDto> getByCategory(Long categoryId) {
        return itemRepo.findByCategoryId(categoryId)
                .stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto update(Long itemId, ItemDto dto) {
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item " + itemId + " not found"));

        // update mutable fields
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setImageUrl(dto.getImageUrl());
        item.setAvailable(dto.getAvailable());

        return ItemMapper.toDto(itemRepo.save(item));
    }

    @Override
    public void delete(Long itemId) {
        if (!itemRepo.existsById(itemId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item " + itemId + " not found");

        itemRepo.deleteById(itemId);
    }
}
