import axios from "axios";
import React, { useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import personDetail from "../images/personDetail.jpg"
const PersonDetail = ({param}) => {
  const [person, setPerson] = useState([]);
  console.log(param)

  useEffect(() => {
    const fetchPerson = async () => {
      try {
        const res = await axios.get(`http://localhost:8080/api/getPerson?id=${param}`);
        setPerson(res.data);
        console.log(res.data);
      } catch (err) {
        console.log(err);
      }
    };
    fetchPerson();
  }, [param]);





  const{id,firstname,lastname,gender,birthdate,
  address,zip,guardian,comment}=person


  return (
    <div className="detail">
      <img src={personDetail} alt="user" />
      <p>{id}</p>
      <p>{firstname}</p>
      <p>{lastname}</p>
      <p>{gender}</p>
       <p>{birthdate}</p>
       <p>{address}</p>
        <p>{zip}</p>
         <p>{guardian}</p>
         <p>{comment}</p>
    </div>
  );
};

export default PersonDetail;
