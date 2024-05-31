/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

/**
 *
 * @author WIN 10
 */
public class Supplier {
    private int supplier_id;
    private String supplier_name;
    private int supplier_status;

    public Supplier(int supplier_id, String supplier_name, int supplier_status) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.supplier_status = supplier_status;
    }

    public Supplier() {
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public int getSupplier_status() {
        return supplier_status;
    }

    public void setSupplier_status(int supplier_status) {
        this.supplier_status = supplier_status;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplier_id=" + supplier_id + ", supplier_name=" + supplier_name + ", supplier_status=" + supplier_status + '}';
    }
   
}
