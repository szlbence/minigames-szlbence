import React from 'react';
import './App.css';
import {BrowserRouter as Router, Routes, Route}
    from 'react-router-dom';
import ContactPage from "./pages/ContactPage";
import PremadeBoxesPage from "./pages/PremadeBoxesPage";
import CustomBoxesPage from "./pages/CustomBoxesPage";
import HomePage from "./pages/HomePage";
import { MDBFooter } from 'mdb-react-ui-kit';
import BootstrapNavbar from "./components/Navbar/BootstrapNavbar";


function App() {
    return (
        <>
            <div>
                <Router>
                    <BootstrapNavbar/>
                    <Routes>
                        <Route exact path='/' exact element={<HomePage/>}/>
                        <Route path='/contact' element={<ContactPage/>}/>
                        <Route path='/premade-boxes' element={<PremadeBoxesPage/>}/>
                        <Route path='/custom-boxes' element={<CustomBoxesPage/>}/>
                    </Routes>
                </Router>
            </div>
            <MDBFooter  className='text-center text-lg-left'>
                <div className='text-center p-3' style={{ backgroundColor: "#F3E5AB" }}>
                    &copy; {new Date().getFullYear()} Copyright:{' '}
                    GiftRocket Team
                </div>
            </MDBFooter>
        </>
    );
}

export default App;
