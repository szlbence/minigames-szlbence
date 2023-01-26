import React, {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTrash} from "@fortawesome/free-solid-svg-icons";
import DataService from "../components/DataService"

const CartPage = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [totalPrice, setTotalPrice] = useState([]);
    const CART_URL = "/cart";
    const CART_TOTAL_PRICE_URL = "/cart/value";


    async function increaseProductBoxQuantity(cartId, productBoxId) {
        await DataService.postData(`${CART_URL}/${cartId}/add/${productBoxId}`)
        await getTotalPrice();
        await getCarts();
    }

    async function decreaseProductBoxQuantity(cartId, productBoxId) {
        await DataService.sendPut(`${CART_URL}/${cartId}/remove/${productBoxId}`);
        await getTotalPrice();
        await getCarts();
    }

    async function deleteProductBox(cartId, productBoxId) {
        await DataService.sendDelete(`${CART_URL}/${cartId}/remove/${productBoxId}`);
        await getTotalPrice();
        await getCarts();
    }

    async function getCarts() {
        const carts = await DataService.getData(CART_URL);
        setIsLoaded(true);
        setItems(carts.data);
    }

    async function getTotalPrice() {
        const carts = await DataService.getData(`${CART_TOTAL_PRICE_URL}/1`);
        setTotalPrice(carts.data);
    }


    useEffect(() => {
        getCarts();
        getTotalPrice();
    }, [])

    if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <div className="container">
                <div className="grid">
                    {items[0].products.map(cartProduct => //items[0] only until we have user login. until then only 1 cart is available.
                        <Card key={cartProduct.product.id} style={{width: '36rem'}}>
                            <Card.Header></Card.Header>
                            <Card.Body>
                                <Card.Title>{cartProduct.product.name}</Card.Title>
                                <div className="grid">
                                    <p>Total quantity of products : {cartProduct.quantity} </p>
                                    <button type="submit" onClick={async() => await increaseProductBoxQuantity(items[0].id, cartProduct.product.id)}>+</button>
                                    <button type="submit" onClick={async() =>await decreaseProductBoxQuantity(items[0].id, cartProduct.product.id)}>-</button>
                                    <button type="button" onClick={async() =>await deleteProductBox(items[0].id, cartProduct.product.id)}><FontAwesomeIcon icon={faTrash}/></button>
                                </div>
                            </Card.Body>
                        </Card>
                    )}
                    <Card style={{width: '36rem'}} className="checkout">
                        {/*<Card.Header></Card.Header>*/}
                        <Card.Body>
                            <Card.Title></Card.Title>
                            <p>Total Price: {totalPrice}</p>
                            <button type="button">Checkout</button>
                        </Card.Body>
                    </Card>
                </div>
            </div>
        );
    }
};

export default CartPage;
