import React from 'react';
import './App.css';
import {BrowserRouter as Router, Routes, Route}
    from 'react-router-dom';
import MinerPage from "./pages/MinerPage";
import ResearchPage from "./pages/ResearchPage";
import UpgradesPage from "./pages/UpgradesPage";
import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import LogoutPage from "./pages/LogoutPage";
import { MDBFooter } from 'mdb-react-ui-kit';
import BootstrapNavbar from "./components/Navbar/BootstrapNavbar";
import Nav from "react-bootstrap/Nav";
import DropDown from "./components/DropDown";


function App() {
    return (
        <>
            <div>
                <Router>
                    <BootstrapNavbar/>
                    <Routes>
                        <Route exact path='/' exact element={<HomePage/>}/>
                        <Route path='/miner' element={<MinerPage/>}/>
                        <Route path='/research' element={<ResearchPage/>}/>
                        <Route path='/upgrades' element={<UpgradesPage/>}/>
                        <Route path="/login" element={<LoginPage/>}/>
                        <Route path="/logout" element={<LogoutPage/>}/>
                    </Routes>
                </Router>
            </div>
            <footer  className='text-center p-4'>
                    &copy; {new Date().getFullYear()} Copyright:{' '}
                    Minigames
            </footer>
        </>
    );
}

export default App;
