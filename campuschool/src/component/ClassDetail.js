import { Typography,styled,Paper,Stack,Button,TextField,FormControl,IconButton,Select,MenuItem } from "@mui/material";
import React, { useState } from "react";
import useLectureFormStore from "../store/LectureFormStore";
import ImageUpload from "./ImageUpload";
export function ClassDetail() {
    const {title,setTitle,day,setDay,description,setDescription,categoryType,setCategoryType,difficulty,setDifficulty} = useLectureFormStore();
    const lectureNameChange = (event) => {
        setTitle(event.target.value)
    }
    const lectureDetailChange = (event) => {
        setDescription(event.target.value)
    }
    const dayChange = (event) => {
      setDay(event.target.value);
    };
    const categoryChange = (event) => {
        setCategoryType(event.target.value);
    };
    const difficultyChange = (event) => {
      setDifficulty(event.target.value);
    };
    return (
      <div>
        <Stack spacing={1} alignItems={"left"}textAlign={"left"} justifyContent={"left"}>
          <Typography variant="h5">강의 명</Typography>
          <TextField id="class-name" label="강의명" variant="outlined" value={title} onChange={lectureNameChange}/>
          <Typography variant="h5">대표 이미지 선택</Typography>
          <ImageUpload></ImageUpload>
          <Typography variant="h5">강의 내용</Typography>
          <TextField
              id="class-detail"
              label="강의 내용"
              multiline
              rows={4}
              value={description}
              onChange={lectureDetailChange}
            />
          <Typography variant="h5">강의 일수</Typography>
          <FormControl variant="standard" sx={{ m: 1, minWidth: 120 }}>
            <Select
              id="day-select"
              value={day}
              onChange={dayChange}
            >
              <MenuItem value={1}>1</MenuItem>
              <MenuItem value={2}>2</MenuItem>
              <MenuItem value={3}>3</MenuItem>
              <MenuItem value={4}>4</MenuItem>
              <MenuItem value={5}>5</MenuItem>
            </Select>
          </FormControl>
          <Typography variant="h5">카테고리</Typography>
          <FormControl variant="standard" sx={{ m: 1, minWidth: 120 }}>
            <Select
              id="category-select"
              value={categoryType}
              onChange={categoryChange}
            >
              <MenuItem value="VOCAL">Vocal</MenuItem>
              <MenuItem value="MUSIC">Music</MenuItem>
              <MenuItem value="COOK">Cook</MenuItem>
              <MenuItem value="EXERCISE">Exercise</MenuItem>
              <MenuItem value="CODING">Coding</MenuItem>
              <MenuItem value="PICTURE">Picture</MenuItem>
              <MenuItem value="DANCE">Dance</MenuItem>
              <MenuItem value="STAR">Star</MenuItem>
            </Select>
          </FormControl>
          <Typography variant="h5">강의 레벨</Typography>
          <FormControl variant="standard" sx={{ m: 1, minWidth: 120 }}>
            <Select
              id="difficulty-select"
              value={difficulty}
              onChange={difficultyChange}
            >
              <MenuItem value="LOW">초급</MenuItem>
              <MenuItem value="MIDDLE">중급</MenuItem>
              <MenuItem value="HIGH">고급</MenuItem>
            </Select>
          </FormControl>
        </Stack>
      </div>
    );
  }