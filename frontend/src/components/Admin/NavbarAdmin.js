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

import AdminPanelSettingsIcon from '@mui/icons-material/AdminPanelSettings';
import DrawerComponent from "./DrawerComponent";

const NavbarAdmin = ({handleLogout}) => {
  
    
    const [value, setValue] = useState("admin");
    const navigate = useNavigate();
  
    const theme = useTheme();
    console.log(theme);
    const isMatch = useMediaQuery(theme.breakpoints.down("md"));
    console.log(isMatch);
  
    const handleChange = (event, newValue) => {
      setValue(newValue);
    };
    // const handleLogout = () => {
    //   axios.post("http://localhost:8080/api/auth/sigout", {
       
    //   }) .then((res) => { 
    //     console.log(res)
    //   })
    //   // navigate("/")
    // };
  
  
  
    return (
      <>
        <AppBar sx={{ background: "#000000"}}>
          <Toolbar>
            <AdminPanelSettingsIcon sx={{ transform: "scale(2)", color: "#097969" }} />
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
                  <Tab label="Admin" value="admin" onClick={() => navigate("/admin")}/>
                  <Tab label="User" value="user" onClick={() => navigate("/user")}/>
                  <Tab label="Reservation" value="reservation" onClick={() => navigate("/reservation")}/>
                  {/* <Tab label="Faq" value="faq" onClick={() => navigate("/faq")}/>
                  <Tab label="Contact" value="contact" onClick={() => navigate("/contact")}/> */}
                </Tabs>
                <Button sx={{ marginLeft: "auto", backgroundColor: "#097969" }} variant="contained"
                onClick={handleLogout}
                >
                  Logout
                </Button>
                {/* //signup */}
                {/* <Button sx={{ marginLeft: "10px", backgroundColor: "#097969" }} variant="contained"
                onClick={() => navigate("/")}
                >
                  SignUp
                </Button> */}
              </>
            )}
          </Toolbar>
        </AppBar>
      </>
    );

}

export default NavbarAdmin