import create from "zustand";
import {
  getPopularLectures,
  getRecentLectures,
  getMyOpenLectures,
  getMyRegisterLectures,
  getMyWaitLectures,
} from "../api/LectureFormAPI";

export const useLectureCardStore = create((set) => ({
  popularLectures: [],
  recentLectures: [],
  myOpenLectures: null,
  myRegisterLectures: null,
  myWaitLectures: null,
  setPopularLectures: async () => {
    const popularLectures = await getPopularLectures();
    set((prev) => ({ ...prev, popularLectures: popularLectures }));
  },
  setRecentLectures: async () => {
    const recentLectures = await getRecentLectures();
    set((prev) => ({ ...prev, recentLectures: recentLectures }));
  },
  setMyOpenLectures: async () => {
    const myOpenLectures = await getMyOpenLectures();
    set((prev) => ({ ...prev, myOpenLectures: myOpenLectures }));
  },
  setMyRegisterLectures: async () => {
    const myOpenLectures = await getMyRegisterLectures();
    set((prev) => ({ ...prev, myRegisterLectures: myOpenLectures }));
  },
  setMyWaitLectures: async () => {
    const myOpenLectures = await getMyWaitLectures();
    set((prev) => ({ ...prev, myWaitLectures: myOpenLectures }));
  },
}));
