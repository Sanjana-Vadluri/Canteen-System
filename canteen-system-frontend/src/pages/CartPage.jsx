import { useEffect, useState } from "react";
import {
    getCart,
    updateQty,
    removeItem,
    clearCart,
} from "../services/cartService";
import { Link } from "react-router-dom";
import { placeOrder } from "../services/orderService";
import { useNavigate } from "react-router-dom";

export default function CartPage() {
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true);

    const nav = useNavigate();

    const handleCheckout = () => {
        placeOrder()
            .then(() => {
                alert("Order placed!");
                nav("/orders");        // redirect to order history
            })
            .catch(() => alert("Failed to place order"));
    };

    /* fetch cart on mount */
    useEffect(() => {
        refresh();
    }, []);

    const refresh = () =>
        getCart()
            .then((res) => setItems(res.data))
            .finally(() => setLoading(false));

    const changeQty = (itemId, newQty) =>
        updateQty(itemId, newQty).then(refresh);

    const removeLine = (itemId) => removeItem(itemId).then(refresh);

    const emptyCart = () => clearCart().then(refresh);

    const total = items.reduce(
        (sum, it) => sum + it.quantity * it.price, // backend returns price and qty
        0
    );

    if (loading) return <p className="p-8">Loading cart…</p>;

    return (
        <div className="p-8 max-w-3xl mx-auto space-y-6">
            <h1 className="text-2xl font-bold">Your Cart</h1>

            {items.length === 0 ? (
                <p>
                    Cart is empty — <Link className="text-blue-600" to="/canteens">browse canteens</Link>
                </p>
            ) : (
                <>
                    {items.map((it) => (
                        <div
                            key={it.id}
                            className="flex justify-between items-center border rounded p-4"
                        >
                            <div>
                                <h2 className="font-semibold">{it.itemName}</h2>
                                <p className="text-sm text-gray-600">
                                    ₹{it.price} × {it.quantity} = ₹{it.lineTotal}
                                </p>
                            </div>

                            <div className="flex items-center gap-2">
                                <button
                                    className="px-2 py-1 border"
                                    disabled={it.quantity <= 1}
                                    onClick={() => changeQty(it.itemId, it.quantity - 1)}
                                >
                                    –
                                </button>
                                <span>{it.quantity}</span>
                                <button
                                    className="px-2 py-1 border"
                                    onClick={() => changeQty(it.itemId, it.quantity + 1)}
                                >
                                    +
                                </button>
                                <button
                                    className="ml-4 px-3 py-1 bg-red-500 text-white rounded"
                                    onClick={() => removeLine(it.itemId)}
                                >
                                    Remove
                                </button>
                            </div>
                        </div>
                    ))}

                    <div className="text-right text-lg font-semibold">
                        Total: ₹{total}
                    </div>

                    <div className="flex justify-between">
                        <button
                            onClick={emptyCart}
                            className="text-red-600 hover:underline"
                        >
                            Clear Cart
                        </button>

                        {/* placeholder for checkout */}
                        <button
                            onClick={handleCheckout}
                            className="bg-green-600 text-white px-4 py-2 rounded"
                        >
                            Checkout
                        </button>
                    </div>
                </>
            )}
        </div>
    );
}
