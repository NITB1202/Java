/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;


import DAO.StaffDAO;
import DAO.AccountDAO;
import DTO.entities.Staff;
import DTO.entities.Account;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author WIN 10
 */
public class StaffBUS {
    protected static ArrayList<Staff> list;
    protected static StaffDAO staffDAO;
    protected static AccountDAO userDAO;
    private static int quantity = 0;
    public StaffBUS() throws ClassNotFoundException, SQLException, IOException{
    	staffDAO = new StaffDAO();
        userDAO = new AccountDAO();
    }
    
    public Vector<Account> getAllAD() throws Exception{
        return userDAO.getAllAD();
    }
    
    public Vector<Account> getAllQL() throws Exception{
        return userDAO.getAllQL();
    }
    
    public Vector<String> getRoleAD() throws Exception{
    	return staffDAO.getRoleAD();
    }
    
    public Vector<String> getRoleQL() throws Exception{
    	return staffDAO.getRoleQL();
    }
    
    public Vector<String> getRoleAll() throws Exception{
    	return staffDAO.getRoleAll();
    }
    
    public boolean checkTel(String tel) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
    	return staffDAO.checkTel(tel);
    }
    
    public boolean checkTelExcept(String tel,int id) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
    	return staffDAO.checkTelExcept(tel,id);
    }
    
    public String addStaff(Staff a, Account u) throws Exception{
        if(staffDAO.addStaff(a,u)){
            return "Thêm nhân viên thành công";
        }else{
            return "Lỗi! Thêm nhân viên không thành công";
        }
    }
    
    public String updateOneStaff(Staff a, Account ac) throws Exception{
        if(staffDAO.updateOneStaff(a,ac)){
            return "Sửa nhân viên thành công";
        }else{
            return "Lỗi! Sửa nhân viên không thành công";
        }
    }
    
    public String eraShowStaff(Staff a) throws Exception {
    	if(staffDAO.eraShowStaff(a)){
            return "Xoá nhân viên thành công";
        }else{
            return "Lỗi! Xoá nhân viên không thành công";
        }
    }
    public Vector<Account> allOutSearchAD(String str) throws Exception{
        return userDAO.allOutSearchAD(str);
    }
    
    public Vector<Account> allOutSearchQL(String str) throws Exception{
        return userDAO.allOutSearchQL(str);
    }
    
    public Account findbyID(int id) throws Exception {
    	return userDAO.findbyID(id);
    }
    public Staff findStaffbyID(int id) throws Exception {
    	return staffDAO.findbyID(id);
    }
    public String getRole(String id) throws SQLException{       
        return userDAO.getRole(id);
    }
    public String getRoleID(String name) throws SQLException{       
        return userDAO.getRoleID(name);
    }
}
