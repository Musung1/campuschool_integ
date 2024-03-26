import LectureCard from "./LectureCard";
import { Stack, Button, Paper, Typography } from "@mui/material";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import React from "react";
import Slider from "react-slick";
import { useLectureCardStore } from "../store/LectureCardStore";
function CarouselView(props) {
  const { popularLectures, recentLectures } = useLectureCardStore();
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1,
  };
  const contents = () => {
    var lectures = [];
    if (props.type === "popular") {
      if (popularLectures == null) return null;
      lectures = popularLectures;
    } else if (props.type === "recent") {
      if (recentLectures == null) return null;
      lectures = recentLectures;
    }
    console.log(lectures);
    return lectures.map((lecture) => (
      <div style={{ margin: "20px" }}>
        <LectureCard lecture={lecture} url={props.url}></LectureCard>
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
