import { Typography, styled, Paper } from "@mui/material";
import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import TabContext from "@mui/lab/TabContext";
import TabList from "@mui/lab/TabList";
import TabPanel from "@mui/lab/TabPanel";
import { useState } from "react";
import NotificationTable from "../component/NotificationTable";
import RegisterRequesetTable from "../component/RegisterRequestTable";
import ChatRoom from "../component/ChatRoom";
import { useParams } from "react-router-dom";

function MyOpenClassRoomView() {
  const lectureId = useParams().id;
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
        <Typography variant="h3">강의실</Typography>
        <MyOpenClassRoomTab id={lectureId} />
      </CategoryPaper>
    </div>
  );
}
function MyOpenClassRoomTab(props) {
  const [value, setValue] = useState("1");

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
              <Tab label="공지" value="1" />
              <Tab label="강의요청" value="2" />
              <Tab label="회원 채팅룸" value="3" />
            </TabList>
          </Box>
          <TabPanel value="1">
            <NotificationTable id={props.id} role="teacher" />
          </TabPanel>
          <TabPanel value="2">
            <RegisterRequesetTable id={props.id} />
          </TabPanel>
          <TabPanel value="3">
            <ChatRoom />
          </TabPanel>
        </TabContext>
      </Box>
    </div>
  );
}

export default MyOpenClassRoomView;
