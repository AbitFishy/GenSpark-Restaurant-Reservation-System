import React, { useRef } from "react";
import emailjs from "@emailjs/browser";

import {
  FormControl,
  InputLabel,
  Input,
  Button,
  Container,
  TextField,
} from "@mui/material";
import swal from "sweetalert";


const Contact = () => {
  const form = useRef();

  const sendEmail = (e) => {
    e.preventDefault();

    emailjs
      .sendForm(
        "service_dbyat6j",
        "template_pynimg9",
        form.current,
        "gx9gGKOxJ-O03t6qW"
      )
      .then(
        (result) => {
          console.log(result.text);
        },
        (error) => {
          console.log(error.text);
        }
      );
      swal({
        title: "message sent successfully",
        text: `message sent`,
        icon: "success",
        dangerMode: false,
      });
      setTimeout(() => {
        window.location.reload(true);
      }, 1000);
  };

  return (
    <Container componet="div" sx={{ marginTop: 8 }}>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          margin: 20,
          padding: 20,
        }}
      >
        <form style={{ width: "100%" }} ref={form} onSubmit={sendEmail}>
          <h1>Contact Form</h1>

          <FormControl margin="normal" fullWidth>
            <InputLabel htmlFor="name">Name</InputLabel>
            <Input id="name" type="text" name="user_name" />
          </FormControl>

          <FormControl margin="normal" fullWidth>
            <InputLabel htmlFor="email">Email</InputLabel>
            <Input id="email" type="email" name="user_email" />
          </FormControl>

          <FormControl margin="normal" fullWidth>
            {/* <InputLabel htmlFor="message">Message</InputLabel> */}
            <label>Message</label>
            <textarea rows="4" cols="10" name="message" />
          </FormControl>

          <Button
            variant="contained"
            fullWidth
            color="primary"
            size="medium"
            sx={{
              MarginTop: 0,
              "&:hover": { backgroundColor: "secondary.main" },
            }}
            type="submit"
            value="Send"
          >
            Send
          </Button>
        </form>
      </div>
    </Container>
    // <Container sx={{ marginTop: 20 }}>
    //   <Grid container>
    //     <Grid item>
    //       <form ref={form} onSubmit={sendEmail}>
    //         <label>Name</label>
    //         <input type="text" name="user_name" />
    //         <label>Email</label>
    //         <input type="email" name="user_email" />
    //         <label>Message</label>
    //         <textarea name="message" />
    //         <input type="submit" value="Send" />
    //       </form>
    //     </Grid>
    //   </Grid>
    // </Container>
  );
};

export default Contact;
