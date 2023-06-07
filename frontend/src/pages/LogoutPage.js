import React from 'react';
import {useNavigate} from "react-router-dom";
import {get_cookie} from "../utils/authentications";


const Logout = () => {
    const nav = useNavigate();

    function delete_cookie( name, path ) {
        if( get_cookie( name ) ) {
            document.cookie = name + "=" +
                ((path) ? ";path="+path:"")+
                ";expires=Thu, 01 Jan 1970 00:00:01 GMT";
        }
    }

    delete_cookie("token", "/");
    return (
        <div>
            <h1>You have successfully logged out!</h1>
        </div>
    );

};

export default Logout;
