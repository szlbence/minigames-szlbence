import React from "react";

import {useEffect, useState} from "react";
import "../App.css"
import Card from "react-bootstrap/Card";
import DataService from "../components/DataService";
import Button from "react-bootstrap/Button";
import {MinerScoreboard} from "../components/MinerScoreboard";
import {getResearches, getUpgrades, getTotalCoin, getTotalPrice, getTotalCpC} from "../utils/apis";
import {Loader} from "../components/Loader";
import {parseJwt} from "../utils/authentications";

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

                await getUpgrades(UPGRADES_URL, setIsLoaded, setUpgrades);
                await getResearches(RESEARCH_URL, UPGRADES_URL,setIsLoaded, setItems);
                await getTotalPrice(UPGRADES_TOTAL_PRICE_URL,setTotalPrice);
                await getTotalCoin(USER_URL,user, setTotalCoin);
                await getTotalCpC(USER_URL, user, setTotalCpC);
            }
        catch (error) {
            console.log("Cannot add to upgrades: " + error);
            }
        }
        else{alert("Insufficient coins! Keep on clickin'! ")};
    }

    let cookie = document.cookie;
    let cookieValue = cookie.slice(6);
    let user = null;
    if (cookie){
        user = (parseJwt(cookieValue)).sub;}


    useEffect(() => {
        if (cookie){
        getUpgrades(UPGRADES_URL, setIsLoaded, setUpgrades);
        getResearches(RESEARCH_URL, UPGRADES_URL,setIsLoaded, setItems);

        getTotalPrice(UPGRADES_TOTAL_PRICE_URL,setTotalPrice);
        getTotalCoin(USER_URL,user, setTotalCoin);
        getTotalCpC(USER_URL, user, setTotalCpC);
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
                            {items.map(item =>
                                    <Card key={item.id}>
                                    <Card.Header className="bg-success text-light ">{item.name}</Card.Header>
                                    <Card.Body>
                                    <img className="homeImg" src={`${item.name.replace(" ", "_")}.jpeg`}
                                    />
                                    <p className="price">Research price: {item.price} Coins</p>
                                    <p className="cpc">CpC increase: {item.cpc}</p>
                                    <p className="description">{item.description}</p>
                                    <Button type="submit" bsPrefix="my-purple-button bg-success" style={{borderColor: '#198754'}} onClick={() => {
                                    AddToUpgrades(upgrades[0].id, item.id, item.price)
                                }}>Research</Button>
                                    </Card.Body>
                                    </Card>
                            )}
                        </div>
                    </div>
            );
        }
    }
    else {
        location.replace("/login");
    }
};

export default ResearchPage;
