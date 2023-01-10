import React from "react";
import { Nav, NavLink, NavMenu }
    from "./NavBarElements";

const Navbar = () => {
    return (
        <>
            <Nav>
                <NavMenu>
                    <NavLink to="/" activeStyle>
                        Home
                    </NavLink>
                    <NavLink to="/premade-boxes" activeStyle>
                        Premade Boxes
                    </NavLink>
                    <NavLink to="/custom-boxes" activeStyle>
                        Custom Boxes
                    </NavLink>
                    <NavLink to="/contact" activeStyle>
                        Contact Us
                    </NavLink>
                </NavMenu>
            </Nav>
        </>
    );
};

export default Navbar;