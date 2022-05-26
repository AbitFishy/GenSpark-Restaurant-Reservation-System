import React from "react";

import { useNavigate } from "react-router-dom";

import {
  Grid,
  Paper,
  Avatar,
  Typography,
  TextField,
  Button,
  Container,
} from "@mui/material";
import AddCircleOutlineOutlinedIcon from "@mui/icons-material/AddCircleOutlineOutlined";


const Signup = ({
  email,
  setEmail,
  password,
  setPassword,
  userName,
  setUserName,
  userNumber,
  setUserNumber,
  postData,
}) => {
  console.log(email);
  const paperStyle = { padding: "30px 20px", width: 300, margin: "20px auto" };
  const headerStyle = { margin: 0 };

  const navigate = useNavigate();

  return (
    <Container component="main" maxWidth="xs" sx={{ marginTop: 20 }}>
      <Grid>
        <Paper elevation={20} style={paperStyle}>
          <Grid align="center">
            <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
              <AddCircleOutlineOutlinedIcon />
            </Avatar>
            <h2 style={headerStyle}>Sign Up</h2>
            <Typography variant="caption" gutterBottom>
              Please fill this form to create an account !
            </Typography>
          </Grid>
          <form onSubmit={()=> navigate("/")}>
            <TextField
              fullWidth
              label="Name"
              placeholder="Enter your name"
              required
              value={userName}
              onChange={(e)=> setUserName(e.target.value)}
            />
            <TextField
              fullWidth
              label="Email"
              type="email"
              placeholder="Enter your email"
              required
              value={email}
              onChange={(e)=> setEmail(e.target.value)}
            />
            <TextField
              fullWidth
              label="Phone Number"
              type="tel"
              placeholder="Enter your phone number"
              required
              value={userNumber}
              onChange={(e)=> setUserNumber(e.target.value)}
            />
            <TextField
              fullWidth
              label="Password"
              type="password"
              placeholder="Enter your password"
              required
              value={password}
              onChange={(e)=>setPassword(e.target.value)}
            />
            <Button
              sx={{ "&:hover": { backgroundColor: "secondary.main" } }}
              type="submit"
              variant="contained"
              color="primary"
              onClick={postData}
            >
              Sign up
            </Button>
          </form>
        </Paper>
      </Grid>
    </Container>
  );
};

export default Signup;
