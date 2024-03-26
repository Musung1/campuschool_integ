// ./stores/memos.js
import create from 'zustand';
import { createLectureForm } from '../api/LectureFormAPI';

const useLectureFormStore = create((set) => ({
    title: "",
    setTitle: (value)=> set({title:value}),
    img: null,
    setImage: (value)=> set({img:value}),
    day : 1,
    setDay: (value)=> set({day:value}),
    description: "",
    setDescription: (value)=> set({description:value}),
    categoryType: "VOCAL",
    setCategoryType: (value)=> set({categoryType:value}),
    difficulty: "LOW",
    setDifficulty: (value)=> set({difficulty:value}),
    curriculumList: [{num:1,content:""}],
    setCurriculumList: (newCurriculumList)=>
     set((prev)=>({curriculumList: [...prev.curriculumList,newCurriculumList]})),
    updateCurriculumItem: (num, newContent) =>
    set((prev) => {
        const updatedCurriculumList = [...prev.curriculumList];
        updatedCurriculumList[num] = { ...updatedCurriculumList[num], content: newContent };
        return { curriculumList: updatedCurriculumList };
    }),
    avaliableTimeList: [],
    addAvaliableTime: (day, time) =>
        set((prev) => ({
            avaliableTimeList: [...prev.avaliableTimeList, { day, time }],
        })),
    removeAvaliableTime: (day, time) =>
        set((prev) => ({
            avaliableTimeList: prev.avaliableTimeList.filter((item) => !(item.day === day && item.time === time)),
        })),
}));

export default useLectureFormStore;