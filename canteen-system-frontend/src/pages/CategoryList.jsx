import { useEffect, useState } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";
import { getCategoriesByCanteen } from "../services/categoryService";

export default function CategoryList() {
    const { canteenId } = useParams();
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const nav = useNavigate();

    useEffect(() => {
        getCategoriesByCanteen(canteenId)
            .then((res) => setData(res.data))
            .finally(() => setLoading(false));
    }, [canteenId]);

    if (loading) return <p className="p-8">Loading categories…</p>;

    return (
        <div className="p-8">
            <button
                onClick={() => nav(-1)}
                className="mb-4 text-blue-600 hover:underline"
            >
                ← Back to Canteens
            </button>

            <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-3">
                {data.map((cat) => (
                    <Link
                        key={cat.id}
                        to={`/categories/${cat.id}/items`}
                        className="block border rounded-xl p-6 shadow hover:shadow-lg transition"
                    >
                        <h2 className="text-lg font-semibold">{cat.name}</h2>
                        {cat.description && (
                            <p className="text-sm text-gray-600">{cat.description}</p>
                        )}
                    </Link>
                ))}
            </div>
        </div>
    );
}
