import React, {useEffect, useState} from "react";
import ReactDOM from 'react-dom';
import "../App.css"
import Card from "react-bootstrap/Card";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTrash} from "@fortawesome/free-solid-svg-icons";
import DataService from "../components/DataService"
import Button from 'react-bootstrap/Button'

const Contact = () => {
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [totalPrice, setTotalPrice] = useState([]);
    const [totalCoin, setTotalCoin] = useState([]);
    const [totalCpC, setTotalCpC] = useState([]);
    const UPGRADES_URL = "/upgrades";
    const UPGRADES_TOTAL_PRICE_URL = "/upgrades/value";
    const USER_URL = "/user";


    const [particles, setParticles] = useState([]);

    const handleClick = (e) => {
        const randomDirection = Math.random() * 360;
        const distance = 300;

        const deltaX = Math.cos(randomDirection) * distance;
        const deltaY = Math.sin(randomDirection) * distance;

        const particle = {
            id: particles.length,
            style: {
                top: `${e.clientY}px`,
                left: `${e.clientX}px`,
                transform: `translate(${deltaX}px, ${deltaY}px)`,
            },
        };

        setParticles([...particles, particle]);

        setTimeout(() => {
            setParticles((prevState) => prevState.filter((p) => p !== particle));
        }, 1000);
    };

    const renderParticles = particles.map((particle) => (
        <div key={particle.id} className="particle" style={particle.style}>
            +1
        </div>
    ));







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


    useEffect(() => {
        if (cookie){
            getUpgrades();
            getTotalPrice();
            getTotalCoin();
            getTotalCpC();
        }}, [])
    if (cookie) {
        if (!isLoaded) {
            return <div>Loading...</div>;
        } else {
            return (

                <div className="container">

                    <h1 style={{textAlign: "center"}}>TOTAL CPC: {totalCpC}, Coins spent: {totalPrice}, Coins mined: {totalCoin}, Available coins: {totalCoin-totalPrice}</h1>
                    <div className="gold-ore-button-container">
                        <button className="gold-ore-button" onClick={handleClick}>
                            Gold Ore
                        </button>
                        {renderParticles}
                    </div>

                    <br></br>
                    <br></br>
                    <br></br>
                </div>
            );
        }
    }
    else{
        location.replace("/login");
    }
};

export default Contact;


// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYW5pIiwicm9sZSI6WyJBRE1JTiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvdXNlci9sb2dpbiJ9.TcbXvaiCaxqYiWx6M-sSKPJIVecu92eKf8_c3U1xHZw
// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYW5pIiwicm9sZSI6WyJBRE1JTiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvdXNlci9sb2dpbiJ9.TcbXvaiCaxqYiWx6M-sSKPJIVecu92eKf8_c3U1xHZw
// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCZW5jZSIsInJvbGUiOlsiVVNFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvdXNlci9sb2dpbiJ9.h9NNJz0rFI_JJakXZUVUpOyffrBbI65TauHUjBSi2T8
