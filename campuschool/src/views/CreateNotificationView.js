import { Typography, Stack, Button, TextField } from "@mui/material";
import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { postNotifications } from "../api/NotificationAPI";
export function CreateNotificationView() {
  const lectureId = useParams().id;
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const navigate = useNavigate();

  const lectureNameChange = (event) => {
    setTitle(event.target.value);
  };
  const lectureContentChange = (event) => {
    setContent(event.target.value);
  };
  return (
    <div>
      <Stack
        spacing={1}
        alignItems={"left"}
        textAlign={"left"}
        justifyContent={"left"}
      >
        <Typography variant="h5">강의 명</Typography>
        <TextField
          id="class-name"
          label="강의명"
          variant="outlined"
          value={title}
          onChange={lectureNameChange}
        />
        <Typography variant="h5">강의 내용</Typography>
        <TextField
          id="class-detail"
          label="강의 내용"
          multiline
          rows={4}
          value={content}
          onChange={lectureContentChange}
        />
        <Button
          onClick={async () => {
            await postNotifications(lectureId, {
              title: title,
              content: content,
            });
            navigate(-1);
          }}
        >
          공지 작성
        </Button>
      </Stack>
    </div>
  );
}
