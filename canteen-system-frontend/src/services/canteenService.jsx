import api from "./api";

/* GET /api/canteens  → returns [{id, name, location}] */
export const getAllCanteens = () => api.get("/canteens");
