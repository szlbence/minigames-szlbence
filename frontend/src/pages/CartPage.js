import React from "react";
import {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";


const CartPage = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const URL = "http://localhost:8080/cart";

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
                                <Card.Text>
                                    {value}
                                </Card.Text>
                                <button type="button">button</button>
                            </Card.Body>
                        </Card>
                    )}
                </div>
            </div>
        );
    }
};

export default CartPage;
