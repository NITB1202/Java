/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

/**
 *
 * @author WIN 10
 */
public class Warehouse {
    private String isbn;
    private int bookID;
    private int borrowNum;
    private int storeNum;
    private String edition;
    private int publisherID;
    private boolean status;
    private long cost;
    private String img;
	
	public Warehouse(String isbn, int bookID, int borrowNum, int storeNum, String edition, int publisherID,
			boolean status, long cost, String img) {
		super();
		this.isbn = isbn;
		this.bookID = bookID;
		this.borrowNum = borrowNum;
		this.storeNum = storeNum;
		this.edition = edition;
		this.publisherID = publisherID;
		this.status = status;
		this.cost = cost;
		this.img = img;
	}
	
	public Warehouse() {
		super();
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public int getBorrowNum() {
		return borrowNum;
	}

	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}

	public int getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}
	
	public String getImg()
	{
		return img;
	}
	
	public void setImg(String img)
	{
		this.img = img;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.isbn + " " + this.bookID + " " + this.borrowNum + " " + this.storeNum + " " + this.edition + " " + this.publisherID + " " + this.status + " " + this.cost + " " + this.img;
	}
}
