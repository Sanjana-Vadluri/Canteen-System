package com.canteen.canteensystem.controller;

import com.canteen.canteensystem.dto.CanteenDto;
import com.canteen.canteensystem.service.CanteenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canteens")
@RequiredArgsConstructor
@CrossOrigin // allows React frontend to call this
public class CanteenController {

    private final CanteenService canteenService;

    @PostMapping
    public ResponseEntity<CanteenDto> addCanteen(@RequestBody CanteenDto dto) {
        return ResponseEntity.ok(canteenService.addCanteen(dto));
    }

    @GetMapping
    public ResponseEntity<List<CanteenDto>> getAll() {
        return ResponseEntity.ok(canteenService.getAllCanteens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanteenDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(canteenService.getCanteenById(id));
    }
}
