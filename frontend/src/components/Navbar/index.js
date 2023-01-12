import React from "react";
import { Nav, NavLink, NavMenu }
    from "./NavBarElements";

const Navbar = () => {
    return (
        <>
            <Nav>
                <NavMenu>
                    <NavLink to="/" activestyle="true">
                        Home
                    </NavLink>
                    <NavLink to="/premade-boxes" activestyle="true">
                        Premade Boxes
                    </NavLink>
                    <NavLink to="/custom-boxes" activestyle="true">
                        Custom Boxes
                    </NavLink>
                    <NavLink to="/contact" activestyle="true">
                        Contact Us
                    </NavLink>
                </NavMenu>
            </Nav>
        </>
    );
};

export default Navbar;