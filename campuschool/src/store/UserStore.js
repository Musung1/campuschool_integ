import create from "zustand";
import {
  getUser,
  modifyDescription,
  modifyImg,
  modifyName,
} from "../api/UserAPI";
const useUserStore = create((set) => ({
  user: null,
  img: null,
  setImage: async (value) => {
    set({ img: value });
    await modifyImg(value);
  },
  setName: async (name) => {
    await modifyName(name);
  },
  setDescription: async (description) => {
    await modifyDescription(description);
  },
  clearUser: () => {
    set({ user: null });
  },
  setUser: (userDTO) => {
    set({
      user: userDTO,
    });
  },
  userDetail: async () => {
    try {
      const userDTO = await getUser();
      localStorage.setItem("user", JSON.stringify(userDTO));
      set({
        user: {
          id: userDTO.id,
          name: userDTO.name,
          img: userDTO.img,
          description: userDTO.description,
          portfolioImg: userDTO.portfolioImg,
          roleType: userDTO.roleType,
        },
      });
    } catch {
      localStorage.setItem("user", null);
      console.log("error");
    }
  },
}));
export default useUserStore;
