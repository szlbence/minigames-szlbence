import React from "react";

import {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import DataService from "../components/DataService";
import DropDown from "../components/DropDown";
import Button from "react-bootstrap/Button";

const ProductsPage = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [carts, setCarts] = useState([])
    const PRODUCT_URL = "/product";
    const CART_URL = "/cart";

    async function AddToCart(cartId, productId) {
        try {
            await DataService.postData(`${CART_URL}/${cartId}/add/${productId}`);
        }
        catch (error) {
            console.log("Cannot add to cart: " + error);
        }
    }

    async function getCarts() {
        try {
            const carts = await DataService.getData(CART_URL);
            setCarts(carts.data);
        } catch (error) {
            console.log("Error loading carts: " + error);
        }
    }

    async function getProducts() {
        try {
            const products = await DataService.getData(PRODUCT_URL);
            setIsLoaded(true);
            setItems(products.data);
        } catch (error) {
            console.log("Error loading products: " + error);
        }
    }

    useEffect(() => {
        getCarts();
        getProducts();
    }, [])

    if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <>
                <DropDown props={setItems}/>
                <div className="container">
                    <div className="grid">
                        {items.map(item =>
                            <Card key={item.id} style={{width: '36rem'}}>
                                <Card.Header></Card.Header>
                                <Card.Body>
                                    <Card.Title>{item.name}</Card.Title>
                                    <img className="homeImg" src={`${item.name.replace(" ", "_")}.jpeg`}
                                         style={{objectFit: "cover", width: 2000}}/>
                                    <p className="price">Total price: {item.price}</p>
                                    <p className="description">Description: {item.description}</p>
                                    <Button type="submit" bsPrefix="product-button" onClick={() => {
                                        AddToCart(carts[0].id, item.id)
                                    }}>Add To Cart
                                    </Button>
                                </Card.Body>
                            </Card>
                        )}
                    </div>
                    <br></br>
                    <br></br>
                </div>
            </>
        );
    }
};

export default ProductsPage;
