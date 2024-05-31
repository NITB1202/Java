/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import DTO.entities.Role;
import connection.ConnectDB;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author WIN 10
 */
public class RoleDAO {
    public static ArrayList<Role> list;
    protected Role role;
    private ConnectDB connectDB;
    
    public RoleDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
        read();
    }
    
    public ArrayList<Role> getList() {
        return list;
    }
    public Role getRole(String roleName) {
        for (Role role : list) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        return null;
    }

    public ArrayList<Role> read() throws IOException, ClassNotFoundException, SQLException{
        String context = this.getClass().getName();
        connectDB.connect(context);
        list = new ArrayList<>() ;
        try {
            String sql = "Select * from roles";
            Statement stmt = ConnectDB.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Role role = new Role();
                role.setRoleID(rs.getString(1));
                role.setRoleName(rs.getString(2));
                System.out.println(role.getRoleName());
                role.setIsDeleted(rs.getByte(3));
                list.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectDB.disconnect(context);
        return list;
    }
    
    public boolean create(Role role) throws SQLException {
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
            String sql = "INSERT INTO roles(id, name, isActive) "
                    + "VALUES (?, ?,1)";
            PreparedStatement pstmt = ConnectDB.conn.prepareStatement(sql);
            pstmt.setString(1, role.getRoleID());
            pstmt.setString(2, role.getRoleName());            
            pstmt.executeUpdate();
            list.add(role);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectDB.disconnect(context);
        return false;
    }
    public boolean delete(Role role) throws SQLException {
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
           String sql = "UPDATE roles SET isActive=0 WHERE id = ?;";
            PreparedStatement pstmt = connectDB.conn.prepareStatement(sql);
            pstmt.setString(1, role.getRoleID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RolePermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean ckNewRole(String name) throws Exception {
    	boolean flag=true;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="select name from roles";
            	Statement stmt = ConnectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	if(name.toUpperCase().equals(rs.getString(1).toUpperCase())) {
                		flag=false;
                	}
                }                
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
    
    public boolean ckNewRoleID(String id) throws Exception {
    	boolean flag=true;
        ConnectDB db=new ConnectDB();
        if(ConnectDB.conn!=null){
            try{
            	String sql="select id from roles";
            	Statement stmt = ConnectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	if(id.toUpperCase().equals(rs.getString(1).toUpperCase())) {
                		flag=false;
                	}
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                db.disconnect();
            }
        }
        return flag;
    }
}
