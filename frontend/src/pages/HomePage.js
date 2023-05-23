import React from 'react';
import "../App.css";
import image from "../minigames-logo"
import Card from 'react-bootstrap/Card';
import Nav from "react-bootstrap/Nav";

const Home = () => {
    return (
        <div className="main">
            <div className="image">
                <img className="homeImg" src={image} style={{objectFit: "cover", width: 2000}}/>
            </div>
            <div className="page">
                <h1 align="center" style={{fontSize: 50}}></h1>
                <br/>
                <p style={{fontSize: 35, textAlign:"center"}}>Play your favorite Games, collect Coins and become THE KING OF MINIGAMES!</p>
                <div className="homeGrid">
                    <Card className="homeCard" style={{ width: '36rem' }}>
                        <Card.Header></Card.Header>
                        <Card.Body className="homeCardBody">
                            <Card.Title><Nav.Link href="/product">Products</Nav.Link></Card.Title>
                            <img className="homeImg" src="premade.png" style={{objectFit: "cover", width: 2000}}/>
                            <Card.Text>
                                Here you can find all of our amazing products. Hopefully you find something that suits your taste!
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </div>
            </div>
        </div>
    );
};

export default Home;
