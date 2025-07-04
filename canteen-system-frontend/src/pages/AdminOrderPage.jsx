import { useEffect, useState } from "react";
import { getAllOrders, updateOrderStatus } from "../services/adminService";
import useOrdersSocket from "../hooks/useOrdersSocket";

const nextStatus = {
    PENDING: "PREPARING",
    PREPARING: "READY",
    READY: "COMPLETED",
};

export default function AdminOrdersPage() {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);


    /* initial fetch */
    useEffect(() => {
        refresh();
    }, []);

    const refresh = () =>
        getAllOrders()
            .then((res) => setOrders(res.data))
            .finally(() => setLoading(false));

    /* live updates */
    useOrdersSocket(
        (newOrder) => setOrders((prev) => [newOrder, ...prev]),
        (updated) =>
            setOrders((prev) =>
                prev.map((o) => (o.id === updated.id ? updated : o))
            )
    );

    const advanceStatus = (orderId, current) => {
        const nxt = nextStatus[current];
        if (!nxt) return;

        // Optimistically update UI 👀
        setOrders((prev) =>
            prev.map((o) =>
                o.id === orderId ? { ...o, status: nxt } : o
            )
        );

        // Make backend call 🔗
        updateOrderStatus(orderId, nxt).catch((err) => {
            console.error(err);
            // Optional: Rollback or show error toast
        });
    };


    if (loading) return <p className="p-8">Loading orders…</p>;

    return (
        <div className="p-8 space-y-6">
            <h1 className="text-2xl font-bold mb-4">Kitchen / Admin Dashboard</h1>

            {orders.length === 0 ? (
                <p>No orders yet.</p>
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

                            {nextStatus[o.status] && (
                                <button
                                    onClick={() => advanceStatus(o.id, o.status)}
                                    className="bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700"
                                >
                                    Mark {nextStatus[o.status]}
                                </button>
                            )}
                        </div>
                    </div>
                ))
            )}
        </div>
    );
}
