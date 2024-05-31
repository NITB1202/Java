/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

/**
 *
 * @author WIN 10
 */
public class SupplyCardDetail {
    private int scID;
    private String ISBN;
    private int num;
    public SupplyCardDetail(int scID, String iSBN, int num) {
            super();
            this.scID = scID;
            ISBN = iSBN;
            this.num = num;
    }
    public SupplyCardDetail()
    {
            super();
    }
    public int getScID() {
            return scID;
    }
    public void setScID(int scID) {
            this.scID = scID;
    }
    public String getISBN() {
            return ISBN;
    }
    public void setISBN(String iSBN) {
            ISBN = iSBN;
    }
    public int getNum() {
            return num;
    }
    public void setNum(int num) {
            this.num = num;
    }
    @Override
    public String toString() {
            return "detailSC [scID=" + scID + ", ISBN=" + ISBN + ", num=" + num + "]";
    }
	
}
