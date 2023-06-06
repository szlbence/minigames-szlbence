import React, {useEffect, useState} from "react";
import "../App.css"
import DataService from "../components/DataService"
import {MinerScoreboard} from "../components/MinerScoreboard";
import {getTotalCoin, getTotalCpC, getTotalPrice, getUpgrades} from "../utils/apis";

const Contact = () => {
    const [isLoaded, setIsLoaded] = useState(false);
    const [items, setItems] = useState([]);
    const [totalPrice, setTotalPrice] = useState([]);
    const [totalCoin, setTotalCoin] = useState([]);
    const [totalCpC, setTotalCpC] = useState([]);
    const UPGRADES_URL = "/upgrades";
    const UPGRADES_TOTAL_PRICE_URL = "/upgrades/value";
    const USER_URL = "/user";
    const [showPlusSign, setShowPlusSign] = useState(false);

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
        }, 200);
    };

    const renderParticles = particles.map((particle) => (
        <div key={particle.id} className="particle" style={particle.style}>
            +1
        </div>
    ));

    async function increaseCoinQuantity(user) {
        try {
                await  DataService.sendPut(`${USER_URL}/${user}/coin`);
                await getTotalPrice(UPGRADES_TOTAL_PRICE_URL, setTotalPrice);
                await getUpgrades(USER_URL, setIsLoaded, setItems);
                await getTotalCpC(USER_URL, user, setTotalCpC);
                await getTotalCoin(USER_URL, user, setTotalCoin);
            } catch (error) {
                console.log("Cannot increase coin quantity: " + error);
            }
            showPointIncrement();
    }

    function showPointIncrement() {
        setShowPlusSign(true);
        setTimeout(() => {
            setShowPlusSign(false);
        }, 1000);
    }

     function checkForGoldOre(){
        let goldOreFound = false;
        for (let i=0;i<items[0].products.length;i++){
            if (items[0].products[i].product.name == "Gold Mine"){
                goldOreFound = true;
            }
        }

        if (goldOreFound){
            return(
                <div className="gold-ore-button-container">
                    <button className="gold-ore-button" onClick={async () => await increaseCoinQuantity(user)}>
                        {showPlusSign && (
                            <div className="plus-sign" style={{ top: '400px', left: '50%', position: 'absolute', transform: 'translateX(-50%)', fontSize: '24px', color: 'green' }}>
                                + {totalCpC}
                            </div>
                        )}
                        <img width="450" height="300" src={`Gold_Ore.jpeg`} alt="A beautiful Golden Ore"></img>
                    </button>
                </div>)
        }
        else{
            return(
                <div className="gold-ore-button-container">
                    <button className="gold-ore-button" onClick={async () => await increaseCoinQuantity(user)}>
                        {showPlusSign && (
                            <div className="plus-sign" style={{ top: '400px', left: '50%', position: 'absolute', transform: 'translateX(-50%)', fontSize: '24px', color: 'green' }}>
                                + {totalCpC}
                            </div>
                        )}
                        <img width="450" height="300" src={`Silver_Ore.jpeg`} alt="A beautiful Golden Ore"></img>
                    </button>
                </div>)
        }
    }


    function parseJwt(token) {
        if (!token) { return; }
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
    }

    let cookie = document.cookie;
    let cookieValue = cookie.slice(6);
    let user = null;
    if (cookie){
        user = (parseJwt(cookieValue)).sub;}


    useEffect(() => {
        if (cookie){
            getUpgrades(UPGRADES_URL,setIsLoaded, setItems);
            getTotalPrice(UPGRADES_TOTAL_PRICE_URL, setTotalPrice);
            getTotalCoin(USER_URL,user, setTotalCoin);
            getTotalCpC(USER_URL, user, setTotalCpC);
        }}, [])

    if (cookie) {
        if (!isLoaded) {
            return <div>Loading...</div>;
        } else {
            return (

                <div className="container">

                    {/*<h1 style={{textAlign: "center"}}>TOTAL CPC: {totalCpC}, Coins spent: {totalPrice}, Coins mined: {totalCoin}, Available coins: {totalCoin-totalPrice}</h1>*/}
                    <MinerScoreboard
                        totalCpC={totalCpC}
                        totalCoin={totalCoin}
                        totalPrice={totalPrice}
                    />
                    {checkForGoldOre()}
                </div>
            );
        }
    }
    else{
        location.replace("/login");
    }
};

export default Contact;
