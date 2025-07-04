import { useEffect } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

export default function useOrdersSocket(onNew, onUpdate) {
    useEffect(() => {
        const sock = new SockJS(import.meta.env.VITE_API_URL + "/ws");
        const client = new Client({ webSocketFactory: () => sock });

        client.onConnect = () => {
            client.subscribe("/topic/orders", (msg) => onNew(JSON.parse(msg.body)));
            client.subscribe("/topic/order-status", (msg) =>
                onUpdate(JSON.parse(msg.body))
            );
        };
        client.activate();
        return () => client.deactivate();
    }, [onNew, onUpdate]);
}
