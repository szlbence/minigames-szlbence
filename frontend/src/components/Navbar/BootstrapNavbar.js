import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import image from "./minigamelogo-removebg-preview.png";
import React from "react";
import {get_cookie, parseJwt} from "../../utils/authentications";

function BootstrapNavbar() {
    if (get_cookie("token")){


        let cookie = document.cookie;
        let cookieValue = cookie.slice(6);
        let user = (parseJwt(cookieValue)).sub;
        return (
            <Navbar className="navbarColor p-2 justify-content-between">
                    <Nav.Link href="/">
                        <div className="d-flex flex-column">
                            <img src={image} style={{width:80, height:80}}/>
                            <strong>Minigames</strong>
                        </div>
                    </Nav.Link>
                        <Nav className="me-auto">
                            <Nav.Link href="/miner">Miner's Treasure</Nav.Link>
                            <Nav.Link href="/research">Research Facility</Nav.Link>
                            <Nav.Link href="/upgrades">Upgrades</Nav.Link>
                        </Nav>
                    <div className="d-flex flex-column">
                        Welcome to Minigames, {user}
                        <Nav.Link href="/logout">Log Out</Nav.Link>
                    </div>
            </Navbar>
        );
    }
    else{
        return (
            <Navbar className="navbarColor p-2 justify-content-between">
                <Nav.Link href="/">
                    <div className="d-flex flex-column">
                        <img src={image} style={{width:80, height:80}}/>
                        <strong>Minigames</strong>
                    </div>
                </Nav.Link>
                <Nav className="me-auto">
                    <Nav.Link href="/miner">Miner's Treasure</Nav.Link>
                    <Nav.Link href="/research">Research Facility</Nav.Link>
                    <Nav.Link href="/upgrades">Upgrades</Nav.Link>
                </Nav>
                <Nav.Link href="/login">Login</Nav.Link>
            </Navbar>

        );

    }
}

export default BootstrapNavbar;
