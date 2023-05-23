import React from 'react';

const Contact = () => {
    function parseJwt(token) {
        if (!token) { return; }
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
    }

    let cookie = document.cookie;
    let cookieValue = cookie.slice(6);

    console.log(parseJwt(cookieValue))
    return (
        <div>
            <h1>Coming Soon! Members are Daniel Dudas, Gabor Nagy, Andras Csovari, Bence Szlavik</h1>
        </div>
    );
};

export default Contact;


// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYW5pIiwicm9sZSI6WyJBRE1JTiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvdXNlci9sb2dpbiJ9.TcbXvaiCaxqYiWx6M-sSKPJIVecu92eKf8_c3U1xHZw
// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKYW5pIiwicm9sZSI6WyJBRE1JTiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvdXNlci9sb2dpbiJ9.TcbXvaiCaxqYiWx6M-sSKPJIVecu92eKf8_c3U1xHZw
// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJCZW5jZSIsInJvbGUiOlsiVVNFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvdXNlci9sb2dpbiJ9.h9NNJz0rFI_JJakXZUVUpOyffrBbI65TauHUjBSi2T8
