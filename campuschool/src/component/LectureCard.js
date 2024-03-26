import {
  Chip,
  Stack,
  Card,
  CardActionArea,
  CardMedia,
  CardContent,
  Typography,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { imgUrl } from "../constant/BaseUrl";
import { RatingStar } from "./RatingStar";
function LectureCard(props) {
  const navigate = useNavigate();

  const goClassDetail = (id) => {
    navigate(props.url + id);
  };
  return (
    <Card onClick={() => goClassDetail(props.lecture.id)}>
      <CardActionArea>
        <CardMedia
          component="img"
          height="150"
          image={imgUrl(props.lecture.refImage)}
          sx={{ borderRadius: "5%" }}
        />
        <CardContent>
          <Stack textAlign={"left"}>
            <Typography gutterBottom variant="body1" noWrap>
              {props.lecture.title}
            </Typography>
            <Typography gutterBottom variant="body2" noWrap>
              {props.lecture.teacherName}
            </Typography>
            <Stack direction={"row"}>
              <RatingStar rating={props.lecture.averageRating} />
              <Typography gutterBottom variant="body2" noWrap>
                {"(" + props.lecture.reviews + ")"}
              </Typography>
            </Stack>
            <Stack
              direction={"row"}
              display="flex"
              justifyContent="space-between"
              alignItems="center"
            >
              <Typography gutterBottom variant="body2" noWrap>
                {"조회수 :" + props.lecture.views}
              </Typography>
              <Chip label={props.lecture.categoryType} />
            </Stack>
          </Stack>
        </CardContent>
      </CardActionArea>
    </Card>
  );
}
export default LectureCard;
