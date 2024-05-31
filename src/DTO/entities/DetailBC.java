/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

import java.util.Vector;

/**
 *
 * @author User
 */
public class DetailBC {
    private int ID;
    private String ISBN;
    private String description;
    private String Bookname;
    private Vector<String> Authorname;
    private int num;
    private int lost;
    private float bookCost;
    public DetailBC(){
        
    }

    public DetailBC(int ID, String ISBN, String description, String Bookname, Vector<String> Authorname, int num, int lost, float bookCost) {
        this.ID = ID;
        this.ISBN = ISBN;
        this.description = description;
        this.Bookname = Bookname;
        this.Authorname = Authorname;
        this.num = num;
        this.lost = lost;
        this.bookCost = bookCost;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookname() {
        return Bookname;
    }

    public void setBookname(String Bookname) {
        this.Bookname = Bookname;
    }

    public Vector<String> getAuthorname() {
        return Authorname;
    }

    public void setAuthorname(Vector<String> Authorname) {
        this.Authorname = Authorname;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public float getBookCost() {
        return bookCost;
    }

    public void setBookCost(float bookCost) {
        this.bookCost = bookCost;
    }
}
