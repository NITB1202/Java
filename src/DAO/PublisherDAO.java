/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.Publisher;
import connection.ConnectDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author WIN 10
 */
public class PublisherDAO {
    private ConnectDB connectDB;
    public PublisherDAO(ConnectDB connectDB) throws SQLException, IOException{
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
    public void savePublisher(Publisher p) {
        String query = "INSERT INTO Publisher (name) VALUES (?)";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, p.getName());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getByNamePub(String name) {
        String status = null;
        String query = "SELECT name FROM publisher WHERE name COLLATE Latin1_General_CI_AI = ?";

        try {
            connectDB.connect();
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
public List<Publisher> getAllName() throws SQLException {
    List<Publisher> list = new ArrayList<>();
    String query = "SELECT * FROM publisher";
    connectDB.connect();    
    try (Connection connection = connectDB.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
            Publisher p = new Publisher();
            p.setId(resultSet.getInt(1));
            p.setName(resultSet.getString(2));
            p.setStatus(resultSet.getInt(3));
            list.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}

public List<String> getPublisherForBook(String bookName) throws SQLException {
    List<String> publishers = new ArrayList<>();
    String query = "SELECT p.name AS publisher_name " +
            "FROM book b " +
            "JOIN cp_book cb ON b.id = cb.bookID " +
            "JOIN publisher p ON cb.publisherID = p.id " +
            "WHERE b.name = ?";
    connectDB.connect();
    try (Connection conn = connectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, bookName);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            publishers.add(rs.getString("publisher_name"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return publishers;
}

public String getByIDPubName(String isbn) throws SQLException{
    String query = "SELECT publisher.name FROM cp_book INNER JOIN publisher ON cp_book.publisherID = publisher.id WHERE cp_book.ISBN COLLATE Latin1_General_BIN = ? COLLATE Latin1_General_BIN";
    String s = null;
    connectDB.connect();
    try {
        Connection connection = connectDB.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, isbn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    s = resultSet.getString("name");
                }
            }
        } 
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    return s;
}
    public String getByName(String name) throws SQLException{
        String query = "SELECT name FROM publisher WHERE name = ?";
        String s = null;
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        s = resultSet.getString("name");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return s;
    }
    public  void deleteByName (String name){
        String query = "UPDATE publisher SET isActive = 0 WHERE name = ?";
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
    public boolean updatePublisherName(String oldName, String newName) {
		String query = "UPDATE publisher SET name = ? WHERE name = ?";
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
