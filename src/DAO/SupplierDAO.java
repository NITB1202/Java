/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.Supplier;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author WIN 10
 */
public class SupplierDAO {
    private ConnectDB connectDB;
    public SupplierDAO() throws SQLException, IOException{
        try {
                connectDB = new ConnectDB();
        } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
    public SupplierDAO(ConnectDB connectDB) throws SQLException, IOException{
            try {
                    this.connectDB = new ConnectDB();
            } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }

    public String getBySupplierName(String name) throws SQLException{
        String status = null;
        String query = "SELECT name FROM supplier WHERE name COLLATE Latin1_General_CI_AI = ?";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        status = resultSet.getString("name");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return status;
    }
	
    public List<Supplier> getAllName() throws SQLException {
        List<Supplier> list = new ArrayList<>();
        String query = "SELECT * FROM supplier";
        connectDB.connect();
        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            	Supplier a = new Supplier();
                a.setSupplier_id(resultSet.getInt(1));
                a.setSupplier_name(resultSet.getString(2));
                a.setSupplier_status(resultSet.getInt(3));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void saveSupplier(Supplier a) {
        String query = "INSERT INTO supplier (name) VALUES (?)";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if(connection!=null)
            {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, a.getSupplier_name());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void deleteByName (String name){
        String query = "UPDATE supplier SET isActive = 0 WHERE name = ?";
        try{
            connectDB.connect();
            if(connectDB.conn != null){
                PreparedStatement pstmt = connectDB.conn.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void update(Supplier supplier){
        String query = "UPDATE supplier SET name = ? WHERE id = ?";
        try{
            connectDB.connect();
            if(connectDB.conn != null){
                PreparedStatement pstmt = connectDB.conn.prepareStatement(query);
                pstmt.setString(1, supplier.getSupplier_name());                
                pstmt.setInt(2, supplier.getSupplier_id());
                pstmt.executeUpdate();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getBySupplierID(String sub) throws SQLException {
        int supID = 0;
        String query = "SELECT id FROM supplier WHERE name LIKE ?";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, sub);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        supID = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectDB.disconnect();
        return supID;
    }
    
    public boolean updateSupplierName(String oldName, String newName) throws SQLException {
        String query = "UPDATE supplier SET name = ? WHERE name = ?";
        try {
        	connectDB.connect();
        	if(ConnectDB.conn != null){
        		PreparedStatement stmt = ConnectDB.conn.prepareStatement(query);
        		stmt.setString(1, newName);
                stmt.setString(2, oldName);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
        }
    
    public void disconnect() {
            try {
                 connectDB.disconnect();
         } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
         }
    }
}