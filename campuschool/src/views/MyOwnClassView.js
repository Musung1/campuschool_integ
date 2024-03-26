import { Typography, styled, Paper } from "@mui/material";
import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import { useState, useEffect } from "react";
import ClassGrid from "../component/ClassGrid";
import { useLectureCardStore } from "../store/LectureCardStore";

function MyOwnClassView() {
  const CategoryPaper = styled(Paper)(({ theme }) => ({
    height: 1000,
    padding: theme.spacing(2),
    ...theme.typography.h2,
    textAlign: "center",
    margin: 10,
  }));
  return (
    <div>
      <CategoryPaper>
        <Typography variant="h3">내가 보유한 강의</Typography>
        {MyOwnTab()}
      </CategoryPaper>
    </div>
  );
}
function MyOwnTab() {
  const [value, setValue] = useState("1");
  const {
    myRegisterLectures,
    myWaitLectures,
    setMyRegisterLectures,
    setMyWaitLectures,
  } = useLectureCardStore();
  useEffect(() => {
    setMyRegisterLectures();
    setMyWaitLectures();
  }, []);
  if (myRegisterLectures == null || myWaitLectures == null) {
    return <div>loading</div>;
  }
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "left",
        alignItems: "left",
        margin: 10,
      }}
    >
      <Box sx={{ width: "100%", typography: "body1" }}>
        <TabContext value={value}>
          <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
            <TabList onChange={handleChange} aria-label="lab API tabs example">
              <Tab label="현재 수강중" value="1" />
              <Tab label="신청 대기중" value="2" />
              <Tab label="수강 완료" value="3" />
            </TabList>
          </Box>
          <TabPanel value="1">
            {" "}
            <ClassGrid
              currentPage={myRegisterLectures}
              url={"/user/register/"}
            ></ClassGrid>
          </TabPanel>
          <TabPanel value="2">
            <ClassGrid currentPage={myWaitLectures} url={"/class/"}></ClassGrid>
          </TabPanel>
          <TabPanel value="3">Item Two</TabPanel>
        </TabContext>
      </Box>
    </div>
  );
}

export default MyOwnClassView;
