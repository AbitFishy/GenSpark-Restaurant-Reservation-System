import React from "react";
import { Routes, Route } from "react-router-dom";

//file imports
import Login from "./Login";
import Signup from "./Signup";
import Home from "./Home";
import Post from "./Post";
import Navbar from "./Navbar";
import Contact from "./Contact";

//material imports
import { createTheme, ThemeProvider } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: {
      main: "#000000", //black
      light: "", //green
    },
    secondary: {
      main: "#097969", //green
    },
    warning: {
      main: "#fff",
    },
  },
  typography: {
    fontFamily: "Garamond",
  },
});

const Main = () => {
  return (
    <ThemeProvider theme={theme}>
      <Navbar />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="signup" element={<Signup />} />
        <Route path="home" element={<Home />} />
        <Route path="post" element={<Post />} />
        <Route path="contact" element={<Contact />} />
      </Routes>
    </ThemeProvider>
  );
};

export default Main;
