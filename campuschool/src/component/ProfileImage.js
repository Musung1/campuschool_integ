import React, { useState } from "react";
import { Container, Card, CardMedia } from "@mui/material";
import imageCompression from "browser-image-compression";
import useUserStore from "../store/UserStore";
import avatar from "../assets/categoryIcon/defaultAvatar.png";
import { imgUrl } from "../constant/BaseUrl";

const ProfileImage = () => {
  const { user, img, setImage, userDetail } = useUserStore();
  const image = user.img != null ? imgUrl(user.img) : avatar;
  const [previewUrl, setPreviewUrl] = useState(image);

  const handleImageChange = async (e) => {
    const file = e.target.files[0];
    if (!file) return; // 파일이 선택되지 않은 경우 처리
    try {
      const options = {
        maxSizeMB: 1, // 최대 파일 크기 (1MB로 설정)
        maxWidthOrHeight: 1920, // 최대 너비 또는 높이 (1920px로 설정)
        useWebWorker: true, // 웹 워커 사용 여부
      };
      const compressedFile = await imageCompression(file, options);
      await setImage(compressedFile);
      await userDetail();
      const reader = new FileReader();
      reader.onload = () => {
        setPreviewUrl(reader.result);
      };
      reader.readAsDataURL(compressedFile);
    } catch (e) {
      console.error(e);
    }
  };

  const handleUpload = () => {
    // 이미지 업로드 로직을 이곳에 추가
  };

  return (
    <Container style={{ margin: 0, padding: 0 }}>
      {previewUrl && (
        <Card sx={{ maxWidth: 200, marginTop: 2 }}>
          <CardMedia component="img" image={previewUrl} alt="Preview" />
        </Card>
      )}
      <input type="file" accept="image/*" onChange={handleImageChange} />
    </Container>
  );
};

export default ProfileImage;
