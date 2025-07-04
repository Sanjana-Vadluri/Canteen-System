import { useEffect } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

export default function useOrderStatusSocket(onStatusUpdate, onNewOrder) {
    useEffect(() => {
        const sock = new SockJS(import.meta.env.VITE_API_URL + "/ws");
        const client = new Client({ webSocketFactory: () => sock });

        client.onConnect = () => {
            console.log("✅ WebSocket connected!");

            client.subscribe("/topic/order-status", (msg) => {
                const updated = JSON.parse(msg.body);
                console.log("📡 Order updated:", updated);
                onStatusUpdate(updated);
            });

            client.subscribe("/topic/new-orders", (msg) => {
                const newOrder = JSON.parse(msg.body);
                console.log("✨ New order received:", newOrder);
                onNewOrder(newOrder);
            });
        };

        client.activate();
        return () => client.deactivate();
    }, [onStatusUpdate, onNewOrder]);
}
