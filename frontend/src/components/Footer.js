import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import { Container, Typography, Grid } from "@mui/material";

export default function Footer() {
  const currentYear = new Date().getFullYear();
  const yearTxt = currentYear === 2022 ? "2022" : "2022 - " + currentYear;
  return (
    <>
      <Container>
        <Grid container>
          <Grid item xs={12}>
            <Typography variant="h6">
            © { yearTxt } Service - Developed by Genspark Team 3
            </Typography>
          </Grid>
        </Grid>
      </Container>
      <Box sx={{ flexGrow: 1, top: "auto", bottoom: 0 }}>
        <AppBar position="static" sx={{ color: "#fff" }} color="primary">
          <Toolbar></Toolbar>
        </AppBar>
      </Box>
    </>
  );
}