package DTO.entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author WIN 10
 */
public abstract class Person {
    protected int personID;
    protected String name;
    protected String tel;
    protected String address;
    protected int status;

    public Person(int personID, String name, String tel, String address, int status) {
        this.personID = personID;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.status = status;
    }
    public Person(int personID, String name, String tel, String address) {
        this.personID = personID;
        this.name = name;
        this.tel = tel;
        this.address = address;
    }
    public Person(String name, String tel, String address) {
        this.name = name;
        this.tel = tel;
        this.address = address;
    }
    public Person() {
    }

    public Person(int personID, int status) {
        this.personID = personID;
        this.status = status;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
