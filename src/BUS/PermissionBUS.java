/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;


import DAO.PermissionDAO;
import DTO.entities.Permission;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;


/**
 *
 * @author WIN 10
 */
public class PermissionBUS {
    protected static ArrayList<Permission> list;
    protected static PermissionDAO permissionDAO;
    private static int quantity = 0;
    

    public PermissionBUS() throws ClassNotFoundException, SQLException, IOException {
        permissionDAO = new PermissionDAO();
        list = new ArrayList<>(permissionDAO.getList());
        quantity = list.size();
    }

    public static ArrayList<Permission> getList() {
        return list;
    }
    
    public static int getQuantity() {
        quantity = list.size();
        return quantity;
    }
    
    
}
