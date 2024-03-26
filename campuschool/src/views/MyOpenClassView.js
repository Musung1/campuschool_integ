import { Typography, styled, Paper } from "@mui/material";
import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import { useEffect, useState } from "react";
import { useLectureCardStore } from "../store/LectureCardStore";
import ClassGrid from "../component/ClassGrid";

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
        <Typography variant="h3">내가 개설한 강의</Typography>
        {MyOpenTab()}
      </CategoryPaper>
    </div>
  );
}
function MyOpenTab() {
  const [value, setValue] = useState("1");
  const { myOpenLectures, setMyOpenLectures } = useLectureCardStore();
  useEffect(() => {
    setMyOpenLectures();
    console.log(myOpenLectures);
  }, []);
  if (myOpenLectures == null) {
    return <div>loading</div>;
  }
  const classClick = () => {};
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
              <Tab label="진행중인 강의" value="1" />
              <Tab label="종료된 강의" value="2" />
            </TabList>
          </Box>
          <TabPanel value="1">
            <ClassGrid
              currentPage={myOpenLectures}
              url={"/user/open/"}
            ></ClassGrid>
          </TabPanel>
          <TabPanel value="2">Item Two</TabPanel>
        </TabContext>
      </Box>
    </div>
  );
}

export default MyOwnClassView;
