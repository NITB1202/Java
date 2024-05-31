/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

/**
 *
 * @author WIN 10
 */
public class Book {
    private int id = 0;
	private String name = "";
	private boolean status;
	public Book(int id, String name, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}
	public Book()
	{
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "sach_DTO [id=" + id + ", name=" + name + ", status=" + status + "]";
	}
}
