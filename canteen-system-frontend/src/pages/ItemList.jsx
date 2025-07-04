import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { getItemsByCategory } from "../services/itemService";
import { addToCart } from "../services/cartService";
import ItemCard from "../components/ItemCard.jsx";

export default function ItemList() {
    const { categoryId } = useParams();
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getItemsByCategory(categoryId)
            .then((res) => setItems(res.data))
            .finally(() => setLoading(false));
    }, [categoryId]);

    const handleAdd = (itemId) => {
        addToCart(itemId).then(() =>
            alert("Added to cart") // replace with toast later
        );
    };

    if (loading) return <p className="p-8">Loading items…</p>;

    return (
        <div className="p-8">
            <Link
                to={-1}
                className="mb-4 inline-block text-blue-600 hover:underline"
            >
                ← Back to Categories
            </Link>

            {items.length === 0 ? (
                <p>No items in this category.</p>
            ) : (
                <div className="grid gap-8 sm:grid-cols-2 lg:grid-cols-4 ml-10">
                    {items.map((it) => (
                        <ItemCard key={it.id} item={it} onAdd={handleAdd} />
                    ))}
                </div>
            )}
        </div>
    );
}
