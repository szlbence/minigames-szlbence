import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import DataService from "../components/DataService";


const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [authenticationFailed, setAuthenticationFailed] = useState(false);
    const navigate = useNavigate();
    const user = {
        username : username,
        password : password
    }

    async function clickSend(user) {
        try {
            await DataService.postWithBody("/user/login", user);
            navigate("/");

        }
        catch(error){
            if(error.response.status === 403){
                setAuthenticationFailed(true);
            }
            else{
                console.log("Error: " + error)
            }
        }

    }
    return (
        <div className="login-form-container">
            <h2 class="login-title">Log In</h2>
            <form className="login-form" name='f' action="/user/login" method='POST'>
                <div>
                    <label htmlFor="username">Username</label>
                    <input id="username" type='text' value={username} placeholder="Insert name..." onChange={(e) => setUsername(e.target.value)} />
                </div>
                <div>
                    <label htmlFor="password">Password</label>
                    <input id="password" type='password' value={password} placeholder="Insert password..." onChange={(e) => setPassword(e.target.value)} />
                </div>
                <button className="my-purple-button" name="submit" type="button" value="submit" onClick={() => clickSend(user)}>Send</button>
            </form>
            {authenticationFailed &&
                <p>
                    Authentication failed!
                </p>
            }
        </div>

    // <div className="login-form-container">
    //         <form name='f' action="/user/login" method='POST'>
    //             <table>
    //                 <tr>
    //                     <td>User:</td>
    //                     <td><input type='text' value={username} onChange={(e) => setUsername(e.target.value)} /></td>
    //                 </tr>
    //                 <tr>
    //                     <td>Password:</td>
    //                     <td><input type='password' value={password} onChange={(e) => setPassword(e.target.value)} /></td>
    //                 </tr>
    //                 <tr>
    //                     <td><button className="my-purple-button" name="submit" type="button" value="submit" onClick={() => clickSend(user)}>Send</button></td>
    //                 </tr>
    //             </table>
    //         </form>
    //         {authenticationFailed &&
    //             <p>
    //                 Authentication failed!
    //             </p>
    //         }
    //     </div>
    );
};

export default LoginPage;
