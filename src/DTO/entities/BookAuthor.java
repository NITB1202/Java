/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

/**
 *
 * @author WIN 10
 */
public class BookAuthor {
    private int authorID;
	private String ISBN;
	public BookAuthor(int authorID, String iSBN) {
		super();
		this.authorID = authorID;
		ISBN = iSBN;
	}
	public BookAuthor()
	{
		super();
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	@Override
	public String toString() {
		return "bookAuthor [authorID=" + authorID + ", ISBN=" + ISBN + "]";
	}

}
