import { Stack, TextField, Typography, Button } from "@mui/material";
import { useState } from "react";
import { login } from "../api/UserAPI";
import { useNavigate } from "react-router-dom";
import useUserStore from "../store/UserStore";
import { BlankPaper } from "../component/BlankPaper";
export function LoginView() {
  const navigate = useNavigate();
  const { userDetail, user } = useUserStore();

  const goHome = () => {
    navigate("/");
  };
  const [loginForm, setLoginForm] = useState({
    username: "",
    password: "",
  });
  const loginButtonClick = async () => {
    const user = await login(loginForm);
    localStorage.setItem("user", JSON.stringify(user));
    await userDetail();
    console.log(user);
    goHome();
    window.location.reload(); // 페이지 새로 고치기
  };
  return (
    <Stack alignContent={"center"} alignItems={"center"} height={700}>
      <BlankPaper>
        <Stack spacing={2}>
          <Typography variant="h4">{"로그인"}</Typography>
          <Typography variant="h5">{"아이디"}</Typography>
          <TextField
            id="username"
            variant="outlined"
            value={loginForm.username}
            onChange={(e) =>
              setLoginForm({ ...loginForm, username: e.target.value })
            }
          />
          <Typography variant="h5">{"비밀번호"}</Typography>
          <TextField
            id="password"
            variant="outlined"
            type="password"
            value={loginForm.password}
            onChange={(e) =>
              setLoginForm({ ...loginForm, password: e.target.value })
            }
          />
          <Button variant="contained" onClick={loginButtonClick}>
            로그인
          </Button>
        </Stack>
      </BlankPaper>
    </Stack>
  );
}
