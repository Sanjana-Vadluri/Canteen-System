export default function ItemCard({ item, onAdd }) {
    return (
        <div className="border rounded-xl p-4 shadow-custom hover:shadow-lg flex flex-col w-60">
            {item.imageUrl && (
                <img
                    src={item.imageUrl}
                    alt={item.name}
                    className="h-56 w-60 object-cover rounded-md mb-2"
                />
            )}
            <h3 className="font-semibold text-lg">{item.name}</h3>
            {item.description && (
                <p className="text-sm text-gray-600 line-clamp-2">{item.description}</p>
            )}
            <div className="mt-auto flex items-center justify-between">
                <span className="font-medium">₹{item.price}</span>
                <button
                    onClick={() => onAdd(item.id)}
                    className="bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700"
                >
                    Add
                </button>
            </div>
        </div>
    );
}
