import { loginedUserAPIService } from "./UserAPI";
export const getReviews = async (id) => {
  try {
    const url = "/class/" + id + "/review";
    const response = await loginedUserAPIService.get(url, {
      params: {
        sort: "createdAt,desc",
        page: 0,
        size: 5,
      },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const postReview = async (id, reviewForm) => {
  try {
    const url = "/class/" + id + "/review";
    const response = await loginedUserAPIService.post(url, reviewForm);
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const getRecentReviews = async () => {
  try {
    const url = "/class/reviews";
    const response = await loginedUserAPIService.get(url);
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
