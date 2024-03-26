import Header from "./component/Header";
import CategoryClassView from "./views/CategoryClassView";
import Home from "./views/HomeView";
import Footer from "./component/Footer";
import { Paper, styled } from "@mui/material";
import { Routes, Route } from "react-router-dom";
import ClassDetailView from "./views/ClassDetailView";
import MyOwnClassView from "./views/MyOwnClassView";
import MyOpenClassView from "./views/MyOpenClassView";
import MyRegisterClassRoomView from "./views/MyRegisterRoomView";
import MyOpenClassRoomView from "./views/MyOpenClassRoomView";
import OpenClassView from "./views/OpenClassView";
import RegisterClassView from "./views/RegisterClassView";
import { LoginView } from "./views/LoginView";
import { SignUpView } from "./views/SignUpView";
import ProfileView from "./views/\bProfileView";
import { CreateNotificationView } from "./views/CreateNotificationView";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { amber } from "@mui/material/colors";

const theme = createTheme({
  palette: {
    primary: {
      main: amber[500],
    },
    secondary: {
      main: amber[100],
    },
  },
});
function App() {
  const CategoryPaper = styled(Paper)(({ theme }) => ({
    width: "65%",
    maxWidth: "500",
    textAlign: "center",
  }));
  return (
    <ThemeProvider theme={theme}>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <CategoryPaper>
          <Header></Header>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<LoginView />} />
            <Route path="/signup" element={<SignUpView />} />
            <Route path="/profile" element={<ProfileView />} />
            <Route path="/classes" element={<CategoryClassView />} />
            <Route path="/classes/:category" element={<CategoryClassView />} />
            <Route path="/class/:id" element={<ClassDetailView />} />
            <Route path="/class/register/:id" element={<RegisterClassView />} />
            <Route path="/class/open" element={<OpenClassView />} />
            <Route path="/user/register" element={<MyOwnClassView />} />
            <Route
              path="/user/register/:id"
              element={<MyRegisterClassRoomView />}
            />
            <Route path="/user/open" element={<MyOpenClassView />} />
            <Route path="/user/open/:id" element={<MyOpenClassRoomView />} />
            <Route
              path="/user/open/:id/notification"
              element={<CreateNotificationView />}
            />
          </Routes>
          <Footer></Footer>
        </CategoryPaper>
      </div>
    </ThemeProvider>
  );
}

export default App;
