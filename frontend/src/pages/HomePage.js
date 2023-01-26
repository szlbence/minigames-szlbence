import React from 'react';
import "../App.css";
import image from "../pexels-andrea-piacquadio-1050256.jpg"
import Card from 'react-bootstrap/Card';
import Nav from "react-bootstrap/Nav";

const Home = () => {
    return (
        <div className="main">
            <div className="image">
                <img className="homeImg" src={image} style={{objectFit: "cover", width: 2000}}/>
            </div>
            <div className="page">
                <h1 align="center" style={{fontSize: 50}}>Welcome to GiftRocket</h1>
                <br/>
                <p style={{fontSize: 35}}>Our company is established to make sure that every person can
                    choose their desired gifts which can be given to their person of choice</p>
                <div className="grid">
                    <Card style={{ width: '36rem' }}>
                        <Card.Header></Card.Header>
                        <Card.Body>
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
