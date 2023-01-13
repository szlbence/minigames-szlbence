import React from "react";

import {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";


function AddToCart(id, productBoxId) {
    (async () => {
        await fetch(`/cart/${id}/add/${productBoxId}`, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
        });
    })();

}
const PremadeBoxes = () => {

    const [isLoaded, setIsLoaded] = useState({cart: false, productBox: false});
    const [items, setItems] = useState([]);
    const [carts, setCarts] = useState([])
    const PRODUCTBOX_URL = "/productbox";
    const CART_URL = "/cart";

    useEffect(() => {
        setIsLoaded({...isLoaded, cart: false});
        fetch(CART_URL)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded({...isLoaded, cart: true});
                    setCarts(result);
                }
            )
    }, [])

    useEffect(() => {
        // since we need to load something we are definitely not loaded yet
        setIsLoaded({...isLoaded, productBox: false});

        // we might navigate somewhere else before the loading is
        // done.  In that case let's have a way to cancel the loading.
        const abortController = new AbortController();

        fetch(PRODUCTBOX_URL,
              {
                  // way to communicate with ongoing fetch from "outside"
                  signal: abortController.signal
              })
            .then(res => {
                // check if we really got back the data
                if (res.status == 200) {
                    return res.json();
                } else {
                    // TODO: display error on UI
                }
            })
            .then(
                (result) => {
                    // now the data is definitely loaded
                    setIsLoaded({...isLoaded, productBox: true});
                    setItems(result);
                }
            )

        // create a callback that can abort the fetch
        const cleanUp = () => {
            abortController.abort();
        }

        // register the clean-up callback with the React app
        return cleanUp;
    }, [])
    // TODO: only skip rendering in parts of component
    if (!isLoaded.cart || !isLoaded.productBox) {
        return <div>Loading...</div>;
    } else {
        return (
            <div className="container">
                <div className="grid">
                    {items.map((item, index) =>
                        <Card key={`${item.name}--${index}`} style={{width: '36rem'}}>
                            <Card.Header><Card.Img variant="top" src="holder.js/100px180"/></Card.Header>
                            <Card.Body>
                                <Card.Title>{item.name}</Card.Title>
                                <Card.Text>
                                    <p className="price">Total price: {item.totalPrice}</p>
                                    <p className="description">Description: {item.description}</p>
                                    <p className="description">Products: {Object.keys(item.products).join(", ")}</p>
                                    <button type="submit" onClick={() => {  AddToCart(carts[0].id, item.id)}}>Add To Cart</button>
                                </Card.Text>
                            </Card.Body>
                        </Card>
                    )}
                </div>
            </div>
        );
    }
};

export default PremadeBoxes;
