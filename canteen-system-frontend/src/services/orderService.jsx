import api from "./api";

/* POST /api/orders/place */
export const placeOrder = () => api.post("/orders/place");

/* GET /api/orders/mine */
export const getMyOrders = () => api.get("/orders/mine");
