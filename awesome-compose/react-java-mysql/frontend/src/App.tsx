import React, { useEffect, useState } from "react";
import logo from "./logo.svg";
import "./App.css";
//theme
import "primereact/resources/themes/lara-light-indigo/theme.css";

//core
import "primereact/resources/primereact.min.css";
import UsersPicker from "./Components/UsersPicker";
import AllMovies from "./Components/AllMovies";
import CustomToolBar from "./Components/CustomToolBar";
import MainBlock from "./Components/MainBlock";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <CustomToolBar />
        <MainBlock />
      </header>
    </div>
  );
}

export default App;
