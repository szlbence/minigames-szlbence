import React, {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTrash} from "@fortawesome/free-solid-svg-icons";

const CartPage = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const URL = "http://localhost:8080/cart";


    async function AddToCart(id, name) {
        const productBoxId = (await getProductBoxId(name));
        const newId = productBoxId.replace(`"`, '');
        const finalId = newId.slice(0, newId.length - 1);
        console.log(productBoxId);
        console.log(newId);
        console.log(finalId);
        await (async () => {
            await fetch(`http://localhost:8080/cart/${id}/add/${finalId}`, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
            });
        })();
    }

    async function RemoveFromCart(id, name) {
        const productBoxId = (await getProductBoxId(name));
        const newId = productBoxId.replace(`"`, '');
        const finalId = newId.slice(0, newId.length - 1);
        console.log(productBoxId);
        console.log(newId);
        console.log(finalId);
        await (async () => {
            await fetch(`http://localhost:8080/cart/${id}/remove/${finalId}`, {
                method: 'DELETE',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
            });
        })();
    }

      async function getProductBoxId(name) {
          const response = await fetch(`http://localhost:8080/productbox/name/${name}`);
          return response.text();
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
                                    <button type="submit" onClick={() => AddToCart(items[0].id, key)}>+</button>
                                <button type="submit" onClick={() => RemoveFromCart(items[0].id, key)}>-</button>
                                <button type="button"><FontAwesomeIcon icon={faTrash} /></button>
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
