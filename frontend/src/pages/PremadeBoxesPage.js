import React from "react";

import {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import DataService from "../components/DataService";


const PremadeBoxes = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [carts, setCarts] = useState([])
    const PRODUCTBOX_URL = "/productbox";
    // const PRODUCTBOX_NAME_URL = "/productbox/name";
    const CART_URL = "/cart";

    async function AddToCart(cartId, productBoxId) {
        //No need for id fetching anymore!
        // const productBoxId = await DataService.getProductBoxId(PRODUCTBOX_NAME_URL, name);
        await DataService.postData(`${CART_URL}/${cartId}/add/${productBoxId}`)
    }
    async function getCarts() {
        const carts = await DataService.getData(CART_URL);
        setCarts(carts.data);
    }

    async function getProductBoxes() {
        const productBoxes = await DataService.getData(PRODUCTBOX_URL);
        setIsLoaded(true);
        setItems(productBoxes.data);
    }

    useEffect(() => {
        getCarts();
        getProductBoxes();
    }, [])

    if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <div className="container">
                <div className="grid">
                    {items.map(item =>
                        <Card key={item.id} style={{width: '36rem'}}>
                            <Card.Header></Card.Header>
                            <Card.Body>
                                <Card.Title>{item.name}</Card.Title>
                                <p className="price">Total price: {item.totalPrice}</p>
                                <p className="description">Description: {item.description}</p>
                                <p className="products">Products: {item.products.map(product => product.name).join(", ")}</p>
                                <button type="submit" onClick={() => {AddToCart(carts[0].id, item.id)}}>Add To Cart</button>
                            </Card.Body>
                        </Card>
                    )}
                </div>
            </div>
        );
    }
};

export default PremadeBoxes;
