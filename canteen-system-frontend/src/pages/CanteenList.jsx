import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllCanteens } from "../services/canteenService";

export default function CanteenList() {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const nav = useNavigate();

    useEffect(() => {
        getAllCanteens()
            .then((res) => setData(res.data))
            .finally(() => setLoading(false));
    }, []);

    if (loading) return <p className="p-8">Loading canteens…</p>;

    return (
        <div className="p-8 grid gap-6 sm:grid-cols-2 lg:grid-cols-3">
            {data.map((c) => (
                <div
                    key={c.id}
                    onClick={() => nav(`/canteens/${c.id}/categories`)}
                    className="cursor-pointer border rounded-xl p-6 shadow hover:shadow-lg transition"
                >
                    <h2 className="text-xl font-bold mb-2">{c.name}</h2>
                    {c.location && <p className="text-gray-600">{c.location}</p>}
                </div>
            ))}
        </div>
    );
}
