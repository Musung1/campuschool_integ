// ./stores/memos.js
import create from "zustand";
import { getLectures } from "../api/LectureFormAPI";

export const useLectureSearchStore = create((set, get) => ({
  currentPage: null,
  setCurrentPage: async (num) => {
    try {
      const value = await getLectures({
        keyword: get().keyword,
        categoryType: get().categoryType,
        difficulty: get().difficulty,
        sort: get().sort + ",desc",
        page: get().pageNumber - 1,
      });
      set({ currentPage: value });
    } catch {
      set({ currentPage: {} });
    }
  },
  keyword: null,
  setKeyword: (value) => set({ keyword: value }),
  categoryType: null,
  setCategoryType: (value) => set({ categoryType: value }),
  difficulty: null,
  setDifficulty: (value) => set({ difficulty: value }),
  sort: "views",
  setSort: (value) => set({ sort: value }),
  pageNumber: 1,
  setPageNumber: (value) => set({ pageNumber: value }),
}));
