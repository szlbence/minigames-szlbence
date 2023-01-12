import React, {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTrash} from "@fortawesome/free-solid-svg-icons";
import uuid from 'react-uuid';

const CartPage = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [boxId, setBoxId] = useState(uuid);
    const URL = "http://localhost:8080/cart";

    async function AddToCart(id, name) {
        const productBoxId = (await getProductBoxId(name));
        await (async () => {
            await fetch(`http://localhost:8080/cart/${id}/add/${productBoxId}`, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
            });
        })();

     async function getProductBoxId(name) {
            return await  fetch(`http://localhost:8080/productbox/${name}`).then(res => res.json()).then((result) => {
                setBoxId(result);
            }, boxId);
        }
    }

        useEffect(() => {
        fetch(URL)
            .then(res => res.json())
            .then(
                (result) => {
                    // console.log(result);
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
                    {/*{items[0].productBoxes.map(item =>*/}
                    {Object.entries(items[0].productBoxes).map(([key, value]) =>
                        <Card style={{width: '36rem'}}>
                            <Card.Header><Card.Img variant="top" src="holder.js/100px180"/></Card.Header>
                            <Card.Body>
                                <Card.Title>{key}</Card.Title>
                                <div className="grid">
                                    <Card.Text>
                                        Total quantity of products  : {value}
                                    </Card.Text>
                                    <button type="submit" onClick={AddToCart(items[0].id, key)}>+</button>
                                <button type="button">-</button>
                                <button type="button"><FontAwesomeIcon icon={faTrash} /></button>
                                </div>
                            </Card.Body>
                        </Card>
                    )}
                    </div>
                    <div align="center"><br/><br/><br/><br/><br/><br/><br/><br/>
                    Total value of your cart : {items[0].totalPrice}
                    </div>
                </div>
        );
    }
};

export default CartPage;
