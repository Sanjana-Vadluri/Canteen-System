package com.canteen.canteensystem.service;

import com.canteen.canteensystem.dto.CanteenDto;
import java.util.List;

public interface CanteenService {
    CanteenDto addCanteen(CanteenDto dto);
    List<CanteenDto> getAllCanteens();
    CanteenDto getCanteenById(Long id);
}
