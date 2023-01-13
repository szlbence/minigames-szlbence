import React from "react";

import {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";


function AddToCart(id, productBoxId) {
    (async () => {
        await fetch(`/cart/${id}/add/${productBoxId}`, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
        });
    })();

}
const PremadeBoxes = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [carts, setCarts] = useState([])
    const PRODUCTBOX_URL = "/productbox";
    const CART_URL = "/cart";

    useEffect(() => {
        fetch(CART_URL)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setCarts(result);
                }
            )
    }, [carts])

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
                                    <button type="submit" onClick={() => {  AddToCart(carts[0].id, item.id)}}>Add To Cart</button>
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
