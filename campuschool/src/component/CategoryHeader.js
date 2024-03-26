import { Paper } from "@mui/material";
import { styled } from "@mui/material/styles";
import swimBanner from "../assets/banner/swimbanner.jpeg";
import banner1 from "../assets/banner/banner1.png";
import banner2 from "../assets/banner/banner2.png";
import banner3 from "../assets/banner/banner3.png";
import CardMedia from "@mui/material/CardMedia";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import React, { useEffect, useState } from "react";
import Slider from "react-slick";
const CategoryPaper = styled(Paper)(({ theme }) => ({
  height: 200,
  padding: theme.spacing(2),
  ...theme.typography.h2,
  textAlign: "center",
  alignItems: "center",
  alignContent: "center",
  margin: 10,
}));
const images = () => {
  const names = [banner1, banner2, banner3];
  return names.map((name) => (
    <img
      src={name}
      height="200"
      width={"100%"}
      style={{ borderRadius: "2%" }}
    ></img>
  ));
};
function CategoryHeader(props) {
  const settings = {
    dots: false,
    infinite: true,
    speed: 2000, // 슬라이드 전환 속도 (밀리초)
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true, // 자동 재생 설정
    autoplaySpeed: 4000, // 자동 재생 간격 (밀리초)
  };
  return <Slider {...settings}>{images()}</Slider>;
}
export default CategoryHeader;
