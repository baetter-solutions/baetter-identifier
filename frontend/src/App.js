import * as React from 'react';
import './App.css';
import {Routes, Route} from 'react-router-dom'
import {Main} from './components/Main'
import Fileupload from "./components/Fileupload";
import Calculation from "./components/Calculation";
import {Export} from "./components/Export";
import {ReferenceData} from "./components/ReferenceData";
import {About} from "./components/About";
import {Navbar} from "./components/Navbar";

function App() {
    return (
        <>
            <Navbar/>
            <Routes>
                <Route path='/' element={<Main/>}/>
                <Route path='Fileupload' element={<Fileupload/>}/>
                <Route path='Calculation' element={<Calculation/>}/>
                <Route path='Export' element={<Export/>}/>
                <Route path='ReferenceData' element={<ReferenceData/>}/>
                <Route path='About' element={<About/>}/>
            </Routes>
        </>
    );
}

export default App;
