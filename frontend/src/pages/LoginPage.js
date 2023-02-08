import React, {useState} from 'react';
import DataService from "../components/DataService";




const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const user = {
        "username" : username,
        "password" : password
    }


    async function clickSend() {
        await DataService.postWithBody("/user/login", user);

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
                        <td><input name="submit" type="submit" value="submit" onClick={clickSend}/></td>
                    </tr>
                </table>
            </form>
        </div>
    );
};

export default LoginPage;