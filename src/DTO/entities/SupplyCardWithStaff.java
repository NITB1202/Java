/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

/**
 *
 * @author WIN 10
 */
public class SupplyCardWithStaff {
        private SupplyCard sc;
	private Staff s;
	public SupplyCardWithStaff(SupplyCard sc, Staff s) {
		super();
		this.sc = sc;
		this.s = s;
	}
	
	public SupplyCardWithStaff()
	{
		super();
	}
	
	public SupplyCard getSupply_Card() {
		return sc;
	}
	
	public Staff getStaff()
	{
		return s;
	}
}
