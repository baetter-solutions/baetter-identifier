import * as React from 'react';
import './App.css';
import Navbar from "./components/Navbar";
import {Routes, Route} from 'react-router-dom'
import {Main} from './components/Main'
import {About} from "./components/About";
// import {Nav2Bar} from "./components/Nav2Bar";
import Fileupload from "./components/Fileupload";
import {ReferenceData} from "./components/ReferenceData";
import {Export} from "./components/Export";
import {Calculation} from "./components/Calculation";
import {Home} from "@mui/icons-material";

// const linksArray = ["Import", "Kalkulation", "Export", "Stammdaten"];
const linksArray = [
    { label: "Main", path: "/" },
    { label: "Import", path: "import" },
    { label: "Kalkulation", path: "calculation" },
    { label: "Export", path: "export" },
    { label: "Stammdaten", path: "refData" },
    { label: "About", path: "about" }
]

function App() {
    return (
        <>
            {/*<Nav2Bar/>*/}
            <Navbar links={linksArray}/>
            <Routes>
                <Route path='/' element={<Main/>}/>
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
