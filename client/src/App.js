import { BrowserRouter, Routes, Route } from "react-router-dom";
import Persons from "./pages/Persons";
import PersonDetail from "./pages/PersonDetail";
import React from "react";

function App() {
  const [persons, setPersons] = React.useState([]);
  const [param, setParam] = React.useState("");


  const handleChange = (e) => {
    setParam(e.target.value);
      console.log(param) 
  };

  return (
    <div className="app">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Persons persons={persons} setPersons={setPersons} param={param} handleChange={handleChange}  />} />
          <Route path="/getPerson" element={<PersonDetail param={param}  />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
