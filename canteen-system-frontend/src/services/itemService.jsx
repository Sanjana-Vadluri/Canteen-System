import api from "./api";

/* GET /api/categories/{id}/items */
export const getItemsByCategory = (categoryId) =>
    api.get(`/categories/${categoryId}/items`);
