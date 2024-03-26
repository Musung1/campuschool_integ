import * as React from "react";
import {
  Pagination,
  Stack,
  Button,
  Divider,
  TextField,
  FormControl,
  MenuItem,
  Select,
  Typography,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import List from "@mui/material/List";
import Avatar from "@mui/material/Avatar";
import ImageIcon from "@mui/icons-material/Image";
import { RatingStar } from "./RatingStar";
import { useState } from "react";
import { postReview, getReviews } from "../api/ReviewAPI";
import { baseUrl, imgUrl } from "../constant/BaseUrl";

export default function Reviews(props) {
  const navigate = useNavigate();
  const [reviews, setReviews] = React.useState(null);
  useEffect(() => {
    console.log(props.id);
    async function init() {
      const reviewList = await getReviews(props.id);
      setReviews(reviewList.content);
      console.log(reviewList.content);
    }
    init();
  }, []);
  if (reviews == null) {
    return <div>loading</div>;
  }
  return (
    <Stack alignItems={"left"} textAlign={"left"} spacing={2}>
      <List sx={{ width: "100%", maxWidth: 360, bgcolor: "background.paper" }}>
        {reviews.map((review) => (
          <ReviewItem review={review} />
        ))}
        <Typography variant="h5" paddingBottom={2} paddingTop={3}>
          리뷰 작성하기
        </Typography>
        <WriteReview id={props.id}></WriteReview>
      </List>
      <Pagination
        count={1}
        variant="outlined"
        shape="rounded"
        // onChange={handleChange}
      />
    </Stack>
  );
}
function ReviewItem(props) {
  return (
    <Stack alignItems={"left"} textAlign={"left"} spacing={1} paddingBottom={1}>
      <Stack direction={"row"} spacing={1}>
        <Avatar src={imgUrl(props.review.userImg)} />
        <Stack>
          <Typography>{props.review.name}</Typography>
          <RatingStar rating={props.review.rating}></RatingStar>
        </Stack>
      </Stack>
      <Typography variant="body1">{props.review.content}</Typography>
      <Typography variant="subtitle2">
        {new Date(props.review.createdAt).toLocaleString()}
      </Typography>
      <Divider></Divider>
    </Stack>
  );
}
function WriteReview(props) {
  const [rating, setRating] = useState(5);
  const [content, setContent] = useState("");
  const writeButtonClick = async () => {
    const id = props.id;
    const reviewForm = {
      content: content,
      rating: rating,
    };
    const reviewDTO = await postReview(id, reviewForm);
    console.log(reviewDTO);
    window.location.reload();
  };
  const ratingChange = (event) => {
    setRating(event.target.value);
  };
  const contentChange = (event) => {
    setContent(event.target.value);
  };
  return (
    <Stack spacing={1}>
      <FormControl variant="standard" sx={{ m: 1, minWidth: 120 }}>
        <Select id="day-select" value={rating} onChange={ratingChange}>
          <MenuItem value={1}>
            <RatingStar rating={1}></RatingStar>
          </MenuItem>
          <MenuItem value={2}>
            <RatingStar rating={2}></RatingStar>
          </MenuItem>
          <MenuItem value={3}>
            <RatingStar rating={3}></RatingStar>
          </MenuItem>
          <MenuItem value={4}>
            <RatingStar rating={4}></RatingStar>
          </MenuItem>
          <MenuItem value={5}>
            <RatingStar rating={5}></RatingStar>
          </MenuItem>
        </Select>
      </FormControl>
      <TextField value={content} onChange={contentChange}></TextField>
      <Button variant="outlined" onClick={writeButtonClick}>
        리뷰 작성
      </Button>
    </Stack>
  );
}
