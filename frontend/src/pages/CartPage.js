import React, {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTrash} from "@fortawesome/free-solid-svg-icons";
import DataService from "../components/DataService"

const CartPage = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const CART_URL = "/cart";
    const PRODUCTBOX_URL = "/productbox/name";



    async function increaseProductBoxQuantity(id, name) {
        const productBoxId = await DataService.getProductBoxId(PRODUCTBOX_URL, name);
        await DataService.postData(`${CART_URL}/${id}/add/${productBoxId}`)
        await getCarts();
    }

    async function decreaseProductBoxQuantity(id, name) {
        const productBoxId = await DataService.getProductBoxId(PRODUCTBOX_URL, name);
        await DataService.sendDelete(`${CART_URL}/${id}/remove/${productBoxId}`);
        await getCarts();
    }

    async function getCarts() {
        const carts = await DataService.getData(CART_URL);
        setIsLoaded(true);
        setItems(carts.data);
    }


    useEffect(() => {
        getCarts();
    }, [])

    if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            <div className="container">
                <div className="grid">
                    {Object.entries(items[0].productBoxes).map(([key, value]) =>
                        <Card key={key} style={{width: '36rem'}}>
                            <Card.Header></Card.Header>
                            <Card.Body>
                                <Card.Title>{key}</Card.Title>
                                <div className="grid">
                                    <Card.Text>
                                        Total quantity of products : {value}
                                    </Card.Text>
                                    <button type="submit" onClick={() => increaseProductBoxQuantity(items[0].id, key)}>+</button>
                                    <button type="submit" onClick={() => decreaseProductBoxQuantity(items[0].id, key)}>-</button>
                                    <button type="button"><FontAwesomeIcon icon={faTrash}/></button>
                                </div>
                            </Card.Body>
                        </Card>
                    )}
                    <Card style={{width: '36rem'}} className="checkout">
                        {/*<Card.Header></Card.Header>*/}
                        <Card.Body>
                            <Card.Title></Card.Title>
                            <Card.Text>
                                <p>Total Price: {items[0].totalPrice}</p>
                            </Card.Text>
                            <button type="button">Checkout</button>
                        </Card.Body>
                    </Card>
                </div>
            </div>
        );
    }
};

export default CartPage;
