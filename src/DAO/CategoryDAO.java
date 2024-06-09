/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.Category;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author WIN 10
 */
public class CategoryDAO {
    private ConnectDB connectDB;
	public CategoryDAO() throws SQLException, IOException, ClassNotFoundException{
        connectDB = new ConnectDB();
	}
    public CategoryDAO(ConnectDB connectDB) throws SQLException, IOException{
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
	public String getByNameCategory(String name) throws ClassNotFoundException, SQLException, IOException {
	    String status = null;
	    String query = "SELECT name FROM category WHERE name COLLATE Latin1_General_CI_AI = ?";
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
        public String getByName(String isbn) throws SQLException {
        String status = null;
        String query = "SELECT category.name FROM category INNER JOIN book_category ON category.id = book_category.categoryID WHERE book_category.ISBN COLLATE Latin1_General_BIN = ? COLLATE Latin1_General_BIN";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, isbn);
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
    public void saveProvider(Category c) {
        String query = "INSERT INTO category (name) VALUES (?)";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if(connection!=null)
            {
            	try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, c.getName());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Category> getAll() throws SQLException {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM category";
        connectDB.connect();
        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
            	Category c = new Category();
                c.setId(resultSet.getInt(1));
                c.setName(resultSet.getString(2));
                c.setStatus(resultSet.getInt(3));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void deleteByName (String name){
        String query = "UPDATE category SET isActive = 0 WHERE name = ?";
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
    
    public Vector<String> getCategoryByISBN(String ISBN) {
        Vector<String> cateList=new Vector<>();
        String query = "select name from book_category join category on book_category.categoryID=category.id where ISBN=?";
        try {
        	connectDB.connect();
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, ISBN);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        cateList.add(resultSet.getString("name"));
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            disconnect();
        }
        return cateList;
    }
    
    public int getByCategoryID(String name) {
        int id = 0;
        String query = "SELECT id FROM category WHERE name LIKE ?";

        try {
        	connectDB.connect();
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return id;
    }
    
    public boolean updateCategoryName(String oldCategoryName, String newCategoryName) {
		String query = "UPDATE category SET name = ? WHERE name = ?";
        try {
        	connectDB.connect();
        	if(ConnectDB.conn != null){
        		PreparedStatement stmt = ConnectDB.conn.prepareStatement(query);
        		stmt.setString(1, newCategoryName);
                stmt.setString(2, oldCategoryName);
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
