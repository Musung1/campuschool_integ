import * as React from "react";
import PropTypes from "prop-types";
import Box from "@mui/material/Box";
import Collapse from "@mui/material/Collapse";
import IconButton from "@mui/material/IconButton";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Typography from "@mui/material/Typography";
import Paper from "@mui/material/Paper";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import { Pagination, Stack, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { getNotifications } from "../api/NotificationAPI";
import { useEffect } from "react";

function createData(index, noti) {
  return {
    index: index,
    title: noti.title,
    content: noti.content,
    createdBy: noti.createdBy,
    createdAt: new Date(noti.createdAt).toLocaleString(),
  };
}

function Row(props) {
  const { row } = props;
  const [open, setOpen] = React.useState(false);

  return (
    <React.Fragment>
      <TableRow sx={{ "& > *": { borderBottom: "unset" } }}>
        <TableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={() => setOpen(!open)}
          >
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell component="th" scope="row">
          {row.index}
        </TableCell>
        <TableCell align="center">{row.title}</TableCell>
        <TableCell align="center">{row.createdBy}</TableCell>
        <TableCell align="center">{row.createdAt}</TableCell>
      </TableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1 }}>
              <Typography variant="h6" gutterBottom component="div">
                내용
              </Typography>
              <TableCell component="th" scope="row">
                {row.content}
              </TableCell>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
}
export default function NotificationTable(props) {
  const navigate = useNavigate();
  const [notifications, setNotifications] = React.useState(null);
  const goNotification = () => {
    navigate("/user/open/" + String(props.id) + "/notification");
  };
  useEffect(() => {
    console.log(props.id);
    async function getNoti() {
      const noti = await getNotifications(props.id);
      setNotifications(noti);
      console.log(noti);
    }
    getNoti();
  }, []);
  if (notifications == null) {
    return <div>loading</div>;
  }
  var index = notifications.pageable.offset + 1;
  const rows = notifications.content.map((noti) => createData(index++, noti));
  return (
    <Stack alignItems={"center"}>
      <TableContainer component={Paper}>
        <Table aria-label="collapsible table">
          <TableHead>
            <TableRow>
              <TableCell />
              <TableCell align="center">No</TableCell>
              <TableCell align="center">제목</TableCell>
              <TableCell align="center">글쓴이</TableCell>
              <TableCell align="center">작성시간</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <Row key={row.name} row={row} />
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <Stack
        direction="row"
        spacing={2}
        justifyContent="space-between"
        alignContent={"right"}
        width="100%"
      >
        {props.role == "teacher" ? (
          <Button variant="contained" onClick={() => goNotification()}>
            공지 작성
          </Button>
        ) : null}
      </Stack>
      <Pagination
        count={1}
        variant="outlined"
        shape="rounded"
        // onChange={handleChange}
      />
    </Stack>
  );
}
