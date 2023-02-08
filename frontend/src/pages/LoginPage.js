import React from 'react';

const LoginPage = () => {
    return (
        <div>
            <form name='f' action="/login" method='POST'>
                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='username' /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='password' /></td>
                    </tr>
                    <tr>
                        <td><input name="submit" type="submit" value="submit" /></td>
                    </tr>
                </table>
            </form>
        </div>
    );
};

export default LoginPage;