import api from "./api";

/* GET /api/canteens/{id}/categories  */
export const getCategoriesByCanteen = (canteenId) =>
    api.get(`/canteens/${canteenId}/categories`);
