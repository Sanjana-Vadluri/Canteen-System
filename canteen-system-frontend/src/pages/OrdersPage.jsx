import { useEffect, useState } from "react";
import { getMyOrders } from "../services/orderService";
import useOrderStatusSocket from "../hooks/useOrderStatusSocket";

export default function OrdersPage() {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);

    const refresh = () =>
        getMyOrders()
            .then((res) => setOrders(res.data))
            .finally(() => setLoading(false));

    useEffect(() => {
        refresh();
    }, []);

    /* ✅ WebSocket live updates for status + new orders */
    useOrderStatusSocket(
        (updated) => {
            console.log("📡 Order updated:", updated);
            setOrders((prev) =>
                prev.map((o) => (o.id === updated.id ? updated : o))
            );
        },
        (newOrder) => {
            console.log("✨ New order received:", newOrder);
            setOrders((prev) => [newOrder, ...prev]); // Add new order at top
        }
    );

    if (loading) return <p className="p-8">Loading orders…</p>;

    return (
        <div className="p-8 space-y-6">
            <h1 className="text-2xl font-bold">My Orders</h1>

            {orders.length === 0 ? (
                <p>No orders yet.</p>
            ) : (
                orders.map((o) => (
                    <div key={o.id} className="border rounded p-4 space-y-2">
                        <div className="flex justify-between">
                            <span>Order #{o.id}</span>
                            <span className="font-medium">{o.status}</span>
                        </div>
                        <ul className="ml-4 list-disc">
                            {o.items.map((it) => (
                                <li key={it.id}>
                                    {it.itemName} × {it.quantity} = ₹{it.lineTotal}
                                </li>
                            ))}
                        </ul>
                        <div className="text-right font-semibold">
                            Total: ₹{o.total}
                        </div>
                    </div>
                ))
            )}
        </div>
    );
}
