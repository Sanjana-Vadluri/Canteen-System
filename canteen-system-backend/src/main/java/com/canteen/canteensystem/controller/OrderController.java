package com.canteen.canteensystem.controller;

import com.canteen.canteensystem.dto.OrderDto;
import com.canteen.canteensystem.model.OrderStatus;
import com.canteen.canteensystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /* -------- student endpoints -------- */

    @PostMapping("/place")
    public ResponseEntity<OrderDto> placeOrder() {
        return new ResponseEntity<>(orderService.placeOrder(), HttpStatus.CREATED);
    }

    @GetMapping("/mine")
    public ResponseEntity<List<OrderDto>> myOrders() {
        return ResponseEntity.ok(orderService.myOrders());
    }

    /* -------- admin / kitchen endpoints -------- */

    @GetMapping
    public ResponseEntity<List<OrderDto>> all() {
        return ResponseEntity.ok(orderService.allOrders());
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDto> updateStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateStatus(orderId, status));
    }
}
