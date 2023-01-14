import React from "react";
import {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import DataService from "../components/DataService"

const PremadeBoxes = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const PRODUCTS_URL = "/product";

    async function getProducts() {
        const products = await DataService.getData(PRODUCTS_URL)
        setIsLoaded(true);
        setItems(products.data);
    }

    useEffect(() => {
        getProducts();
    }, [])

    if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <div className="container">
                <div className="grid">
                    {items.map(item =>
                        <Card style={{width: '36rem'}} key={item.id}>
                            <Card.Header></Card.Header>
                            <Card.Body>
                                <Card.Title>{item.name}</Card.Title>
                                <p className="price">Total price: {item.price}</p>
                                <p className="description">Description: {item.description}</p>
                                <button type="button">Add to box</button>
                            </Card.Body>
                        </Card>
                    )}
                </div>
            </div>
        );
    }
};

export default PremadeBoxes;
