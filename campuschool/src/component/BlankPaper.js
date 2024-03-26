import { styled, Paper } from "@mui/material";
export const BlankPaper = styled(Paper)(({ theme }) => ({
  height: "100",
  width: "60%",
  padding: theme.spacing(2),
  ...theme.typography.h2,
  textAlign: "center",
  alignContent: "center",
  alignItems: "center",
  margin: 10,
}));
