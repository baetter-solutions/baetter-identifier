import * as React from 'react';
import './App.css';
import Navbar from "./components/Navbar";

const linksArray = ["Import", "Kalkulation", "Export", "Stammdaten"];

function App() {
  return (
    <div className="App">
            <Navbar links={linksArray}/>
    </div>
  );
}

export default App;
