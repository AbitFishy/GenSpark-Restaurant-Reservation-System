import React from "react";
import {
  Grid,
  Container,
  Typography,
  Paper,
  Slider,
  CardContent,
} from "@mui/material";

import ImageList from "@mui/material/ImageList";
import ImageListItem from "@mui/material/ImageListItem";
import Fade from "react-reveal/Fade";

const itemData = [
  {
    img: "https://media-cldnry.s-nbcnews.com/image/upload/t_focal-758x379,f_auto,q_auto:best/rockcms/2022-03/plant-based-food-mc-220323-02-273c7b.jpg",
    title: "plant base",
  },
  {
    img: "https://images.squarespace-cdn.com/content/v1/53b839afe4b07ea978436183/1608506169128-S6KYNEV61LEP5MS1UIH4/traditional-food-around-the-world-Travlinmad.jpg",
    title: "foods",
  },
  {
    img: "https://images.unsplash.com/photo-1551963831-b3b1ca40c98e",
    title: "Breakfast",
  },
  {
    img: "https://images.unsplash.com/photo-1551782450-a2132b4ba21d",
    title: "Burger",
  },

  {
    img: "https://images.unsplash.com/photo-1444418776041-9c7e33cc5a9c",
    title: "Coffee",
  },

  {
    img: "https://images.unsplash.com/photo-1558642452-9d2a7deb7f62",
    title: "Honey",
  },

  {
    img: "https://images.unsplash.com/photo-1518756131217-31eb79b20e8f",
    title: "Fern",
  },
  {
    img: "https://images.unsplash.com/photo-1597645587822-e99fa5d45d25",
    title: "Mushrooms",
  },
  {
    img: "https://images.unsplash.com/photo-1567306301408-9b74779a11af",
    title: "Tomato basil",
  },
];

const HomeCustom = () => {
  return (
    <Container>
      <Grid container spacing={1} sx={{ marginTop: 5 }}>
        <Fade left>
          <Grid item xs={12} sm={6}>
            <ImageList
              sx={{ width: 500, height: 450 }}
              cols={3}
              rowHeight={140}
            >
              {itemData.map((item) => (
                <ImageListItem key={item.img}>
                  <img
                    src={`${item.img}?w=164&h=164&fit=crop&auto=format`}
                    srcSet={`${item.img}?w=164&h=164&fit=crop&auto=format&dpr=2 2x`}
                    alt={item.title}
                    loading="lazy"
                  />
                </ImageListItem>
              ))}
            </ImageList>
          </Grid>
        </Fade>

        <Grid item xs={12} sm={6}>
          <Fade right>
            <Typography variant="h3" gutterBottom>
              Super Foods
            </Typography>
            <Typography variant="h5" gutterBottom>
              Live a healthier life and learn how to make delicious meals with
              super foods, including avocados, kale, brussel sprouts and more.
              Nutritionists reveal 10 plant-based foods they eat every week
            </Typography>
            <Typography variant="h3" gutterBottom>
              Tasty Meats
            </Typography>
            <Typography variant="h5">
              Our meat department has the finest steak available. T-bone, rib
              eye, filet mignon and ground beef.Our meat and poultry are the
              highest quality. We'll cook any cut of meat for you. Just Fax your
              order or call us.
            </Typography>
          </Fade>
        </Grid>
      </Grid>
      <Grid container spacing={1}>
        <Grid item xs={12} sm={6}>
          <Fade right>
            <Typography variant="h5" gutterBottom>
              Why is safety important in our restaurant?{" "}
            </Typography>
            <Typography variant="body1" gutterBottom>
              Practicing restaurant kitchen safety procedures protects your
              employees and keeps your establishment efficient and operational
            </Typography>
            <Typography variant="h5" gutterBottom>
              What is OSHA restaurant?
            </Typography>
            <Typography variant="body1" gutterBottom>
              provides specific rules and regulations that restaurant owners
              must adhere to “keep their workforce free of serious recognized
              hazards
            </Typography>
          </Fade>
        </Grid>
        <Grid item xs={12} sm={6}>
          <Paper elevation={15}>
            <CardContent>
              <Fade left>
                <Typography gutterBottom variant="body2" color="primary">
                  Customer Service
                </Typography>

                <Slider
                  // className={classes.slider}
                  color="secondary"
                  value={9}
                  max={10}
                  valueLabelDisplay="auto"
                  aria-labelledby="non-linear-slider"
                />
              </Fade>
              <Fade bottom>
                <Typography gutterBottom variant="body2" color="primary">
                  Quality
                </Typography>
                <Slider
                  // className={classes.slider}
                  color="secondary"
                  value={9}
                  max={10}
                  valueLabelDisplay="auto"
                  aria-labelledby="non-linear-slider"
                />
              </Fade>
              <Fade right>
                <Typography gutterBottom variant="body2" color="primary">
                  Saftey
                </Typography>
                <Slider
                  // className={classes.slider}
                  color="secondary"
                  value={10}
                  max={10}
                  valueLabelDisplay="auto"
                  aria-labelledby="non-linear-slider"
                />
              </Fade>
            </CardContent>
          </Paper>
        </Grid>
      </Grid>
    </Container>
  );
};

export default HomeCustom;
