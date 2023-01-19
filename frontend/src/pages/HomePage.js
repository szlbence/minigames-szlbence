import React from 'react';
import "../App.css";
import image from "../pexels-andrea-piacquadio-1050256.jpg"
import Card from 'react-bootstrap/Card';

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
                            <Card.Title>Pre-made Boxes</Card.Title>
                            <Card.Text>
                                If you don't have a specific idea about your gift
                            </Card.Text>
                        </Card.Body>
                    </Card>
                    <Card style={{ width: '36rem' }}>
                        <Card.Header></Card.Header>
                        <Card.Body>
                            <Card.Title>Custom Boxes</Card.Title>
                            <Card.Text>
                                You can create your own gift box
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </div>
            </div>
        </div>
    );
};

export default Home;
