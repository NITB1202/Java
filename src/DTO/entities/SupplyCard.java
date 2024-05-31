/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

import java.sql.Timestamp;

/**
 *
 * @author WIN 10
 */
public class SupplyCard {
        private int id;
	private Timestamp supDate;
	private int provider;
	private int staffID;
	private long tongchi;
		
	public SupplyCard(int id, Timestamp supDate, int provider, int staffID, long tongchi) {
		super();
		this.id = id;
		this.supDate = supDate;
		this.provider = provider;
		this.staffID = staffID;
		this.tongchi = tongchi;
	}

	public SupplyCard()
	{
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getSupDate() {
		return supDate;
	}

	public void setSupDate(Timestamp supDate) {
		this.supDate = supDate;
	}

	public int getProvider() {
		return provider;
	}

	public void setProvider(int provider) {
		this.provider = provider;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	
	public long getTongchi()
	{
		return tongchi;
	}
	
	public void setTongchi(long tongchi)
	{
		this.tongchi = tongchi;
	}
	
	@Override
	public String toString() {
		return "supply_Card [id=" + id + ", supDate=" + supDate + ", provider=" + provider + ", staffID=" + staffID
				+ "]";
	}	
}