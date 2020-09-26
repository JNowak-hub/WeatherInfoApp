import React from "react";
import LoginFormWrapper from "../styles/LoginFormWrapper";
import InputFieldWrapper from "../styles/InputFieldWrapper";
import ButtonWrapper from "../styles/ButtonWrapper";

const LoginPage = () => {
  const logIn = () => {
    console.log("work");
  };

  return (
    <LoginFormWrapper>
      <label>UserName</label>
      <InputFieldWrapper type="name"></InputFieldWrapper>
      <label>Password</label>
      <InputFieldWrapper type="password"></InputFieldWrapper>
      <ButtonWrapper onClick={logIn}>LogIn</ButtonWrapper>
    </LoginFormWrapper>
  );
};

export default LoginPage;
