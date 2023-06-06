import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import image from "./minigamelogo-removebg-preview.png";
import React from "react";

function parseJwt(token) {
    if (!token) { return; }
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
}


function get_cookie(name){
    return document.cookie.split(';').some(c => {
        return c.trim().startsWith(name + '=');
    });
}


function BootstrapNavbar() {
    if (get_cookie("token")){


        let cookie = document.cookie;
        let cookieValue = cookie.slice(6);
        let user = (parseJwt(cookieValue)).sub;
        return (
            <Navbar className="navbarColor p-3 justify-content-between">
                    <Nav.Link href="/">
                        <div className="d-flex flex-column">
                        <img src={image} style={{width:100, height:100}}/>
                        <Navbar.Brand>Minigames</Navbar.Brand>
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
            <Navbar className="navbarColor">
                <Container fluid>
                    <img src={image} style={{width:100, height:100}}/>
                    <Navbar.Brand>Minigames</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="/">Home</Nav.Link>
                            <Nav.Link href="/miner">Miner's Treasure</Nav.Link>
                            <Nav.Link href="/research">Research Facility</Nav.Link>
                            <Nav.Link href="/upgrades">Upgrades</Nav.Link>
                            <Nav.Link href="/login">Login</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        );

    }
}

export default BootstrapNavbar;
