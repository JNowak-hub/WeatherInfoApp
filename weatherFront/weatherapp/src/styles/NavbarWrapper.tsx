import styled from "styled-components";

const NavbarWrapper = styled.nav`
  @media only screen and (max-width: 600px) {
    height: 55px;
    background: rgba(155, 170, 207, 0.55);
    border: 1px solid #010000;
    box-sizing: border-box;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25), 0px 4px 4px rgba(0, 0, 0, 0.25);
    display: flex;
    > * {
      flex: 1;
    }
  }
`;

export default NavbarWrapper;
