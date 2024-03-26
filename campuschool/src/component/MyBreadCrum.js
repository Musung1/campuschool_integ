import * as React from 'react';
import Typography from '@mui/material/Typography';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Link from '@mui/material/Link';
import { useNavigate } from 'react-router-dom';

function handleClick(event) {

}

export default function MyBreadCrum(props) {
  const navigate = useNavigate();

  const goClasses = () => {
    navigate('/classes')
  }
  function detail() {
    if(props.name != null) {
      return <Typography variant="h6" color="text.primary">{props.name}</Typography>
    }
  }
  return (
    <Breadcrumbs aria-label="breadcrumb">
      <Link underline="hover" color="inherit" onClick={goClasses}>
        <Typography variant="h6"> 
          전체강의
        </Typography>
      </Link>
      {detail()}
  </Breadcrumbs>
  );
}