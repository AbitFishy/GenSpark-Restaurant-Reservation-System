import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

import {
  AppBar,
  Button,
  Tab,
  Tabs,
  Toolbar,
  Typography,
  useMediaQuery,
  useTheme,
} from "@mui/material";

import AddBusinessRoundedIcon from "@mui/icons-material/AddBusinessRounded";
import DrawerComponent from "./DrawerComponent";

const Navbar = () => {
  const [value, setValue] = useState(0);
  const theme = useTheme();
  console.log(theme);
  const isMatch = useMediaQuery(theme.breakpoints.down("md"));
  console.log(isMatch);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const navigate = useNavigate();


  return (
    <>
      <AppBar sx={{ background: "#000000"}}>
        <Toolbar>
          <AddBusinessRoundedIcon sx={{ transform: "scale(2)", color: "#097969" }} />
          {isMatch ? (
            <>
              <Typography sx={{ fontSize: "2rem", paddingLeft: "10%" }}>
                Restaurant Reservation
              </Typography>
              <DrawerComponent />
            </>
          ) : (
            <>
              <Tabs
                sx={{ marginLeft: "auto" }}
                indicatorColor="secondary"
                textColor="inherit"
                value={value}
                onChange={handleChange}
              >
                <Tab label="Home" value="0" onClick={() => navigate("/home")}/>
                <Tab label="Post" value="1" onClick={() => navigate("/post")}/>
                <Tab label="Faq" value="2" onClick={() => navigate("/faq")}/>
                <Tab label="Contact" value="3" onClick={() => navigate("/contact")}/>
              </Tabs>
              <Button sx={{ marginLeft: "auto", backgroundColor: "#097969" }} variant="contained"
              onClick={() => navigate("/")}
              >
                Login
              </Button>
              <Button sx={{ marginLeft: "10px", backgroundColor: "#097969" }} variant="contained"
              onClick={() => navigate("/signup")}
              >
                SignUp
              </Button>
            </>
          )}
        </Toolbar>
      </AppBar>
    </>
  );
};

export default Navbar;