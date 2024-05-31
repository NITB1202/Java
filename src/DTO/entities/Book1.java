/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

import java.util.Objects;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class Book1 {
    private String ISBN;
    private String tenSach;
    private int storeNum;
    private int borrowNum;
    private String publisher;
    private String version;
    private Vector<String> author=new Vector();
    private long Cost;
    private String img;

    public Book1(String ISBN, String tenSach, int storeNum,int borrowNum, String publisher, long Cost, String img) {
        this.ISBN = ISBN;
        this.tenSach = tenSach;
        this.storeNum = storeNum;
        this.borrowNum=borrowNum;
        this.publisher = publisher;
        this.Cost = Cost;
        this.img = img;
    }
    
    public Book1(String ISBN, String tenSach,String version, int storeNum,int borrowNum, String publisher, long Cost, String img) {
        this.ISBN = ISBN;
        this.tenSach = tenSach;
        this.version = version;
        this.storeNum = storeNum;
        this.borrowNum=borrowNum;
        this.publisher = publisher;
        this.Cost = Cost;
        this.img = img;
    }
    
     public int getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(int borrowNum) {
        this.borrowNum = borrowNum;
    }

    public Vector<String> getAuthor() {
        return author;
    }
    public String stringAuthor()
    {
        String str="";
        int i=0;
        for (String author : author) {
             if (i==1)
            {
                str=str+",";
            }
             str=str+author;
            i=i+1;
        }
        return str;
    }
    public void setAuthor(String authorName)
    {
        author.add(authorName);
    }
    public Book1() {
        
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(int storeNum) {
        this.storeNum = storeNum;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getCost() {
        return Cost;
    }

    public void setCost(long Cost) {
        this.Cost = Cost;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    
}