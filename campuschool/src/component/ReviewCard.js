import {
  Chip,
  Stack,
  Card,
  CardActionArea,
  CardMedia,
  CardContent,
  Typography,
  Avatar,
  Divider,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { imgUrl } from "../constant/BaseUrl";
import { RatingStar } from "./RatingStar";
function ReviewCard(props) {
  const navigate = useNavigate();

  const goClassDetail = (id) => {
    navigate(props.url + id);
  };
  return (
    <Card onClick={() => goClassDetail(props.review.id)}>
      <CardActionArea>
        <CardMedia
          component="img"
          height="150"
          image={imgUrl(props.review.refImage)}
          sx={{ borderRadius: "5%" }}
        />
        <CardContent>
          <Stack textAlign={"left"}>
            <Stack
              direction={"row"}
              display="flex"
              justifyContent="space-between"
              alignItems="center"
            >
              <Stack>
                <Typography gutterBottom variant="body1" noWrap>
                  {props.review.title}
                </Typography>
                <Typography gutterBottom variant="body2" noWrap>
                  {props.review.teacherName}
                </Typography>
              </Stack>
              <Chip label={props.review.categoryType} />
            </Stack>
            <Stack padding={2}>
              <Divider></Divider>
            </Stack>
            <Stack
              alignItems={"left"}
              textAlign={"left"}
              spacing={1}
              paddingBottom={1}
            >
              <Stack direction={"row"} spacing={1}>
                <Avatar src={imgUrl(props.review.refImage)} />
                <Stack>
                  <Typography>{props.review.reviewer}</Typography>
                  <RatingStar rating={props.review.rating}></RatingStar>
                </Stack>
              </Stack>
              <Typography variant="body1">{props.review.content}</Typography>
              <Typography variant="subtitle2">
                {new Date(props.review.createdAt).toLocaleString()}
              </Typography>
            </Stack>
          </Stack>
        </CardContent>
      </CardActionArea>
    </Card>
  );
}
export default ReviewCard;
