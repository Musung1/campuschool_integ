import {
  Typography,
  styled,
  Paper,
  Button,
  Stack,
  TextField,
} from "@mui/material";
import useUserStore from "../store/UserStore";
import { useEffect, useState } from "react";
import ProfileImage from "../component/ProfileImage";
function ProfileView() {
  const {
    userDetail,
    user,
    name,
    description,
    history,
    education,
    setName,
    setDescription,
    setHistory,
    setEducation,
  } = useUserStore();
  useEffect(() => {
    userDetail();
  }, []);
  const CategoryPaper = styled(Paper)(({ theme }) => ({
    height: 1000,
    ...theme.typography.h2,
  }));
  return (
    <CategoryPaper>
      <Typography variant="h3" padding={2}>
        내 프로필
      </Typography>
      {user.id != null ? (
        <Stack direction={"column"} alignItems={"center"} textAlign={"center"}>
          <Stack direction={"column"}>
            <ProfileImage></ProfileImage>
          </Stack>
          <Stack width={1000} textAlign={"left"} alignItems={"center"}>
            <UserNameChange></UserNameChange>
            <UserDescriptionChange></UserDescriptionChange>
          </Stack>
        </Stack>
      ) : null}
    </CategoryPaper>
  );
}
function UserDescriptionChange() {
  const { userDetail, user, setDescription } = useUserStore();
  const [description, setDes] = useState(
    user.description == null ? "" : user.description.description
  );
  const [history, setHistory] = useState(
    user.description == null ? "" : user.description.history
  );
  const [education, setEducation] = useState(
    user.description == null ? "" : user.description.education
  );
  const [descriptionChange, setDescriptionChange] = useState(false);
  const changeDescription = async () => {
    console.log("hello");
    const value = {
      history: history,
      description: description,
      education: education,
    };
    console.log(value);
    await setDescription(value);
    await userDetail();
    setDescriptionChange(false);
  };
  return (
    <div>
      {descriptionChange ? (
        <Stack direction={"row"}>
          <Stack direction={"column"}>
            <Typography variant="h6">설명</Typography>
            <TextField
              value={description}
              onChange={(e) => setDes(e.target.value)}
              multiline
              rows={4}
              sx={{ width: "300px" }}
            ></TextField>
            <Typography variant="h6">학력</Typography>
            <TextField
              value={education}
              onChange={(e) => setEducation(e.target.value)}
              multiline
              rows={1}
              sx={{ width: "300px" }}
            ></TextField>
            <Typography variant="h6">경력</Typography>
            <TextField
              value={history}
              onChange={(e) => setHistory(e.target.value)}
              multiline
              rows={4}
              sx={{ width: "300px" }}
            ></TextField>
          </Stack>
          <Button onClick={async () => changeDescription()}>완료</Button>
        </Stack>
      ) : (
        <Stack direction={"row"}>
          <Stack direction={"column"}>
            <Typography variant="h6">설명</Typography>
            <TextField
              disabled
              multiline
              rows={4}
              value={
                user.description == null ? "" : user.description.description
              }
              sx={{ width: "300px" }}
            ></TextField>
            <Typography variant="h6">학력</Typography>
            <TextField
              disabled
              multiline
              rows={1}
              value={user.description == null ? "" : user.description.education}
              sx={{ width: "300px" }}
            ></TextField>
            <Typography variant="h6">경력</Typography>
            <TextField
              disabled
              multiline
              rows={4}
              value={user.description == null ? "" : user.description.history}
              sx={{ width: "300px" }}
            ></TextField>
          </Stack>
          <Button onClick={() => setDescriptionChange(true)}>수정</Button>
        </Stack>
      )}
    </div>
  );
}
function UserNameChange() {
  const { user, setName, userDetail } = useUserStore();
  const [nameChange, setNameChange] = useState(false);
  const [userName, setUserName] = useState(user.name);

  return (
    <div>
      {nameChange ? (
        <Stack direction={"row"}>
          <Stack direction={"column"}>
            <Typography variant="h6">이름</Typography>
            <Stack direction={"row"}>
              <TextField
                sx={{ width: "300px", height: "60px" }}
                value={userName}
                onChange={(e) => setUserName(e.target.value)}
              />
            </Stack>
          </Stack>
          <Button
            onClick={async () => {
              console.log(userName);
              const userDTO = await setName(userName);
              await userDetail();
              setNameChange(false);
            }}
          >
            완료
          </Button>
        </Stack>
      ) : (
        <Stack direction={"row"}>
          <Stack direction={"column"}>
            <Typography variant="h6">이름</Typography>
            <Stack direction={"row"}>
              <TextField
                disabled
                sx={{ width: "300px", height: "60px" }}
                value={user.name}
              />
            </Stack>
          </Stack>
          <Button onClick={() => setNameChange(true)}>수정</Button>
        </Stack>
      )}
    </div>
  );
}
export default ProfileView;
