import { Typography } from "@mui/material";
import { useState } from "react";
import {Stack, TextField, Button, Chip, FormControl,InputLabel,Select,MenuItem,Box} from "@mui/material";
import MyBreadCrum from "../component/MyBreadCrum";
import { useLectureSearchStore } from "../store/LectureSearchStore";
function CategoryFilter(props) {
    const [search,setSearch] = useState("");
    const {difficulty, setDifficulty, setCurrentPage,sort, setSort,keyword, setKeyword} = useLectureSearchStore();
    const handleClick = (value) => {
        if(difficulty === value ) {
            setDifficulty(null)
        } else {
            setDifficulty(value)
        }
        setCurrentPage()
    }
    const handleChange = (event) => {
        setSort(event.target.value)
        console.log(event.target.value)
        setCurrentPage()
    };
    const searchChange = (event) => {
        setSearch(event.target.value)
    }
    const searchButtonClick = () => {
        setKeyword(search)
        console.log(keyword)
        setCurrentPage()
    }
    return (
        <div style={{margin: 10}}>
            <Stack direction="row" justifyContent="space-between">
            <MyBreadCrum name={props.name}></MyBreadCrum>
                <Stack direction={"row"} spacing={1}>
                    <TextField id="outlined-basic"
                    value={search}
                    onChange={searchChange}
                     variant="outlined" size="small"sx={{ m: 1, width: '20ch' }} />
                    <Button variant="contained"
                    onClick={searchButtonClick}>검색</Button>
                </Stack>
            </Stack>
            <div style={{margin:10}}>
            <Stack direction="row" spacing={1}>
                <Chip label="입문" color= {difficulty === "LOW" ? "primary" : "default" } onClick={()=>handleClick("LOW")} />
                <Chip label="중급" color= {difficulty === "MIDDLE" ? "primary" : "default" }onClick={()=>handleClick("MIDDLE")} />
                <Chip label="고급" color= {difficulty === "HIGH" ? "primary" : "default" } onClick={()=>handleClick("HIGH")} />
            </Stack>
            </div>
            <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
                <Box sx={{ maxWidth: 100}}>
                <FormControl fullWidth>
                    <Select
                    style={{ height: '40px' }}
                    value= {sort}
                    onChange={handleChange}
                    >
                    <MenuItem value={"views"}>조회수</MenuItem>
                    <MenuItem value={"createdAt"}>생성일</MenuItem>
                    </Select>
                </FormControl>
                </Box>
            </div>
        </div>
    );
}
export default CategoryFilter;