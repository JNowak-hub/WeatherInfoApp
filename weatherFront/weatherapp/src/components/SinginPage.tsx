import React from "react";
import FormWrapper from "../styles/LoginFormWrapper";
import InputWrapper from "../styles/InputFieldWrapper";
import ButtonWrapper from "../styles/ButtonWrapper";

const SinginPage = () => {
  const provider = "local";
  const [userName, setUserName] = React.useState("");
  const [name, setName] = React.useState("");
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");

  const signIn = async () => {
    const response = await fetch("http://localhost:8080/api/login/signIn", {
      method: "POST",
      credentials: "same-origin",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({
        userName: userName,
        password: password,
        email: email,
        name: name,
        provider: provider,
      }),
    });
    const data = await response.json();
  };

  return (
    <FormWrapper>
      <label>Insert User Name</label>
      <InputWrapper
        type="text"
        value={userName}
        onChange={(event) => setUserName(event.target.value)}
      />
      <label>Name</label>
      <InputWrapper
        type="text"
        value={name}
        onChange={(event) => setName(event.target.value)}
      />
      <label>Email adress</label>
      <InputWrapper
        type="text"
        value={email}
        onChange={(event) => setEmail(event.target.value)}
      />
      <label>Password</label>
      <InputWrapper
        type="password"
        value={password}
        onChange={(event) => setPassword(event.target.value)}
      />
      <ButtonWrapper onClick={signIn}>Send Form</ButtonWrapper>
    </FormWrapper>
  );
};

export default SinginPage;
