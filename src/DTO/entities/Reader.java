/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class Reader extends Person{
    private LocalDate fineDate;
    public  Reader(){
    }

    public Reader(LocalDate fineDate, int personID, String name, String tel, String address, int status) {
        super(personID, name, tel, address, status);
        this.fineDate = fineDate;
        if(fineDate==null){
            status=0;
        }else{
            status=1;
        }
    }

    public Reader(LocalDate fineDate) {
        this.fineDate=fineDate;
        if(fineDate==null){
            status=0;
        }else{
            status=1;
        }
    }

    public Reader(LocalDate fineDate, int personID, int status) {
        super(personID, status);
        this.fineDate = fineDate;
    }

    public Reader(String name, String tel, String address) {
       super(name,tel,address);
    }

    public void setFineDate(LocalDate fineDate) {
        this.fineDate = fineDate;
    }
    public LocalDate getFineDate() {
        return fineDate;
    }
    public boolean compare(String name,String tel,String address) {
    	if(getName().toUpperCase().equals(name.toUpperCase()) && getTel().toUpperCase().equals(tel.toUpperCase()) && getAddress().toUpperCase().equals(address.toUpperCase())) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
