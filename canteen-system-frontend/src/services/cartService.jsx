import api from "./api";

export const addToCart = (itemId, qty = 1) =>
    api.post("/cart/add", null, { params: { itemId, qty } });

export const getCart = () => api.get("/cart");

export const updateQty = (itemId, qty) =>
    api.put(`/cart/${itemId}`, null, { params: { qty } });

export const removeItem = (itemId) => api.delete(`/cart/${itemId}`);

export const clearCart = () => api.delete("/cart/clear");
