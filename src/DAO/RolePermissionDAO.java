/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.RolePermission;
import DTO.entities.Account;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WIN 10
 */
public class RolePermissionDAO {
    protected ArrayList<RolePermission> list = new ArrayList<>();
    protected RolePermission rolePermission = new RolePermission();
    private ConnectDB connectDB;

    public RolePermissionDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
        read();
    }

    public ArrayList<RolePermission> getList() {
        return list;
    }
    
    public ArrayList<RolePermission> read() throws IOException, ClassNotFoundException, SQLException{
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
            String sql = "Select *  from role_permissions";
            Statement stmt = connectDB.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleID(rs.getString(1));                
                rolePermission.setPermissionID(rs.getInt(2));
                rolePermission.setPerAccess(rs.getInt(3));                
                rolePermission.setPerAccess(rs.getInt(3));
                rolePermission.setPerCreate(rs.getInt(4));                
                rolePermission.setPerView(rs.getInt(5));
                rolePermission.setPerEdit(rs.getInt(6));
                rolePermission.setPerDelete(rs.getInt(7));                
                rolePermission.setIsDelete(rs.getInt(8));
                list.add(rolePermission);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectDB.disconnect(context);
        return list;
    }
    public boolean create(RolePermission per) throws ClassNotFoundException, SQLException {
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
            String sql = "INSERT INTO role_permissions(positionID, authorityID, Per_access, Per_create, Per_view, Per_edit, Per_delete, IsActive) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = ConnectDB.conn.prepareStatement(sql);
            pstmt.setString(1, per.getRoleID());
            pstmt.setInt(2, per.getPermissionID());
            pstmt.setInt(3, per.getPerAccess());
            pstmt.setInt(4, per.getPerCreate());            
            pstmt.setInt(5, per.getPerView());            
            pstmt.setInt(6, per.getPerEdit());
            pstmt.setInt(7, per.getPerDelete());
            pstmt.setInt(8, per.getIsDelete());
            pstmt.executeUpdate();
            list.add(per);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RolePermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connectDB.disconnect(context);
        }
        return false;
    }    
    public boolean update(RolePermission per) throws SQLException {
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
            String sql = "UPDATE role_permissions SET Per_access = ?, Per_create = ?, Per_view =?, Per_edit =?, Per_delete =?  "
                    + "WHERE positionID = ? AND authorityID = ?";
            PreparedStatement pstmt = ConnectDB.conn.prepareStatement(sql);
            pstmt.setInt(1, per.getPerAccess());
            pstmt.setInt(2, per.getPerCreate());
            pstmt.setInt(3, per.getPerView());
            pstmt.setInt(4, per.getPerEdit());
            pstmt.setInt(5, per.getPerDelete());            
            pstmt.setString(6, per.getRoleID());            
            pstmt.setInt(7, per.getPermissionID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PermissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectDB.disconnect(context);
        return false;
    }
    
    public ArrayList<RolePermission> searchByID(String ID) { 
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRoleID().equals(ID)) {
                list.remove(i);
            }
        }
        return list;
    }
}
