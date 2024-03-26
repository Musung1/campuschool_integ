import { loginedUserAPIService } from "./UserAPI";

export const getNotifications = async (id) => {
  try {
    const url = "/user/open/" + id + "/notification";
    const response = await loginedUserAPIService.get(url);
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const postNotifications = async (id, notificationForm) => {
  try {
    const url = "/user/open/" + id + "/notification";
    const response = await loginedUserAPIService.post(url, notificationForm);
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
