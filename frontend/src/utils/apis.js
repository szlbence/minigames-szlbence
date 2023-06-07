import DataService from "../components/DataService";

export async function getTotalPrice(UPGRADES_TOTAL_PRICE_URL, setTotalPrice) {
    try{
        const totalPrice = await DataService.getData(`${UPGRADES_TOTAL_PRICE_URL}/1`);
        setTotalPrice(totalPrice.data);
    }
    catch(error){
        console.info("Error loading total price: ", error);
    }
}

export async function getTotalCoin(USER_URL, user, setTotalCoin) {
    try{
        const totalCoin = await DataService.getData(`${USER_URL}/${user}/coin`);
        setTotalCoin(totalCoin.data);
    }
    catch(error){
        console.log("Error loading total price: " + error);
    }
}

export async function getTotalCpC(USER_URL, user, setTotalCpC) {
    try{
        const totalCpC = await DataService.getData(`${USER_URL}/${user}/cpc`);
        setTotalCpC(totalCpC.data);
    }
    catch(error){
        console.log("Error loading total price: " + error);
    }
}

export async function getUpgrades(UPGRADES_URL, setIsLoaded, setUpgrades) {
    try{
        const upgrades = await DataService.getData(UPGRADES_URL);
        setIsLoaded(true);
        setUpgrades(upgrades.data);
    }
    catch (error){
        console.log("Error loading upgrades: " + error);
    }
}

export async function getResearches(RESEARCH_URL, UPGRADES_URL, setIsLoaded, setItems) {
    try {
        const researches = await DataService.getData(RESEARCH_URL);
        const upgrades = await DataService.getData(`${UPGRADES_URL}/1`);
        let finalResearches = [];
        for (let i = 0; i < researches.data.length; i++) {
            let isInUpgrades = false;
            let name = researches.data[i].name;
            for (let e = 0; e < upgrades.data.products.length; e++){
                if (name === upgrades.data.products[e].product.name){
                    isInUpgrades = true;
                }
            }
            if (!isInUpgrades){
                finalResearches.push(researches.data[i]) ;
            }
        }
        setIsLoaded(true);
        setItems(finalResearches);
    } catch (error) {
        console.log("Error loading researches: " + error);
    }
}
