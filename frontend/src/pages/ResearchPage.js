import React from "react";

import {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import DataService from "../components/DataService";
import DropDown from "../components/DropDown";
import Button from "react-bootstrap/Button";
import {redirect} from "react-router-dom";
import {forEach} from "react-bootstrap/ElementChildren";

const ResearchPage = () => {

    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [upgrades, setUpgrades] = useState([]);
    const [totalPrice, setTotalPrice] = useState([]);
    const [totalCoin, setTotalCoin] = useState([]);
    const [totalCpC, setTotalCpC] = useState([]);
    const RESEARCH_URL = "/research";
    const UPGRADES_URL = "/upgrades";
    const UPGRADES_TOTAL_PRICE_URL = "/upgrades/value";

    const USER_URL = "/user";

    async function AddToUpgrades(upgradesId, researchId, researchPrice) {
        if (totalPrice + researchPrice <= totalCoin ) {
            try {
                await DataService.postData(`${UPGRADES_URL}/${upgradesId}/add/${researchId}`);
                await  DataService.sendPut(`${USER_URL}/${user}/add/${researchId}`);
                await getTotalPrice();
                await getUpgrades();
                await getResearchs();
                await getTotalCpC();
            }
        catch (error) {
            console.log("Cannot add to upgrades: " + error);
            }
        }
        else{alert("Insufficient coins! Keep on clickin'! ")};
    }

    async function getUpgrades() {
        try {
            const upgrades = await DataService.getData(UPGRADES_URL);
            setUpgrades(upgrades.data);
        } catch (error) {
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

    async function getResearchs() {
        try {
            const researchs = await DataService.getData(RESEARCH_URL);
            const upgrades = await DataService.getData(`${UPGRADES_URL}/1`);
            let finalResearches = [];
            for (let i = 0; i < researchs.data.length; i++) {
                let isInUpgrades = false;
                let name = researchs.data[i].name;
                for (let e = 0; e < upgrades.data.products.length; e++){
                    if (name === upgrades.data.products[e].product.name){
                        isInUpgrades = true;
                    }
                }
                if (!isInUpgrades){
                    finalResearches.push(researchs.data[i]) ;
                }
            }
            setIsLoaded(true);
            setItems(finalResearches);
        } catch (error) {
            console.log("Error loading researches: " + error);
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
    let user = null;
    if (cookie){
        user = (parseJwt(cookieValue)).sub;}

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

    useEffect(() => {
        if (cookie){
        getUpgrades();
        getResearchs();
        getTotalPrice();
        getTotalCoin();
        getTotalCpC();
    }}, [])
    if (cookie) {
        if (!isLoaded) {
            return <div>Loading...</div>;
        } else {
            return (
                <>
                    <DropDown props={setItems}/>
                    <div className="container">
                        <h1 style={{textAlign: "center"}}>TOTAL CPC: {totalCpC}, Coins spent: {totalPrice}, Coins mined: {totalCoin}, Available
                            coins: {totalCoin - totalPrice}</h1>
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
                                    AddToUpgrades(upgrades[0].id, item.id, item.price)
                                }}>Research
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
    }
    else {
        location.replace("/login");
    }
};

export default ResearchPage;
