import {
  Table,
  TableContainer,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  Button,
} from "@mui/material";
import { useState, useEffect } from "react";
import useLectureFormStore from "../store/LectureFormStore";
const days = ["월", "화", "수", "목", "금", "토", "일"];
const times = [
  "오전 9~10",
  "오전 10~11",
  "오전 11~12",
  "오후 12~1",
  "오후 1~2",
  "오후 2~3",
  "오후 3~4",
  "오후 4~5",
  "오후 5~6",
  "오후 6~7",
  "오후 7~8",
  "오후 8~9",
  "오후 9~10",
  "오후 10~11",
  "오후 11~12",
  "오후 12~1",
];

function TimeTable(props) {
  // const [selectTime, setSelectTime] = useState([]);
  const { avaliableTimeList } = useLectureFormStore();
  useEffect(() => {
    console.log(avaliableTimeList);
  }, [avaliableTimeList]); // selectTime이 변경될 때마다 호출됩니다.

  return (
    <TableContainer
      style={{ maxWidth: 1400, maxHeight: 800, textAlign: "center" }}
    >
      <Table>
        <TableHead>
          <TableRow>
            {[" ", ...days].map((day) => (
              <TableCell align="center">{day}</TableCell>
            ))}
          </TableRow>
        </TableHead>
        <TableBody>
          {props.ableTime == null ? (
            <OpenCellList />
          ) : (
            <RegisterCellList ableTime={props.ableTime} />
          )}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

function UseTimeCell({ day, time }) {
  const { avaliableTimeList, addAvaliableTime, removeAvaliableTime } =
    useLectureFormStore();
  const [clicked, setClicked] = useState(false);

  const checkHandle = () => {
    if (!clicked) {
      // 새로운 선택 시간을 추가합니다.
      addAvaliableTime(day, time);
      //setSelectTime(prevSelectTime => [...prevSelectTime, { day, time }]);
    } else {
      removeAvaliableTime(day, time);
      // 선택된 시간을 제거합니다.
      //setSelectTime(prevSelectTime => prevSelectTime.filter(select => !(select.day === day && select.time === time)));
    }
    setClicked(!clicked);
  };

  return (
    <Button
      variant="contained"
      onClick={checkHandle}
      style={{
        backgroundColor: clicked ? "#c7ffd8" : "white",
        borderRadius: 0,
        padding: 14,
        margin: 0,
        width: "100%",
        height: "100%",
      }}
    >
      &nbsp;
    </Button>
  );
}
function OpenCellList() {
  return times.map((time, index) => (
    <TableRow key={index}>
      <TableCell>{time}</TableCell>
      {days.map((day, index) => (
        <TableCell key={index} sx={{ border: "1px solid black", padding: 0 }}>
          <UseTimeCell day={day} time={time}></UseTimeCell>
        </TableCell>
      ))}
    </TableRow>
  ));
}
function RegisterCellList(props) {
  console.log(props.ableTime);
  return times.map((time, index) => (
    <TableRow key={index}>
      <TableCell>{time}</TableCell>
      {days.map((day, index) => (
        <TableCell
          key={index}
          sx={{ height: "3px", border: "1px solid black", padding: 0 }}
        >
          <UseRegisterTimeCell
            day={day}
            time={time}
            able={isAble(day, time)}
          ></UseRegisterTimeCell>
        </TableCell>
      ))}
    </TableRow>
  ));
  function isAble(day, time) {
    return props.ableTime.some(
      (element) => element.day === day && element.time === time
    );
  }
}

function UseRegisterTimeCell({ day, time, able }) {
  const { avaliableTimeList, addAvaliableTime, removeAvaliableTime } =
    useLectureFormStore();
  const [clicked, setClicked] = useState(false);

  const checkHandle = () => {
    if (!clicked) {
      // 새로운 선택 시간을 추가합니다.
      addAvaliableTime(day, time);
      //setSelectTime(prevSelectTime => [...prevSelectTime, { day, time }]);
    } else {
      removeAvaliableTime(day, time);
      // 선택된 시간을 제거합니다.
      //setSelectTime(prevSelectTime => prevSelectTime.filter(select => !(select.day === day && select.time === time)));
    }
    setClicked(!clicked);
  };

  return (
    <Button
      variant="contained"
      onClick={checkHandle}
      disabled={!able}
      style={{
        backgroundColor: !able ? "lightgray" : clicked ? "#c7ffd8" : "white",
        borderRadius: 0,
        padding: 14,
        margin: 0,
        width: "100%",
        height: "100%",
      }}
    >
      &nbsp;
    </Button>
  );
}

export default TimeTable;
