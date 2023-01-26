import React from 'react';
import './App.css';
import {BrowserRouter as Router, Routes, Route}
    from 'react-router-dom';
import ContactPage from "./pages/ContactPage";
import ProductsPage from "./pages/ProductsPage";
import CartPage from "./pages/CartPage";
import HomePage from "./pages/HomePage";
import { MDBFooter } from 'mdb-react-ui-kit';
import BootstrapNavbar from "./components/Navbar/BootstrapNavbar";
import Nav from "react-bootstrap/Nav";


function App() {
    return (
        <>
            <div>
                <Router>
                    <BootstrapNavbar/>
                    <Routes>
                        <Route exact path='/' exact element={<HomePage/>}/>
                        <Route path='/contact' element={<ContactPage/>}/>
                        <Route path='/product' element={<ProductsPage/>}/>
                        <Route path='/cart' element={<CartPage/>}/>
                    </Routes>
                </Router>
            </div>
            <footer  className='text-center text-lg-left'>
                <div className='text-center p-4' style={{ backgroundColor: "#F3E5AB" }}>
                    &copy; {new Date().getFullYear()} Copyright:{' '}
                    GiftRocket Team
                </div>
            </footer>
        </>
    );
}

export default App;
