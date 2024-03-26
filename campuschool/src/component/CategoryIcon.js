import MicIcon from "@mui/icons-material/Mic";
import DashboardIcon from "@mui/icons-material/Dashboard";
import MusicNoteIcon from "@mui/icons-material/MusicNote";
import LunchDiningIcon from "@mui/icons-material/LunchDining";
import FitnessCenterIcon from "@mui/icons-material/FitnessCenter";
import ComputerIcon from "@mui/icons-material/Computer";
import ColorLensIcon from "@mui/icons-material/ColorLens";
import AccessibilityNewIcon from "@mui/icons-material/AccessibilityNew";
import DarkModeIcon from "@mui/icons-material/DarkMode";
import PhotoCameraIcon from "@mui/icons-material/PhotoCamera";
import { Typography, Box, Button, Icon } from "@mui/material";
import { amber } from "@mui/material/colors";
import { useNavigate } from "react-router-dom";
const categoryConverter = (category) => {
  switch (category) {
    case "전체":
      return "";
    case "보컬":
      return "VOCAL";
    case "음악":
      return "MUSIC";
    case "요리":
      return "COOK";
    case "운동":
      return "EXERCISE";
    case "코딩":
      return "CODING";
    case "촬영":
      return "PICTURE";
    case "댄스":
      return "DANCE";
    case "기타":
      return "STAR";
  }
};
const imageConverter = (name) => {
  const style = { color: amber[500], fontSize: 35 };
  switch (categoryConverter(name)) {
    case "":
      return <DashboardIcon style={style} />;
    case "VOCAL":
      return <MicIcon style={style} />;
    case "MUSIC":
      return <MusicNoteIcon style={style} />;
    case "COOK":
      return <LunchDiningIcon style={style} />;
    case "EXERCISE":
      return <FitnessCenterIcon style={style} />;
    case "CODING":
      return <ComputerIcon style={style} />;
    case "PICTURE":
      return <PhotoCameraIcon style={style} />;
    case "DANCE":
      return <AccessibilityNewIcon style={style} />;
    case "STAR":
      return <DarkModeIcon style={style} />;
  }
};
function CategoryIcon(props) {
  const navigate = useNavigate();

  const goCategoryDetail = (name) => {
    navigate("/classes/" + name);
  };
  return (
    <Button
      onClick={() => goCategoryDetail(categoryConverter(props.name))}
      color="secondary"
      variant="null"
      sx={{ borderRadius: "20px" }}
    >
      <Box textAlign="center">
        {imageConverter(props.name)}
        <Typography variant="body2">{props.name}</Typography>
      </Box>
    </Button>
  );
}
export default CategoryIcon;
