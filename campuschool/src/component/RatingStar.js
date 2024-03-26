import StarIcon from "@mui/icons-material/Star";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import { Stack } from "@mui/material";
export const RatingStar = (props) => {
  const result = [];
  for (let i = 0; i < props.rating; i++) {
    result.push(<StarIcon fontSize="1"></StarIcon>);
  }
  for (let i = props.rating; i < 5; i++) {
    result.push(<StarBorderIcon fontSize="1"></StarBorderIcon>);
  }
  return <Stack direction={"row"}>{result}</Stack>;
};
