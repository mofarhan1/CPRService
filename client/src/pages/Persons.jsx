import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import axios from "axios";
import { Link, Navigate } from "react-router-dom";
import person from "../images/person.png";

const Persons = ({persons, setPersons,param, handleChange}) => {
  
  useEffect(() => {
    const fetchAllPersons = async () => {
      try {
        const res = await axios.get("http://localhost:8080/api/patients");
        setPersons(res.data);
        console.log(res.data);
      } catch (err) {
        console.log(err);
      }
    };
    fetchAllPersons();
  }, [setPersons,param]);

  

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8800/patients/${param}`);
      window.location.reload()
    } catch (err) {
      console.log(err);
    }
  };

  

  return (
    <div>
      <h1>CPR service</h1>
      <input 
        type="text"
        onChange={handleChange}
      />
      <button className="searchbtn">
        <Link  to={`/getPatient?id=${param}`}style={{ color: "inherit", textDecoration: "none" }}>
         Searh
        </Link>
      </button>
      <div className="person">
      {persons.map((person) => (
          <div key={person.id} className="person">
            <img  src={person} alt="" />
            <p>{person.firstname}</p>
            <p>{person.lastname}</p>
            <p>{person.id}</p>
          </div>
          ))}
      </div>

      
    </div>
  );
};

export default Persons;
