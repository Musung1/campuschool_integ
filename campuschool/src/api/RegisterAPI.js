import { loginedUserAPIService } from "./UserAPI";

export const getRegisters = async (id) => {
  try {
    const url = "/user/open/" + id + "/register";
    const response = await loginedUserAPIService.get(url);
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const approveRegisters = async (id, registerId) => {
  try {
    const url = "/user/open/" + id + "/register/" + registerId;
    const response = await loginedUserAPIService.post(url);
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
