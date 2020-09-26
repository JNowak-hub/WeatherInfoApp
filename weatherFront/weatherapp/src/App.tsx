import BackGroundWrapper from "./styles/BackGroundWrapper";
import React from "react";
import Navbar from "./components/Navbar";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import { Router } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <BackGroundWrapper>
        <Navbar />
        <Switch>
          <Route exact path="/about">
            about
          </Route>
          <Route exact path="/users">
            USERS
          </Route>
          <Route exact path="/">
            HOME
          </Route>
          <Route exact path="/login">
            LOGIN
          </Route>
          <Route exact path="/products">
            PRODUKTS
          </Route>
        </Switch>
      </BackGroundWrapper>
    </BrowserRouter>
  );
}

export default App;
