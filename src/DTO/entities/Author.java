/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

/**
 *
 * @author WIN 10
 */
public class Author {
    private int id;
	private String name;
	private int status;
	public Author(int id, String name, int status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}
	public Author()
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
	public int isStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", status=" + status + "]";
	}
	
}
