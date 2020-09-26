import React from "react";
import LoginFormWrapper from "../styles/LoginFormWrapper";
import InputFieldWrapper from "../styles/InputFieldWrapper";
import ButtonWrapper from "../styles/ButtonWrapper";

const LoginPage = () => {
  const [userName, setUserName] = React.useState("");
  const [password, setPassword] = React.useState("");

  const logIn = async () => {
    // POST request using fetch with async/await
    const requestOptions = {
      method: "POST",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({
        userName: userName,
        password: password,
      }),
    };
    const response = await fetch("http://localhost:8080/api/login", {
      method: "POST",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({
        userName: userName,
        password: password,
      }),
    });
    const data = await response.json();
  };

  return (
    <LoginFormWrapper>
      <label>UserName</label>
      <InputFieldWrapper
        type="name"
        value={userName}
        onChange={(event) => setUserName(event.target.value)}
      ></InputFieldWrapper>
      <label>Password</label>
      <InputFieldWrapper
        type="password"
        value={password}
        onChange={(event) => setPassword(event.target.value)}
      ></InputFieldWrapper>
      <ButtonWrapper onClick={logIn}>LogIn</ButtonWrapper>
    </LoginFormWrapper>
  );
};

export default LoginPage;
