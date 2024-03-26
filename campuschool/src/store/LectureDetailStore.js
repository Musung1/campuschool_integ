// ./stores/memos.js
import create from "zustand";
import { getLectureDetail } from "../api/LectureFormAPI";

export const useLectureDetailStore = create((set) => ({
  lectureDetail: null,
  setLectureDetail: async (id) => {
    const lecture = await getLectureDetail(id);
    set((value) => ({ lectureDetail: lecture }));
  },
}));
