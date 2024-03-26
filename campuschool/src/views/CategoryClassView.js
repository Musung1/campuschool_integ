import CategoryHeader from "../component/CategoryHeader";
import ClassGrid from "../component/ClassGrid";
import CategoryFilter from "../component/CategoryFilter";
import CategoryList from "../component/CategoryList";
import { Pagination } from "@mui/material";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { getLectures } from "../api/LectureFormAPI";
import { useLectureSearchStore } from "../store/LectureSearchStore";
function CategoryClassView() {
  let category = useParams().category;
  const {
    currentPage,
    setCategoryType,
    setCurrentPage,
    pageNumber,
    setPageNumber,
  } = useLectureSearchStore();
  useEffect(() => {
    async function getLec() {
      setCategoryType(category);
      await setCurrentPage();
    }
    getLec();
  }, [category]);

  const handleChange = (event, value) => {
    console.log(value);
    setPageNumber(value);
    console.log(pageNumber);
    setCurrentPage();
  };
  if (currentPage == null) {
    return <div>loading</div>;
  }
  return (
    <div>
      <CategoryHeader name={category}></CategoryHeader>
      <CategoryList></CategoryList>
      <CategoryFilter name={category}></CategoryFilter>
      <ClassGrid currentPage={currentPage.content} url={"/class/"}></ClassGrid>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          margin: 10,
        }}
      >
        <Pagination
          count={currentPage.totalPages}
          variant="outlined"
          shape="rounded"
          onChange={handleChange}
        />
      </div>
    </div>
  );
}

export default CategoryClassView;
