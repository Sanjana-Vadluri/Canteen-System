import api from "./api";

/* GET /api/orders/all */
export const getAllOrders = () => api.get("/orders");

/* PUT /api/orders/{id}/status?value=NEW_STATUS */
export const updateOrderStatus = (orderId, status) =>
    api.put(`/orders/${orderId}/status`, null, {
        params: { status: status },
    });
