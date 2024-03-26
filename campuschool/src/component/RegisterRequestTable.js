import * as React from "react";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemText from "@mui/material/ListItemText";
import ListItemAvatar from "@mui/material/ListItemAvatar";
import Checkbox from "@mui/material/Checkbox";
import Avatar from "@mui/material/Avatar";
import { Stack, Button, Divider } from "@mui/material";
import { imgUrl } from "../constant/BaseUrl";
import { getRegisters, approveRegisters } from "../api/RegisterAPI";

export default function RegisterRequesetTable(props) {
  const [checked, setChecked] = React.useState([]);
  const [registers, setRegisters] = React.useState(null);
  React.useEffect(() => {
    console.log(props.id);
    async function getRegi() {
      const noti = await getRegisters(props.id);
      setRegisters(noti);
      console.log(noti);
    }
    getRegi();
  }, []);
  if (registers == null) {
    return <div>loading</div>;
  }
  const handleToggle = (value) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    setChecked(newChecked);
  };
  const approve = async () => {
    console.log(registers);
    console.log(checked);
    const ids = checked.map((regi) => regi.id);
    console.log(ids);
    ids.forEach(async (registerId) => {
      await approveRegisters(props.id, registerId);
    });
    const noti = await getRegisters(props.id);
    setRegisters(noti);
  };

  return (
    <Stack>
      <List dense sx={{ width: "100%", bgcolor: "background.paper" }}>
        {registers.map((value) => {
          const labelId = `checkbox-list-secondary-label-${value}`;
          return (
            <div>
              <ListItem
                key={value.id}
                secondaryAction={
                  <Checkbox
                    edge="end"
                    onChange={handleToggle(value)}
                    checked={checked.indexOf(value) !== -1}
                    inputProps={{ "aria-labelledby": labelId }}
                  />
                }
                disablePadding
              >
                <ListItemButton onClick={handleToggle(value)}>
                  <ListItemAvatar>
                    <Avatar src={imgUrl(value.img)} />
                  </ListItemAvatar>
                  <ListItemText id={labelId} primary={value.name} />
                  <ListItemText id={labelId} primary={value.description} />
                </ListItemButton>
              </ListItem>
              <Divider variant="inset" component="li" />
            </div>
          );
        })}
      </List>
      <Button variant="contained" onClick={() => approve(checked)}>
        대기 수락
      </Button>
    </Stack>
  );
}
