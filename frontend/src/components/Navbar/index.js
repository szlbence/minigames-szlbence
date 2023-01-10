import React from "react";
import { Nav, NavLink, NavMenu }
    from "./NavBarElements";
import image from "./corporation-removebg-preview.png"

const Navbar = () => {
    return (
        <>
            <Nav>
                <NavMenu>
                    <img src={image} style={{width:130, height:130}}/>
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