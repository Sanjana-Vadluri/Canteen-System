// service/OrderService.java
package com.canteen.canteensystem.service;

import com.canteen.canteensystem.dto.OrderDto;
import com.canteen.canteensystem.model.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderDto placeOrder();                          // student
    List<OrderDto> myOrders();                      // student
    List<OrderDto> allOrders();                     // admin/kitchen
    OrderDto updateStatus(Long orderId, OrderStatus status);  // admin/kitchen
}
