import React from "react";
import FormWrapper from "../styles/LoginFormWrapper";
import InputWrapper from "../styles/InputFieldWrapper";
const SinginPage = () => {
  const [userName, setUserName] = React.useState("");

  return (
    <FormWrapper>
      <label>Insert User Name</label>
      <InputWrapper
        type="text"
        value={userName}
        onChange={(event) => setUserName(event.target.value)}
      ></InputWrapper>
    </FormWrapper>
  );
};

export default SinginPage;
