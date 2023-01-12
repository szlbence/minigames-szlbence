
import React from "react";
import {useEffect, useState} from "react";
import "../PremadeBoxes.css"
import image from "../gift-box.jpg";
import Button from "../components/Button";


const PremadeBoxes = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const PRODUCTBOX_URL = "http://localhost:8080/productbox";

    useEffect(() => {
        fetch(PRODUCTBOX_URL)
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setItems(result);
                }
            )
    }, [items])
    if (!isLoaded) {
        return <div>Loading...</div>;
    } else {
        return (
            // <ul>
            //     {items.map(item => (
            //         <li key={item.id}>
            //             {item.name} {item.totalPrice} {item.description} {}
            //         </li>
            //     ))}
            // </ul>
            <div className="container">
                    {items.map(item => 
                    <div className="card" key={item.id}>
                        <img src={image} width={200}/>
                        <p className="name">Name: {item.name} </p>
                        <p className="price">Total price: {item.totalPrice}</p>
                        <p className="description">Description: {item.description}</p>
                        <p className="description">Products: {Object.keys(item.products).join(", ")}</p>
                        <Button text="Add to cart"/>
                    </div>
                    )}
                    
            </div>
        );
    }
};

export default PremadeBoxes;
