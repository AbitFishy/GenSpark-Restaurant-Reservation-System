/* eslint-disable */
import React, {useState, useEffect} from "react";
// import Post from "./Post";
import axios from "axios";

import { Container, Grid, Typography } from "@mui/material";

import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import Button from "@mui/material/Button";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import { styled } from "@mui/material/styles";
// import { useHistory } from "react-router-dom";
import SendIcon from "@mui/icons-material/Send";
import TextField from "@mui/material/TextField";
// import swal from "sweetalert";

import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";


import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import FormControl from "@mui/material/FormControl";

import "../App.css";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 20,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: "#dddddd",
  },
  // hide last border
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "#097969",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};



const Home = () => {
  // const history = useHistory();

  const [userAccounts, setUserAccounts] = useState([]);

  const [searchTerm, setSearchTerm] = useState("");

  // const [firstName, setFirstName] = useState("");
  // const [lastName, setLastName] = useState("");
  // const [email, setEmail] = useState("");
  // const [userId, setUserId] = useState(null);

  const [open, setOpen] = React.useState(false);

  const handleClose = () => {
    setOpen(false);
  };

  const baseURL = "http://localhost:8080/api";

  useEffect(() => {
    axios.get(baseURL + "/userAccounts").then((response) => {
      setUserAccounts(response.data);
    });
  }, []);

  const handleOpen = () => {
    // console.log("id: " + employees[index].id);
    // console.log("firstName: " + employees[index].firstName);
    // console.log("lastName: " + employees[index].lastName);
    // console.log("email: " + employees[index].email);
    // let item = employees[index];
    // setEmail(item.email);
    // setFirstName(item.firstName);
    // setLastName(item.lastName);
    // setUserId(item.id);
    setOpen(true);
  };
  /*
  function deletePost(id) {
    axios.delete(`${baseURL}/${id}`).then((response) => {
      //   alert(`${id} deleted`);
      setEmployees(null);
      // setLoading(false);
    });
    swal({
      title: "Deleting Employee...",
      text: `employee with id: ${id} deleted`,
      icon: "error",
      dangerMode: true,
    });
    setTimeout(() => {
      window.location.reload(true);
    }, 1000);
    history.push("/");
  }

  function updatePost(id) {
    let item = { firstName, lastName, email, userId };
    // console.warn("item", item);
    axios
      .put(`${baseURL}/${userId}`, item)
      .then((res) => console.warn("posting data", res));
    setOpen(false);
    setTimeout(() => {
      window.location.reload(true);
    }, 1000);
    // history.push("/");
    // window.location.reload(true);
  }

  */
  return (
    <>
      <img className="img-home" src="images/home-banner2.jpg" alt="home" />
      <Container sx={{ marginTop: 8, marginBottom: 10 }}>
        <Grid container sx={{ objectFit: "cover" }}>
          <Grid item xs={12}>
            <Typography variant="h3" sx={{}}>
              Reservations
            </Typography>
          </Grid>
        </Grid>

        <Grid
          container
          maxWidth="lg"
          sx={{
            display: "flex",
            marginTop: 10,
            alignItems: "center",
            justifyContent: "center",
          }}
        >
          <Grid
            item
            sx={{
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <Box
              sx={{
                width: "100%",
                maxWidth: "100%",
              }}
            >
              <TextField
                fullWidth
                label="Search Name.."
                id="fullWidth"
                onChange={(e)=> setSearchTerm(e.target.value)}
                value={searchTerm}
              />
            </Box>
          </Grid>
        </Grid>

        <Grid container sx={{ marginTop: 5 }}>
          <Grid item xs={12}>
            <TableContainer component={Paper} elevation={15}>
              <Table sx={{ minWidth: 700 }} aria-label="customized table">
                <TableHead>
                  <TableRow>
                    <StyledTableCell>ID</StyledTableCell>
                    <StyledTableCell align="right">Name</StyledTableCell>
                    <StyledTableCell align="right">Phone</StyledTableCell>
                    <StyledTableCell align="right">Date</StyledTableCell>
                    <StyledTableCell align="right">Guest</StyledTableCell>
                    <StyledTableCell align="right">Status</StyledTableCell>
                    <StyledTableCell align="right">
                      Edit Reservation
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      Delete Reservation
                    </StyledTableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {userAccounts.filter(userAccount => {
                    if(searchTerm === ""){
                      return userAccount
                    } else if(userAccount.primaryName.toLowerCase().includes(searchTerm.toLowerCase())){
                      return userAccount
                    } 
                  }) 
                  .map((userAccount, index) => ( 
                  <StyledTableRow key={index}>
                    <StyledTableCell
                      className="table-cell"
                      component="th"
                      scope="row"
                    >
                      {userAccount.userId}
                    </StyledTableCell>
                    <StyledTableCell className="table-cell" align="right">
                      {userAccount.primaryName}
                    </StyledTableCell>
                    <StyledTableCell className="table-cell" align="right">
                      {userAccount.phoneNumber}
                    </StyledTableCell>
                    <StyledTableCell className="table-cell" align="right">
                      {userAccount.res.dateTime}
                    </StyledTableCell>
                    <StyledTableCell className="table-cell" align="right">
                      {userAccount.res.numberOfGuests}
                    </StyledTableCell>
                    <StyledTableCell className="table-cell" align="right">
                      {userAccount.res.type}
                    </StyledTableCell>
                    <StyledTableCell className="table-cell" align="right">
                      <Button
                        type="submit"
                        variant="none"
                        endIcon={<EditIcon color="secondary" />}
                        onClick={handleOpen}
                      >
                        Edit
                      </Button>
                    </StyledTableCell>

                    <Modal
                      open={open}
                      onClose={handleClose}
                      aria-labelledby="modal-modal-title"
                      aria-describedby="modal-modal-description"
                    >
                      <Box sx={style}>
                        <Paper className="form-paper" elevation={10}>
                          <Grid container spacing={1} sx={{ padding: 1 }}>
                            <Grid item xs={6} sm={6}>
                              <input
                                className="form-control"
                                placeholder="Name"
                                type="text"
                                // value={primaryName}
                                // onChange={(e) => setFirstName(e.target.value)}
                              />
                            </Grid>
                            <Grid item xs={6} sm={6}>
                              <input
                                className="form-control"
                                placeholder="phone"
                                type="tel"
                                // value={phoneNumber}
                                // onChange={(e) => setLastName(e.target.value)}
                              />
                            </Grid>
                            <Grid item xs={6} sm={6}>
                              <input
                                className="form-control"
                                placeholder="Date"
                                type="datetime-local"
                                // value={dateTime}
                                // onChange={(e) => setLastName(e.target.value)}
                              />
                            </Grid>
                            <Grid item xs={6}>
                              <input
                                className="form-control"
                                placeholder="Guest"
                                type="number"
                                min="0"
                                // value={numberOfGuests}
                                // onChange={(e) => setLastName(e.target.value)}
                              />
                            </Grid>
                            <Grid item xs={12}>
                              <FormControl
                                sx={{ m: 0, minWidth: 120 }}
                                size="small"
                              >
                                <InputLabel id="demo-simple-select-label">
                                  Status
                                </InputLabel>
                                <Select
                                  labelId="demo-simple-select-label"
                                  id="demo-simple-select"
                                  // value={type}
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
                          <div className="post-button" sx={{ marginTop: 2 }}>
                            <Button
                              type="submit"
                              variant="contained"
                              value="Update"
                              fullWidth
                              endIcon={<SendIcon color="secondary" />}
                              // onClick={() => updatePost(`${employee.id}`)}
                            >
                              done
                            </Button>
                          </div>
                        </Paper>
                      </Box>
                    </Modal>

                    <StyledTableCell className="table-cell" align="right">
                      <Button
                        id="delete"
                        type="submit"
                        variant="none"
                        endIcon={<DeleteIcon color="error" />}
                        // onClick={() => deletePost(`${employee.id}`)}
                      >
                        Delete
                      </Button>
                    </StyledTableCell>
                  </StyledTableRow>
                   ))} 
                </TableBody>
              </Table>
            </TableContainer>
          </Grid>
        </Grid>
      </Container>
    </>
  );
};

export default Home;
