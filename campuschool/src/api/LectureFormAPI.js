import { apiService, fileAPIService } from "./UserAPI";

export const createLectureForm = async (lectureForm) => {
  try {
    const response = await fileAPIService.post("/class/open", lectureForm);
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    console.error(lectureForm);
    //throw error;
  }
};
export const getPopularLectures = async () => {
  try {
    const response = await fileAPIService.get("/class/popular");
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const getRecentLectures = async () => {
  try {
    const response = await fileAPIService.get("/class/recent");
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const getLectures = async (searchParam) => {
  try {
    const response = await fileAPIService.get("/class", {
      params: searchParam,
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const getMyOpenLectures = async () => {
  try {
    const response = await fileAPIService.get("/class/open");
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const getMyRegisterLectures = async () => {
  try {
    const response = await fileAPIService.get("/class/register");
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const getMyWaitLectures = async () => {
  try {
    const response = await fileAPIService.get("/class/wait");
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const getLectureDetail = async (id) => {
  console.log(id);
  try {
    const url = "/class/";
    const response = await fileAPIService.get("/class/" + id);
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
export const registerLecture = async (id) => {
  try {
    const url = "/class/";
    const response = await fileAPIService.post("/class/" + id);
    return response.data;
  } catch (error) {
    console.error("Error fetching lecture:", error);
    //throw error;
  }
};
