import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import DataService from "./DataService";
import {useRef} from "react";


function DropDown({props}) {
    const BASE_URL = "/research";
    const items = useRef([]);


    async function getProductsByCategory(category) {
        const products = await DataService.getProductsByCategory(`${BASE_URL}/category/${category}`);
        return products.data;
    }

    const handleSelect = async (e)=>{
        items.current = await getProductsByCategory(e);
        return props(items.current);

    }

    return (
        <DropdownButton id="dropdown-category"
                        title="Select category"
                        onSelect={handleSelect}
        >
            <Dropdown.Item eventKey="ORE">Ores</Dropdown.Item>
            <Dropdown.Item eventKey="ACCESSORIES">Accessories</Dropdown.Item>
        </DropdownButton>
    );
}

export default DropDown;
