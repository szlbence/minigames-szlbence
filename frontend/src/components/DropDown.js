import Dropdown from 'react-bootstrap/Dropdown';
import DropdownButton from 'react-bootstrap/DropdownButton';
import DataService from "./DataService";
import {useRef} from "react";


function DropDown({props}) {
    const BASE_URL = "/product";
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
            <Dropdown.Item eventKey="ALCOHOL">Alcohol</Dropdown.Item>
            <Dropdown.Item eventKey="SWEET">Sweet</Dropdown.Item>
            <Dropdown.Item eventKey="KID">Kid</Dropdown.Item>
        </DropdownButton>
    );
}

export default DropDown;