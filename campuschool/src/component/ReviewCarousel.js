import LectureCard from "./LectureCard";
import { Stack, Button, Paper, Typography } from "@mui/material";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import React, { useEffect, useState } from "react";
import Slider from "react-slick";
import { useLectureCardStore } from "../store/LectureCardStore";
import ReviewCard from "./ReviewCard";
import { getRecentReviews } from "../api/ReviewAPI";
function CarouselView(props) {
  const [recentReviews, setRecentReviews] = useState(null);

  const settings = {
    arrows: true,
    dots: true,
    infinite: true,
    slidesToShow: 3,
    slidesToScroll: 1,
    speed: 500,
  };
  useEffect(() => {
    async function init() {
      setRecentReviews(await getRecentReviews());
    }
    init();
  }, []);
  if (recentReviews == null) return null;
  const contents = () => {
    return recentReviews.map((review) => (
      <div style={{ margin: "20px" }}>
        <ReviewCard
          review={review}
          url={props.url}
          style={{ margin: "0 10px" }}
        ></ReviewCard>
      </div>
    ));
  };
  return (
    <Stack textAlign={"left"} spacing={4}>
      <Typography variant="h5" sx={{ fontWeight: "bold" }}>
        {props.title}
      </Typography>
      <div className="slider-container">
        <Slider {...settings}>{contents()}</Slider>
      </div>
    </Stack>
  );
}

export default CarouselView;
