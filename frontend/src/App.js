import React from 'react';
import './App.css';
import Navbar from './components/Navbar';
import { BrowserRouter as Router, Routes, Route}
    from 'react-router-dom';
import ContactPage from "./pages/ContactPage";
import PremadeBoxesPage from "./pages/PremadeBoxesPage";
import CustomBoxesPage from "./pages/CustomBoxesPage";
import Home from './pages/HomePage'
import HomePage from "./pages/HomePage";


function App() {
    return (
        <Router>
            <Navbar />
            <Routes>
                <Route exact path='/' exact element={<HomePage/>} />
                <Route path='/contact' element={<ContactPage/>} />
                <Route path='/premade-boxes' element={<PremadeBoxesPage/>} />
                <Route path='/custom-boxes' element={<CustomBoxesPage/>} />
            </Routes>
        </Router>
    );
}

export default App;
