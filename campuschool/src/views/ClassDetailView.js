import { Typography, Stack, Button, TextField } from "@mui/material";
import { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import FavoriteIcon from "@mui/icons-material/Favorite";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import { useNavigate } from "react-router-dom";
import { useLectureDetailStore } from "../store/LectureDetailStore";
import { useParams } from "react-router-dom";
import { BlankPaper } from "../component/BlankPaper";
import { imgUrl } from "../constant/BaseUrl";
import Reviews from "../component/Reviews";

function ClassDetailView() {
  const { lectureDetail, setLectureDetail } = useLectureDetailStore();
  let lectureId = useParams().id;
  useEffect(() => {
    setLectureDetail(lectureId);
  }, []);
  if (lectureDetail == null) {
    return <div>loading</div>;
  } else {
    console.log(lectureDetail);
  }
  return (
    <div>
      <BackgroundImage />
      <TeacherImage image={lectureDetail.teacherImage} />
      <TeacherDescription
        name={lectureDetail.teacherName}
        education={lectureDetail.teacherEducation}
      />
      <Stack direction="row" alignItems="flex-start">
        <BlankPaper>
          <Pages />
        </BlankPaper>
        <AnswerButtons name={lectureDetail.teacherName} id={lectureId} />
      </Stack>
    </div>
  );
}
function BackgroundImage() {
  const backImage =
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJHGPmvZI9aDaf_4tbYbOyEwTG9mRlLneB0Q&usqp=CAU";
  return (
    <div>
      <img
        src={backImage}
        alt="Image"
        style={{ opacity: 0.5, width: "100%", height: 300, zIndex: "2" }}
      />
    </div>
  );
}
function TeacherImage(props) {
  const url = imgUrl(props.image);
  console.log(url);
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "left",
        alignItems: "left",
      }}
    >
      <img
        src={url}
        style={{
          width: 150,
          height: 150,
          borderRadius: "10px",
        }}
      ></img>
    </div>
  );
}
function TeacherDescription(props) {
  return (
    <div
      style={{ display: "flex", justifyContent: "left", alignItems: "left" }}
    >
      <Stack>
        <Typography variant="h4">{props.name}</Typography>
        <Typography variant="body1">{props.education}</Typography>
      </Stack>
    </div>
  );
}
function Pages() {
  const [value, setValue] = useState("1");
  const { lectureDetail } = useLectureDetailStore();
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  console.log(lectureDetail);
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "left",
        alignItems: "left",
        margin: 10,
        width: 1000,
        height: 1000,
      }}
    >
      <Box sx={{ width: "55%", height: "300", typography: "body1" }}>
        <TabContext value={value}>
          <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
            <TabList onChange={handleChange} aria-label="lab API tabs example">
              <Tab label="강사 정보" value="1" />
              <Tab label="상세설명" value="2" />
              <Tab label="커리큘럼" value="3" />
              <Tab label="사진" value="4" />
              <Tab label="리뷰" value="5" />
            </TabList>
          </Box>
          <TabPanel value="1">
            <TeacherDetail
              description={lectureDetail.teacherDescription}
              education={lectureDetail.teacherEducation}
              history={lectureDetail.teacherHistory}
            />
          </TabPanel>
          <TabPanel value="2">
            <LectureDescription
              description={lectureDetail.lectureDescription}
            />
          </TabPanel>
          <TabPanel value="3">
            <LectureCurriculum
              curriculums={lectureDetail.curriculumEntityList}
            />
          </TabPanel>
          <TabPanel value="4">
            <LectureImage image={lectureDetail.lectureImage} />
          </TabPanel>
          <TabPanel value="5">
            <Reviews id={lectureDetail.id}></Reviews>
          </TabPanel>
        </TabContext>
      </Box>
    </div>
  );
}
function TeacherDetail(props) {
  return (
    <Stack direction={"row"} alignItems={"left"} textAlign={"left"}>
      <Stack direction={"column"}>
        <Typography variant="h6">설명</Typography>
        <TextField
          disabled
          value={props.description}
          multiline
          rows={4}
          sx={{ width: "300px" }}
        ></TextField>
        <Typography variant="h6">학력</Typography>
        <TextField
          disabled
          value={props.education}
          multiline
          rows={1}
          sx={{ width: "300px" }}
        ></TextField>
        <Typography variant="h6">경력</Typography>
        <TextField
          disabled
          value={props.history}
          multiline
          rows={4}
          sx={{ width: "300px" }}
        ></TextField>
      </Stack>
    </Stack>
  );
}
function LectureDescription(props) {
  return (
    <Stack direction={"row"} alignItems={"left"} textAlign={"left"}>
      <TextField
        disabled
        value={props.description}
        multiline
        rows={4}
        sx={{ width: "300px" }}
      ></TextField>
    </Stack>
  );
}
function LectureCurriculum(props) {
  return props.curriculums.map((curriculum) => curriculumComponent(curriculum));
}
function curriculumComponent(curriculum) {
  const id = curriculum.num + "label";
  return (
    <Stack key={curriculum.num} alignItems={"left"} textAlign={"left"}>
      <Typography variant="h5">{curriculum.num + "회차"}</Typography>
      <TextField
        id={id}
        disabled
        rows={2}
        variant="outlined"
        value={curriculum.content}
      />
    </Stack>
  );
}
function LectureImage(props) {
  const url = imgUrl(props.image);
  return (
    <Stack alignItems={"left"} textAlign={"left"}>
      <img
        src={url}
        style={{
          width: 300,
          height: 300,
          borderRadius: "10px",
        }}
      ></img>
    </Stack>
  );
}
function AnswerButtons(props) {
  const navigate = useNavigate();
  const goRegisterClass = (id) => {
    navigate("/class/register/" + id);
  };
  return (
    <div style={{ display: "flex", justifyContent: "center" }}>
      <Stack alignItems="center" justifyContent="center">
        <Typography variant="h6">
          {props.name + " 강사님께 질문해 보아요"}
        </Typography>
        <Stack direction={"row"} spacing={1}>
          <Button
            style={{ width: "100px", height: "50px", fontSize: "20px" }}
            component="label"
            role={undefined}
            variant="contained"
            tabIndex={-1}
          >
            <FavoriteIcon />
          </Button>
          <Button
            style={{ width: "200px", height: "50px", fontSize: "20px" }}
            component="label"
            role={undefined}
            variant="contained"
            tabIndex={-1}
            width="200"
          >
            질문하기
          </Button>
        </Stack>
        <Button
          style={{
            width: "310px",
            height: "50px",
            fontSize: "20px",
            margin: 10,
          }}
          component="label"
          role={undefined}
          variant="contained"
          tabIndex={-1}
          width="200"
          onClick={() => goRegisterClass(props.id)}
        >
          강의 신청하기
        </Button>
      </Stack>
    </div>
  );
}

export default ClassDetailView;
