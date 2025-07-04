package com.canteen.canteensystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /** 1 · frontend opens a SockJS/STOMP connection to this endpoint */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")              // e.g. http://localhost:8080/ws
                .setAllowedOriginPatterns("*")   // allow React dev server
                .withSockJS();                   // graceful fallback
    }

    /** 2 · routing rules */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");        // where server PUSHES
        registry.setApplicationDestinationPrefixes("/app"); // where client SENDS (not used yet)
    }
}
