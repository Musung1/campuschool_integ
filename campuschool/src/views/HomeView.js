import Header from "../component/Header";
import CategoryHeader from "../component/CategoryHeader";
import CarouselView from "../component/CarouselView";
import CategoryList from "../component/CategoryList";
import { useLectureCardStore } from "../store/LectureCardStore";
import { useEffect } from "react";
import ReviewCarousel from "../component/ReviewCarousel";
import { Stack } from "@mui/material";
function Home() {
  const { setPopularLectures, setRecentLectures } = useLectureCardStore();
  useEffect(() => {
    setPopularLectures();
    setRecentLectures();
  }, []);
  return (
    <Stack spacing={4}>
      <CategoryHeader name="home"></CategoryHeader>
      <CategoryList></CategoryList>
      <CarouselView title="한동대 인기 클래스" type="popular" url={"/class/"} />
      <CarouselView
        title="신규 클래스"
        type="recent"
        url={"/class/"}
      ></CarouselView>
      <ReviewCarousel title="최신 수업 리뷰" url={"/class/"}></ReviewCarousel>
    </Stack>
  );
}

export default Home;
