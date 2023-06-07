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
import BootstrapNavbar from "./components/Navbar/BootstrapNavbar";


function App() {
    return (
        <>
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
            <footer  className='text-center'>
                    &copy; {new Date().getFullYear()} Copyright:{' '}
                    Minigames
            </footer>
        </>
    );
}

export default App;
