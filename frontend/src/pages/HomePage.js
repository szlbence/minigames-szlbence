import React from 'react';
import "./HomePage.css";
import image from "../pexels-andrea-piacquadio-1050256.jpg"

//logo, login, image, description
const Home = () => {
    return (
        <div className="page">
            <img className="homeImg" src={image}/>
            <h1>Welcome to GiftRocket</h1>
            <h2>Our company is established to make sure that every person can
                choose their desired gifts which can be given to their person of choice. </h2>
            <div className="grid">
                <div className="card">
                    <img/>
                    <div className="container">
                        <h4>Pre-made Boxes</h4>
                        <p>If you don't have a specific idea about your gift</p>
                    </div>
                </div>
                <div className="card">
                    <img/>
                    <div className="container">
                        <h4>Custom Boxes</h4>
                        <p>You can create your own gift box here</p>
                    </div>
                </div>
            </div>
        </div>
            );
            };

            export default Home;