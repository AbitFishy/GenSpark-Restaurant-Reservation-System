import React, { } from "react";
// import { useHistory } from "react-router-dom";
// import axios from "axios";

import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";

import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import FormControl from "@mui/material/FormControl";

const Post = () => {
  // const [firstName, setFirstName] = useState("");
  // const [lastName, setLastName] = useState("");
  // const [email, setEmail] = useState("");

  // const history = useHistory();

  // const postData = (e) => {
  //   e.preventDefault();
  //   axios
  //     .post("http://employeemanagerservice-env.eba-azh5g6a4.us-west-1.elasticbeanstalk.com/api/employee", {
  //       firstName,
  //       lastName,
  //       email,
  //     })
  //     .then((res) => console.log("posting data", res));
  //     history.push('/');
  //     window.location.reload(true);
  // };

  return (
    <Container maxWidth="md" sx={{ marginTop: 15, marginBottom: 3 }}>
      <Grid container sx={{ marginBottom: 2 }}>
        <Grid item xs={12}>
          <Typography variant="h2">Add Reservation</Typography>
        </Grid>
      </Grid>
      <Grid container maxWidth="md">
        <Grid item xs={12}>
          <Paper className="form-paper" elevation={10}>
            <Grid container spacing={1}>
              <Grid item xs={12} sm={6}>
                <TextField
                  label="Name"
                  variant="outlined"
                  fullWidth
                  required
                  // value={firstName}
                  // onChange={(e)=> setFirstName(e.target.value)}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  label="Phone"
                  variant="outlined"
                  fullWidth
                  required
                  // value={lastName}
                  // onChange={(e)=> setLastName(e.target.value)}
                />
              </Grid>

              <Grid item xs={12} sm={6}>
                <TextField
                  label="Time/ date"
                  variant="outlined"
                  fullWidth
                  required
                  // value={email}
                  // onChange={(e)=> setEmail(e.target.value)}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  type="number"
                  InputProps={{ inputProps: { min: 0, max: 10 } }}
                  label="Guest"
                  variant="outlined"
                  fullWidth
                  required
                  // value={email}
                  // onChange={(e)=> setEmail(e.target.value)}
                />
              </Grid>
              <Grid item xs={2}>
                <FormControl sx={{ m: 1, minWidth: 120 }} size="medium">
                  <InputLabel id="demo-simple-select-label">Status</InputLabel>
                  <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    // value={age}
                    label="Status"
                    // onChange={handleChange}
                  >
                    <MenuItem value={0}>Pending</MenuItem>
                    <MenuItem value={1}>Confirmed</MenuItem>
                    <MenuItem value={2}>Arrived</MenuItem>
                    <MenuItem value={3}>Cancelled</MenuItem>
                    <MenuItem value={4}>Completed</MenuItem>
                  </Select>
                </FormControl>
              </Grid>
            </Grid>
            <div className="post-button">
              <Button
                sx={{ marginTop: 1 }}
                type="submit"
                variant="contained"
                fullWidth
                endIcon={<SendIcon color="secondary" />}
                // onClick={postData}
              >
                Add
              </Button>
            </div>
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
};

export default Post;