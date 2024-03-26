import LectureCard from "./LectureCard";
import { Grid } from "@mui/material";
import { useLectureCardStore } from "../store/LectureCardStore";
import { useLectureSearchStore } from "../store/LectureSearchStore";
function ClassGrid(props) {
  const contents = () => {
    var lectures = [];
    if (props.currentPage != null) {
      lectures = props.currentPage;
    }
    console.log(lectures);
    return lectures.map((lecture) => (
      <LectureCard lecture={lecture} url={props.url}></LectureCard>
    ));
  };
  return (
    <div>
      <Grid container rowSpacing={5} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
        {contents().map((item) => (
          <Grid item xs={3}>
            {item}
          </Grid>
        ))}
      </Grid>
    </div>
  );
}
export default ClassGrid;
