package com.example.cprService.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "address")
    private String address;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthdate")
    private String birthdate;

    @Column(name = "zip")
    private String zip;

    @Column(name = "guardian")
    private String guardian;

    @Column(name = "comment")
    private String comment;



    public void setAddress(String address) {
        this.address = address;
    }


    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getAddress() {
        return address;
    }


    public String getGuardian() {
        return guardian;
    }

    public String getComment() {
        return comment;
    }


    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }


}
