package com.canteen.canteensystem.service;

import com.canteen.canteensystem.dto.ItemDto;

import java.util.List;

public interface ItemService {

    ItemDto create(Long categoryId, ItemDto dto);

    List<ItemDto> getByCategory(Long categoryId);

    ItemDto update(Long itemId, ItemDto dto);

    void delete(Long itemId);
}
