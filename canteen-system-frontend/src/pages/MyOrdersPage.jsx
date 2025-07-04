import { useEffect, useState } from "react";
import { getMyOrders } from "../services/orderService";

export default function MyOrdersPage() {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getMyOrders()
            .then((res) => setOrders(res.data))
            .finally(() => setLoading(false));
    }, []);

    if (loading) return <p className="p-8">Loading your orders…</p>;

    return (
        <div className="p-8 space-y-6">
            <h1 className="text-2xl font-bold mb-4">My Orders</h1>

            {orders.length === 0 ? (
                <p>No orders yet. Start ordering from a canteen!</p>
            ) : (
                orders.map((o) => (
                    <div
                        key={o.id}
                        className="border rounded p-4 space-y-2 shadow-sm bg-white"
                    >
                        <div className="flex justify-between items-center">
                            <span className="font-semibold">Order #{o.id}</span>
                            <span className="px-2 py-1 rounded bg-gray-200 text-sm">
                {o.status}
              </span>
                        </div>

                        <ul className="ml-4 list-disc text-sm">
                            {o.items.map((it) => (
                                <li key={it.id}>
                                    {it.itemName} × {it.quantity}
                                </li>
                            ))}
                        </ul>

                        <div className="flex justify-between items-center">
                            <span className="font-medium">Total ₹{o.total}</span>
                            <span className="text-sm text-gray-500">
                {new Date(o.createdAt).toLocaleString()}
              </span>
                        </div>
                    </div>
                ))
            )}
        </div>
    );
}
