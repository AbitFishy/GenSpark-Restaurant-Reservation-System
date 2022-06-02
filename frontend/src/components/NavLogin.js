import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

// import axios from "axios";

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
import DrawerLogin from "./DrawerLogin";

const NavbLogin = () => {
  const [value, setValue] = useState("home");
  const navigate = useNavigate();

  const theme = useTheme();
  console.log(theme);
  const isMatch = useMediaQuery(theme.breakpoints.down("md"));
  console.log(isMatch);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
 ;



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
              <DrawerLogin />
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
                <Tab label="Home" value="home" onClick={() => navigate("/home")}/>
                <Tab label="Reservation" value="post" onClick={() => navigate("/post")}/>
                <Tab label="Faq" value="faq" onClick={() => navigate("/faq")}/>
                <Tab label="Contact" value="contact" onClick={() => navigate("/contact")}/>
              </Tabs>
              {/* //signup */}
              <Button sx={{ marginLeft: "10px", backgroundColor: "#097969" }} variant="contained"
              onClick={() => navigate("/")}
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

export default NavLogin;