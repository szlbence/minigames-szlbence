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
    const CART_TOTAL_PRICE_URL = "/cart/value";
    const USER_URL = "/user";

    const [totalPrice, setTotalPrice] = useState([]);
    const [totalCoin, setTotalCoin] = useState([]);

    async function AddToCart(cartId, productId, productPrice) {
        if (totalPrice + productPrice <= totalCoin ) {
            try {
                await DataService.postData(`${CART_URL}/${cartId}/add/${productId}`);
                await getTotalPrice();
                await getCarts();
            }
        catch (error) {
            console.log("Cannot add to cart: " + error);
            }
        }
        else{alert("Insufficient coins! Keep on clickin'! ")};
    }

    async function getCarts() {
        try {
            const carts = await DataService.getData(CART_URL);
            setCarts(carts.data);
        } catch (error) {
            console.log("Error loading carts: " + error);
        }
    }

    async function getTotalPrice() {
        try{
            const totalPrice = await DataService.getData(`${CART_TOTAL_PRICE_URL}/1`);
            setTotalPrice(totalPrice.data);
        }
        catch(error){
            console.log("Error loading total price: " + error);
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

    function parseJwt(token) {
        if (!token) { return; }
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
    }


    function get_cookie(name){
        return document.cookie.split(';').some(c => {
            return c.trim().startsWith(name + '=');
        });
    }

    let cookie = document.cookie;
    let cookieValue = cookie.slice(6);
    let user = (parseJwt(cookieValue)).sub;
    console.log(user);

    async function getTotalCoin() {
        try{
            const totalCoin = await DataService.getData(`${USER_URL}/${user}/coin`);
            setTotalCoin(totalCoin.data);
        }
        catch(error){
            console.log("Error loading total price: " + error);
        }
    }

    useEffect(() => {
        getCarts();
        getProducts();
        getTotalPrice();
        getTotalCoin();
    }, [])

    if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <>
                <DropDown props={setItems}/>
                <div className="container">
                    <h1 style={{textAlign: "center"}}>Coins spent: {totalPrice}, Coins mined: {totalCoin}, Available coins: {totalCoin-totalPrice}</h1>
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
                                        AddToCart(carts[0].id, item.id, item.price)
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
