/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

/**
 *
 * @author Tính
 */
public class TopBorrowedBook {
    private String nameBook;
    private String bookAuthor;
    private String NXB;
    private int soLuotMuon;

    public TopBorrowedBook() {
        super();
    }

    public TopBorrowedBook(String nameBook, String bookAuthor, String NXB, int soLuotMuon) {
        this.nameBook = nameBook;
        this.bookAuthor = bookAuthor;
        this.NXB = NXB;
        this.soLuotMuon = soLuotMuon;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public int getSoLuotMuon() {
        return soLuotMuon;
    }

    public void setSoLuotMuon(int soLuotMuon) {
        this.soLuotMuon = soLuotMuon;
    }

    
    
 
}
