import * as React from 'react';
import './App.css';
import {Routes, Route} from 'react-router-dom'
import {Main} from './components/Main'
import Calculation from "./components/Calculation";
import {Export} from "./components/Export";
import ReferenceData from "./components/ReferenceData";
import {About} from "./components/About";
import {Navbar} from "./components/Navbar";
import CustomerFile from "./components/CustomerFile";

function App() {
    return (
        <>
            <Navbar/>
            <Routes>
                <Route path='/' element={<Main/>}/>
                <Route path='CustomerFile' element={<CustomerFile/>}/>
                <Route path='Calculation' element={<Calculation/>}/>
                <Route path='Export' element={<Export/>}/>
                <Route path='ReferenceData' element={<ReferenceData/>}/>
                <Route path='About' element={<About/>}/>
            </Routes>
        </>
    );
}

export default App;
