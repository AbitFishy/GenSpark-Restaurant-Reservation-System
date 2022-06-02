import React from "react";
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
import { Carousel } from 'react-responsive-carousel';


const data = [
  {
    title: "Best. Product. Ever! Restaurant is the real deal!",
    author: "- Marley P.",
  },
  {
    title:
      "We've used restaurant for the last five years. Restaurant has got everything I need. We have no regrets! Thanks guys, keep up the good work!",
    author: "- Eric T.",
  },
  {
    title: "Thanks to restaurant, we've just launched our 5th website!",
    author: "- Kurt .",
  },

];
const Testimony = () => {
  return (
    <Carousel
      showArrows={true}
      infiniteLoop={true}
      showThumbs={false}
      showStatus={false}
      autoPlay={true}
      interval={6100}
    >
      <div>
        <img src="/images/shirley.png" />
        <div className="myCarousel">
          <h3>{data[0].author}</h3>
          <h4>Food Addict</h4>
          <p>
          {data[0].title}
          </p>
        </div>
      </div>

      <div>
        <img src="/images/daniel.png" />
        <div className="myCarousel">
          <h3>{data[1].author}</h3>
          <h4>Adventure</h4>
          <p>
          {data[1].title}
          </p>
        </div>
      </div>

      <div>
        <img src="/images/theo.png" />
        <div className="myCarousel">
          <h3>{data[2].author}</h3>
          <h4>Food aholic</h4>
          <p>
          {data[2].title}
          </p>
        </div>
      </div>
    </Carousel>
  );
};

export default Testimony;
