import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { Navigate } from "react-router-dom";

export default function AdminRoute({ children }) {
    const { user } = useContext(AuthContext);
    return user && (user.role === "ADMIN" || user.role === "KITCHEN") ? (
        children
    ) : (
        <Navigate to="/" replace />
    );
}
