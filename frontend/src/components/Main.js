import React, { useState } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import axios from "axios";

//file imports
import Login from "./Login";
import Signup from "./Signup";
import Home from "./Home";
import Post from "./Post";
import Navbar from "./Navbar";
import Contact from "./Contact";
import FaqComponent from "./FaqComponent";

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
  const [primaryName, setPrimaryName] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");

  const [passwordLog, setPasswordLog] = useState("");
  const [emailLog, setEmailLog] = useState("");

  const navigate = useNavigate();

  const postData = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/userAccounts", {
        primaryName,
        phoneNumber,
        password,
        email,
      })
      .then((res) => console.warn("posting data", res));
  };

  const postLogin = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/login", {
        email: emailLog,
        password: passwordLog,
      })
      .then((res) => console.warn("posting Login data", res));
      navigate("/home")
  };

  return (
    <ThemeProvider theme={theme}>
      <Navbar />
      <Routes>
        <Route
          path="/"
          element={
            <Login
              passwordLog={passwordLog}
              emailLog={emailLog}
              setEmailLog={setEmailLog}
              setPasswordLog={setPasswordLog}
              postLogin={postLogin}
            />
          }
        />
        <Route
          path="signup"
          element={
            <Signup
              primaryName={primaryName}
              phoneNumber={phoneNumber}
              password={password}
              email={email}
              setEmail={setEmail}
              setPassword={setPassword}
              setPrimaryName={setPrimaryName}
              setPhoneNumber={setPhoneNumber}
              postData={postData}
            />
          }
        />

        <Route path="home" element={<Home />} />
        <Route path="post" element={<Post />} />
        <Route path="faq" element={<FaqComponent />} />
        <Route path="contact" element={<Contact />} />
      </Routes>
    </ThemeProvider>
  );
};

export default Main;
