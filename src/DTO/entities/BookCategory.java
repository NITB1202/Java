/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

/**
 *
 * @author WIN 10
 */
public class BookCategory {
    private int categoryID;
    private String ISBN;
    public BookCategory(int categoryID, String iSBN) {
            super();
            this.categoryID = categoryID;
            ISBN = iSBN;
    }
    public BookCategory()
    {
            super();
    }
    public int getCategoryID() {
            return categoryID;
    }
    public void setCategoryID(int categoryID) {
            this.categoryID = categoryID;
    }
    public String getISBN() {
            return ISBN;
    }
    public void setISBN(String iSBN) {
            ISBN = iSBN;
    }
    @Override
    public String toString() {
            return "bookCategory [categoryID=" + categoryID + ", ISBN=" + ISBN + "]";
    }
}
