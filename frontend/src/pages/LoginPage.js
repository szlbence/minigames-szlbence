import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import DataService from "../components/DataService";


const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const user = {
        "username" : "Jani",
        "password" : "password"
    }

    async function clickSend(user) {
        console.log(user)
        try {
            await DataService.postWithBody("/user/login", user);
            // navigate("/");

        }
        catch(error){
            console.log("Error: " + error)
        }


    }
    return (
        <div>
            <form name='f' action="/user/login" method='POST'>
                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' value={username} onChange={(e) => setUsername(e.target.value)} /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' value={password} onChange={(e) => setPassword(e.target.value)} /></td>
                    </tr>
                    <tr>
                        <td><button name="submit" type="button" value="submit" onClick={() => clickSend(user)}>Send</button></td>
                    </tr>
                </table>
            </form>
        </div>
    );
};

export default LoginPage;