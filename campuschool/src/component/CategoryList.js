import { Stack } from "@mui/material";
import CategoryIcon from "./CategoryIcon";

function CategoryList(props) {
  const categories = [
    "전체",
    "보컬",
    "음악",
    "요리",
    "운동",
    "코딩",
    "촬영",
    "댄스",
    "기타",
  ];
  const list = categories.map((category) => (
    <CategoryIcon name={category}></CategoryIcon>
  ));
  return (
    <Stack
      direction={"row"}
      margin={5}
      spacing={3}
      alignItems={"center"}
      textAlign={"center"}
      justifyContent={"center"}
    >
      {list}
    </Stack>
  );
}
export default CategoryList;
