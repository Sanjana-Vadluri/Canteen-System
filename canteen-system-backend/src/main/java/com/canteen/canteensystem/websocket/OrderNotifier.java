package com.canteen.canteensystem.websocket;

import com.canteen.canteensystem.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderNotifier {

    private final SimpMessagingTemplate broker;   // injected by Spring

    /** push when a student places an order */
    public void newOrder(OrderDto dto) {
        broker.convertAndSend("/topic/orders", dto);          // kitchen listens
    }

    /** push when status changes */
    public void statusChanged(OrderDto dto) {
        broker.convertAndSend("/topic/order-status", dto);    // student listens
    }
}
