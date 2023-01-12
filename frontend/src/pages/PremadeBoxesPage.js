import React from "react";
import {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";


const PremadeBoxes = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const PRODUCTBOX_URL = "http://localhost:8080/productbox";

    useEffect(() => {
        fetch(PRODUCTBOX_URL)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setItems(result);
                }
            )
    }, [items])
    if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <div className="container">
                <div className="grid">
                    {items.map(item =>
                        <Card style={{width: '36rem'}}>
                            <Card.Header><Card.Img variant="top" src="holder.js/100px180"/></Card.Header>
                            <Card.Body>
                                <Card.Title>{item.name}</Card.Title>
                                <Card.Text>
                                    <p className="price">Total price: {item.totalPrice}</p>
                                    <p className="description">Description: {item.description}</p>
                                    <p className="description">Products: {Object.keys(item.products).join(", ")}</p>
                                    <button type="button">button</button>
                                </Card.Text>
                            </Card.Body>
                        </Card>
                    )}
                </div>
            </div>
        );
    }
};

export default PremadeBoxes;
