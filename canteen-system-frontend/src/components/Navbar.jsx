import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useCart } from "../context/CartContext";

/* Optional: if you already created a CartContext
   that keeps track of item count, import it: */
// import { useCart } from "../context/CartContext";

export default function Navbar() {
    const { user, logout } = useAuth();
    const { cartItems } = useCart() || { cartCount: 0 };   // fallback
    //const cartCount = 0; // remove this line when you wire up CartContext
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate("/login");
    };

    return (
        <nav className="bg-orange-600 text-white px-6 py-4 flex justify-between items-center shadow-md">
            {/* --- Logo / left side --- */}
            <Link to="/" className="text-xl font-bold">
                Canteen System
            </Link>

            {/* --- Right‑side links --- */}
            <div className="flex gap-4 items-center">
                {user ? (
                    <>
                        <Link to="/" className="hover:underline">
                            Home
                        </Link>



                        <Link to="/my-orders" className="hover:underline">
                            My Orders
                        </Link>

                        {user.role === "ADMIN" && (
                            <Link to="/admin/orders" className="hover:underline">
                                Admin Dashboard
                            </Link>
                        )}

                        {/* Cart link with badge */}
                        <Link to="/cart" className="relative">
                            🛒
                            {cartItems.length > 0 && (
                                <span className="absolute -top-2 -right-2 bg-red-500 text-white text-xs px-1 rounded-full">
              {cartItems.length}
            </span>
                            )}
                        </Link>

                        <button
                            onClick={handleLogout}
                            className="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded"
                        >
                            Logout
                        </button>

                    </>
                ) : (
                    <>
                        <Link to="/login" className="hover:underline">
                            Login
                        </Link>
                        <Link to="/register" className="hover:underline">
                            Register
                        </Link>
                    </>
                )}
            </div>
        </nav>
    );
}
