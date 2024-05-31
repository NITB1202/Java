/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author User
 */
public class BorrowCard {
    private int ID;
    private String Readername;
    private String Staffname;
    private Date StartDate;
    private Date ExpReDate;
    private Date RealReDate;
    private boolean status;
    private long deposit;
    private Vector<DetailBC> listBook;
    private int num;
    public BorrowCard(){
        
    }
    
    public BorrowCard(int ID, int ReaderID, int StaffID, Date Startdate, Date ExpReDate, Date RealReDate, boolean status, long deposit, Vector<DetailBC> listBook,int num){
        this.ID=ID;
        this.Readername=Readername;
        this.Staffname=Staffname;
        this.StartDate=Startdate;
        this.ExpReDate=ExpReDate;
        this.RealReDate=RealReDate;
        this.status=status;
        this.deposit=deposit;
        this.listBook = listBook;
        this.num =num;
    }
    public int getID() {
        return ID;
    }

    public String getReadername() {
        return Readername;
    }

    public String getStaffname() {
        return Staffname;
    }
    
    public Date getStartDate(){
        return StartDate;
    }
    
    public Date getExpReDate(){
        return ExpReDate;
    }
    
    public Date getRealReDate() {
        return RealReDate;
    }
    
    public boolean getstatus(){
        return status;
    }
    
    public long getdeposit(){
        return deposit;
    }

    public void setReadername(String Readername) {
        this.Readername = Readername;
    }

    public void setStaffname(String Staffname) {
        this.Staffname = Staffname;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setStartDate(Date StartDate){
        this.StartDate=StartDate;
    }
    
    public void setExpReDate(Date ExpReDate){
        this.ExpReDate=ExpReDate;
    }
    
    public void setRealReDate(Date RealReDate) {
        this.RealReDate = RealReDate;
    }
    
    public void setstatus(boolean status){
        this.status=status;
    }
    
    public void setdeposit(long deposit){
        this.deposit=deposit;
    }

    public Vector<DetailBC> getListBook() {
        return listBook;
    }

    public void setListBook(Vector<DetailBC> listBook) {
        this.listBook = listBook;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
}
