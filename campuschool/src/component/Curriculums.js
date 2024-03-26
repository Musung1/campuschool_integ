import { Typography,styled,Paper,Stack,Button,TextField,FormControl,IconButton,Select,MenuItem } from "@mui/material";
import React, { useState } from "react";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import useLectureFormStore from "../store/LectureFormStore";
import { useEffect } from "react";
export function Curriculums() {
    const [num, setNum] = useState(2)
    const {curriculumList,setCurriculumList,updateCurriculumItem} = useLectureFormStore();
    const [localCurriculumList, setLocalCurriculumList] = useState(curriculumList);
    useEffect(() => {
        // 상태가 업데이트될 때마다 로컬 상태도 업데이트합니다.
        setLocalCurriculumList(curriculumList);
    }, [curriculumList]);
    const addCurriculum = () => {
        console.log(curriculumList)
      setCurriculumList({num:num,content:""})
      setNum(num+1)
    }
    return (
      <div>
        <Stack spacing={1} alignItems={"left"}textAlign={"left"} justifyContent={"left"}>
            {localCurriculumList.map((curriculum)=>curriculumComponent(curriculum))}
          <Stack direction={"row"} spacing={1}>
            <IconButton style={{width: 30}} onClick={addCurriculum}>
              <AddCircleOutlineIcon></AddCircleOutlineIcon>
            </IconButton>
            <Typography variant="h6">추가하기</Typography>
          </Stack>  
        </Stack>
      </div>
  
    );
    function curriculumComponent(curriculum) {
        const id = curriculum.num + "label"
        const handleUpdateCurriculum = (num, newContent) => {
            const updatedList = [...localCurriculumList];
            updatedList[num] = { ...updatedList[num], content: newContent };
            setLocalCurriculumList(updatedList);
            updateCurriculumItem(num, newContent);
        };
        const handleChange = (e) => {
            console.log(e.target.value)
            updateCurriculumItem(curriculum.num,e.target.value)
        }
        return(
          <Stack key={curriculum.num}>
            <Typography variant="h5">{curriculum.num + "회차"}</Typography>
            <TextField id= {id} label="내용" variant="outlined" value={curriculum.content} onChange={(e)=>handleUpdateCurriculum(curriculum.num-1,e.target.value)} />
          </Stack>
        );
      }
  }