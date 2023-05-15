import * as React from 'react';
import './App.css';
import Navbar from "./components/Navbar";
import {Routes, Route} from 'react-router-dom'
import {Home} from './components/Home'
import {About} from "./components/About";
import {Nav2Bar} from "./components/Nav2Bar";
import Fileupload from "./components/Fileupload";
import {ReferenceData} from "./components/ReferenceData";
import {Export} from "./components/Export";
import {Calculation} from "./components/Calculation";
import {Home} from "@mui/icons-material";

const linksArray = ["Import", "Kalkulation", "Export", "Stammdaten"];

function App() {
    return (
        <>
            {/*<Nav2Bar/>*/}
            <Navbar links={linksArray}/>
            <Routes>
                <Route path='/' element={<Home/>}/>
                <Route path='import' element={<Fileupload/>}/>
                <Route path='calculation' element={<Calculation/>}/>
                <Route path='export' element={<Export/>}/>
                <Route path='refData' element={<ReferenceData/>}/>
                <Route path='about' element={<About/>}/>
                {/*<div className="App"/>*/}
            </Routes>
        </>
    );
}

export default App;
