import React from "react";

import {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import DataService from "../components/DataService";


const PremadeBoxes = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [carts, setCarts] = useState([])
    const PRODUCT_URL = "/product";
    // const PRODUCTBOX_NAME_URL = "/productbox/name";
    const CART_URL = "/cart";

    async function AddToCart(cartId, productId) {
        //No need for id fetching anymore!
        // const productBoxId = await DataService.getProductBoxId(PRODUCTBOX_NAME_URL, name);
        await DataService.postData(`${CART_URL}/${cartId}/add/${productId}`)
    }
    async function getCarts() {
        const carts = await DataService.getData(CART_URL);
        setCarts(carts.data);
    }

    async function getProducts() {
        const products = await DataService.getData(PRODUCT_URL);
        setIsLoaded(true);
        setItems(products.data);
    }

    useEffect(() => {
        getCarts();
        getProducts();
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
                                <p className="price">Total price: {item.price}</p>
                                <p className="description">Description: {item.description}</p>
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
