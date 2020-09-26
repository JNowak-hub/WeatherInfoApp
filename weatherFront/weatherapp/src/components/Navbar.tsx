import { FC } from "react";
import React, { useEffect } from "react";
import NavbarWrapper from "../styles/NavbarWrapper";
import NavbarItem from "../styles/NavbarItem";
import { Link } from "react-router-dom";
import NavbarButtonWrapper from "../styles/NavbarButtonWrapper";
import TimeWrapper from "../styles/TimeWrapper";
const Navbar = () => {
  const [time, setTime] = React.useState("");

  const updateTime = () => {
    setTime(msToTime(new Date()));
  };

  useEffect(() => {
    setInterval(updateTime, 1000);
  }, [updateTime]);

  function msToTime(duration: Date) {
    let seconds = duration.getSeconds(),
      minutes = duration.getMinutes(),
      hours = duration.getHours();

    const hoursString = hours < 10 ? "0" + hours.toString() : hours.toString();
    const minutesString =
      minutes < 10 ? "0" + minutes.toString() : minutes.toString();
    const secondsString =
      seconds < 10 ? "0" + seconds.toString() : seconds.toString();
    return hoursString + ":" + minutesString + ":" + secondsString;
  }

  return (
    <NavbarWrapper>
      <NavbarItem>
        <TimeWrapper>Time: {time}</TimeWrapper>
      </NavbarItem>
      <NavbarItem>
        <Link to="/">
          <NavbarButtonWrapper>Home</NavbarButtonWrapper>
        </Link>
      </NavbarItem>
      <NavbarItem>
        <Link to="/login">
          <NavbarButtonWrapper>Login</NavbarButtonWrapper>
        </Link>
      </NavbarItem>
      <NavbarItem>
        <Link to="/signin">
          <NavbarButtonWrapper>Sign In</NavbarButtonWrapper>
        </Link>
      </NavbarItem>
    </NavbarWrapper>
  );
};

export default Navbar;
