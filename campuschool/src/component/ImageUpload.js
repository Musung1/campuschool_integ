import React, { useState } from "react";
import { Container, Card, CardMedia } from "@mui/material";
import useLectureFormStore from "../store/LectureFormStore";
import imageCompression from "browser-image-compression";

const ImageUpload = () => {
  const [previewUrl, setPreviewUrl] = useState(null);
  const { img, setImage } = useLectureFormStore();
  const handleImageChange = async (e) => {
    const file = e.target.files[0];
    try {
      const options = {
        maxSizeMB: 1, // 최대 파일 크기 (1MB로 설정)
        maxWidthOrHeight: 1920, // 최대 너비 또는 높이 (1920px로 설정)
        useWebWorker: true, // 웹 워커 사용 여부
      };
      if (file) {
        const compressedFile = await imageCompression(file, options);
        setImage(compressedFile);
        const reader = new FileReader();
        reader.onload = () => {
          setPreviewUrl(reader.result);
        };
        reader.readAsDataURL(compressedFile);
      }
    } catch (e) {
      console.error(e);
    }
  };

  const handleUpload = () => {
    // 이미지 업로드 로직을 이곳에 추가
    console.log("Uploading image:", img);
  };

  return (
    <Container style={{ margin: 0, padding: 0 }}>
      <input type="file" accept="image/*" onChange={handleImageChange} />
      {previewUrl && (
        <Card sx={{ maxWidth: 200, marginTop: 2 }}>
          <CardMedia component="img" image={previewUrl} alt="Preview" />
        </Card>
      )}
    </Container>
  );
};

export default ImageUpload;
