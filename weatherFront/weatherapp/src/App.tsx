import BackGroundWrapper from "./styles/BackGroundWrapper";
import React from "react";
import Navbar from "./components/Navbar";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import { Router } from "react-router-dom";
import LoginPage from "./components/LoginPage";
import SignInPage from "./components/SinginPage";
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
          <Route exact path="/Home">
            HOME
          </Route>
          <Route exact path="/login">
            <LoginPage />
          </Route>
          <Route exact path="/signin">
            <SignInPage />
          </Route>
        </Switch>
      </BackGroundWrapper>
    </BrowserRouter>
  );
}

export default App;
