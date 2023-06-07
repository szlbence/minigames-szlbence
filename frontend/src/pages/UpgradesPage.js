import React, {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTrash} from "@fortawesome/free-solid-svg-icons";
import DataService from "../components/DataService"
import Button from 'react-bootstrap/Button'
import {MinerScoreboard} from "../components/MinerScoreboard";
import {Loader} from "../components/Loader";
import {parseJwt} from "../utils/authentications";

const UpgradesPage = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [totalPrice, setTotalPrice] = useState([]);
    const [totalCoin, setTotalCoin] = useState([]);
    const [totalCpC, setTotalCpC] = useState([]);
    const UPGRADES_URL = "/upgrades";
    const UPGRADES_TOTAL_PRICE_URL = "/upgrades/value";
    const USER_URL = "/user";

    async function increaseProductQuantity(upgradesId, productId, productPrice, user) {
        if (totalPrice + productPrice <= totalCoin ) {
            try {
                await DataService.sendPut(`${UPGRADES_URL}/${upgradesId}/add/${productId}`);
                await  DataService.sendPut(`${USER_URL}/${user}/add/${productId}`);
                await getTotalPrice();
                await getUpgrades();
                await getTotalCpC();
            } catch (error) {
                console.log("Cannot increase product quantity: " + error);
            }
        }
        else{
            alert("Insufficient coins! Keep on clickin'! ")

        }
    }

    async function decreaseProductQuantity(upgradesId, productId) {
        try{
            await DataService.sendPut(`${UPGRADES_URL}/${upgradesId}/remove/${productId}`);
            await  DataService.sendPut(`${USER_URL}/${user}/remove/${productId}`);
            await getTotalPrice();
            await getUpgrades();
            await getTotalCpC();
        }
        catch (error){
            console.log("Cannot decrease product quantity: " + error);
        }
    }

    async function deleteProduct(upgradesId, productId) {
        try{
            await DataService.sendDelete(`${UPGRADES_URL}/${upgradesId}/remove/${productId}`);
            await  DataService.sendDelete(`${USER_URL}/${user}/remove/${productId}`);
            await getTotalPrice();
            await getUpgrades();
            await getTotalCpC();
        }
        catch (error){
            console.log("Cannot delete product: " + error);
        }
    }

    async function getUpgrades() {
        try{
            const upgrades = await DataService.getData(UPGRADES_URL);
            setIsLoaded(true);
            setItems(upgrades.data);
        }
        catch (error){
            console.log("Error loading upgrades: " + error);
        }
    }

    async function getTotalPrice() {
        try{
            const totalPrice = await DataService.getData(`${UPGRADES_TOTAL_PRICE_URL}/1`);
            setTotalPrice(totalPrice.data);
        }
        catch(error){
            console.log("Error loading total price: " + error);
        }
    }

    async function getTotalCoin() {
        try{
            const totalCoin = await DataService.getData(`${USER_URL}/${user}/coin`);
            setTotalCoin(totalCoin.data);
        }
        catch(error){
            console.log("Error loading total price: " + error);
        }
    }

    async function getTotalCpC() {
        try{
            const totalCpC = await DataService.getData(`${USER_URL}/${user}/cpc`);
            setTotalCpC(totalCpC.data);
        }
        catch(error){
            console.log("Error loading total price: " + error);
        }
    }

    function getTotalCpCForUpgrade(quantity, cpc){
        return quantity*cpc;
    }

    let cookie = document.cookie;
    let cookieValue = cookie.slice(6);
    let user = null;
    if (cookie){
        user = (parseJwt(cookieValue)).sub;}

    useEffect(() => {
        if (cookie){
        getUpgrades();
        getTotalPrice();
        getTotalCoin();
        getTotalCpC();
    }}, [])

    if (cookie) {
        if (!isLoaded) {
            return <Loader/>;
        } else {
            return (

                <div className="container">
                    <MinerScoreboard
                        totalCpC={totalCpC}
                        totalCoin={totalCoin}
                        totalPrice={totalPrice}
                    />

                    <div className="grid d-flex justify-content-center">
                        {items[0].products.map(upgradesProduct => //items[0] only until we have user login. until then only 1 upgrades is available.
                            <Card key={upgradesProduct.product.id}>
                                <Card.Header className="bg-danger text-light">{upgradesProduct.product.name}</Card.Header>
                                <Card.Body>
                                    <img className="homeImg" src={`${upgradesProduct.product.name.replace(" ", "_")}.jpeg`} style={{objectFit: "cover", width: 2000}}/>
                                    <div className="container">
                                        <p>Total quantity of products : {upgradesProduct.quantity} </p>
                                        <p>Price of product: {upgradesProduct.product.upgradePrice}</p>
                                        <p>CpC increase: {upgradesProduct.product.cpc}</p>
                                        <p>Total CpC generation: {getTotalCpCForUpgrade(upgradesProduct.quantity, upgradesProduct.product.cpc)}</p>
                                    </div>
                                    <div>
                                        <Button type="submit" bsPrefix="my-purple-button bg-danger" style={{borderColor: '#dc3545'}} size="sm" onClick={async() => await increaseProductQuantity(items[0].id, upgradesProduct.product.id, upgradesProduct.product.upgradePrice, user)}>+</Button>
                                        <Button type="submit" bsPrefix="my-purple-button bg-danger" style={{borderColor: '#dc3545'}} size="sm" onClick={async() =>await decreaseProductQuantity(items[0].id, upgradesProduct.product.id)}>-</Button>
                                        <Button type="button" bsPrefix="my-purple-button bg-danger" style={{borderColor: '#dc3545'}} size="sm" onClick={async() =>await deleteProduct(items[0].id, upgradesProduct.product.id)}><FontAwesomeIcon icon={faTrash}/></Button>
                                    </div>
                                </Card.Body>
                            </Card>
                        )}
                    </div>
                </div>
            );
        }
    }
    else{
        location.replace("/login");
    }
};

export default UpgradesPage;
