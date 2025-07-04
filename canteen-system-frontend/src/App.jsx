import PrivateRoute from "./components/PrivateRoute";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import AuthProvider from "./context/AuthContext";
import Login from "./pages/Login";
import Register from "./pages/Register";
import CanteenList from "./pages/CanteenList.jsx";
import CategoryList from "./pages/CategoryList.jsx";
import ItemList from "./pages/ItemList.jsx";
import CartPage from "./pages/CartPage.jsx";
import OrdersPage from "./pages/OrdersPage.jsx";
import AdminOrdersPage from "./pages/AdminOrderPage.jsx";
import AdminRoute from "./components/AdminRoute.jsx";
import MyOrdersPage from "./pages/MyOrdersPage.jsx";
import Navbar from "./components/Navbar.jsx";
import {CartProvider} from "./context/cartContext.jsx";

export default function App() {
    return (
        <AuthProvider>
            <CartProvider>
            <BrowserRouter>
                <Navbar />
                <Routes>
                    <Route path="/" element={<Navigate to="/canteens" />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route
                        path="/canteens"
                        element={
                            <PrivateRoute>
                                <CanteenList />
                            </PrivateRoute>
                        }
                    />
                    <Route
                        path="/canteens/:canteenId/categories"
                        element={
                            <PrivateRoute>
                                <CategoryList />
                            </PrivateRoute>
                        }
                    />

                    <Route
                        path="/categories/:categoryId/items"
                        element={
                            <PrivateRoute>
                                <ItemList />
                            </PrivateRoute>
                        }
                    />
                    <Route path="/cart" element={<CartPage />} />
                    <Route
                        path="/orders"
                        element={
                            <PrivateRoute>
                                <OrdersPage />
                            </PrivateRoute>
                        }
                    />

                    <Route
                        path="/admin/orders"
                        element={
                            <PrivateRoute>
                                    <AdminOrdersPage />
                            </PrivateRoute>
                        }
                    />

                    <Route
                        path="/my-orders"
                        element={
                            <PrivateRoute>
                                <MyOrdersPage />
                            </PrivateRoute>
                        }
                    />
                </Routes>
            </BrowserRouter>
            </CartProvider>
        </AuthProvider>
    );
}
