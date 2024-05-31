/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import connection.ConnectDB;
import java.util.ArrayList;

import DTO.entities.Warehouse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author WIN 10
 */
public class WarehouseDAO {
    ArrayList<Warehouse> list = new ArrayList<>();
    private ConnectDB connectDB;
    public WarehouseDAO(ConnectDB connectDB) throws SQLException, IOException{
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
    public Warehouse getISBN(String isbn) throws SQLException
    {
        Warehouse kho = null;
        String query = "SELECT * FROM cp_book WHERE ISBN LIKE ?";
        try {
            connectDB.connect();
        Connection connection = connectDB.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,isbn + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    kho = new Warehouse();
                    kho.setIsbn(resultSet.getString("ISBN"));
                    kho.setBookID(resultSet.getInt("bookID"));
                    kho.setBorrowNum(resultSet.getInt("borrowNum"));
                    kho.setStoreNum(resultSet.getInt("storeNum"));
                    kho.setEdition(resultSet.getString("edition"));
                    kho.setPublisherID(resultSet.getInt("publisherID"));
                    kho.setStatus(resultSet.getBoolean("isActive"));
                    kho.setCost(resultSet.getLong("Cost"));
                    kho.setImg(resultSet.getString("img"));
                    list.add(kho);
                } 
            }
        }
        connectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        connectDB.disconnect();
        return kho;
    }

    public String getByImg(String name)throws SQLException {
        String img = null;
        String query = "SELECT cp_book.img FROM book INNER JOIN cp_book ON cp_book.bookID = book.id WHERE book.name = ?";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        img = resultSet.getString("img");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
//        connectDB.disconnect();
        return img;
    }
    
    public String getByEdition(String name)throws SQLException {
        String edition = null;
        String query = "SELECT cp_book.edition FROM book INNER JOIN cp_book ON cp_book.bookID = book.id AND book.name = ?";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        edition = resultSet.getString("edition");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
//        connectDB.disconnect();
        return edition;
    }
    
    public long getByCost(String name)throws SQLException {
        long cost = 0;
        String query = "SELECT cp_book.Cost FROM book INNER JOIN cp_book ON cp_book.bookID = book.id AND book.name = ?";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        cost = resultSet.getLong("Cost");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
//        connectDB.disconnect();
        return cost;
    }
    
    public long getByCostFromISBN(String name)throws SQLException {
        long cost = 0;
        String query = "SELECT cp_book.Cost FROM book INNER JOIN cp_book ON cp_book.bookID = book.id WHERE cp_book.isbn COLLATE Latin1_General_BIN = ? COLLATE Latin1_General_BIN";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        cost = resultSet.getLong("Cost");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
//        connectDB.disconnect();
        return cost;
    }
    
    public String getByImgFromISBN(String name)throws SQLException {
        String img = null;
        String query = "SELECT cp_book.img FROM book INNER JOIN cp_book ON cp_book.bookID = book.id WHERE cp_book.isbn COLLATE Latin1_General_BIN = ? COLLATE Latin1_General_BIN";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        img = resultSet.getString("img");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
//        connectDB.disconnect();
        return img;
    }
    
    public String getByEditionFromISBN(String name)throws SQLException {
        String edition = null;
        String query = "SELECT cp_book.edition FROM book INNER JOIN cp_book ON cp_book.bookID = book.id WHERE cp_book.isbn COLLATE Latin1_General_BIN = ? COLLATE Latin1_General_BIN";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        edition = resultSet.getString("edition");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
//        connectDB.disconnect();
        return edition;
    }
    
    public String getNameBook(String isbn)throws SQLException{
    	String name = null;
    	String query = "SELECT book.name FROM book INNER JOIN cp_book ON cp_book.bookID = book.id WHERE cp_book.isbn COLLATE Latin1_General_BIN = ? COLLATE Latin1_General_BIN";
    	connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, isbn);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        name = resultSet.getString("name");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        connectDB.disconnect();
        return name;
    }
    public String getByISBN(String name) throws SQLException{
        String isbn = null;
        String query = "SELECT cp_book.ISBN FROM book INNER JOIN cp_book ON cp_book.bookID = book.id AND book.name COLLATE Latin1_General_CI_AI = ?";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        isbn = resultSet.getString("ISBN");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        connectDB.disconnect();
        return isbn;
    }

    public int getByNameBook(String name) throws SQLException{
        int bookid = 0;
        String query = "SELECT cp_book.bookID FROM book INNER JOIN cp_book ON cp_book.bookID = book.id WHERE book.name COLLATE Latin1_General_CI_AI = ? COLLATE Latin1_General_CI_AI";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        bookid = resultSet.getInt("bookID");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        connectDB.disconnect();
        return bookid;
    }

    public int getByPubID(String pub) throws SQLException{
        int pubid = 0;
        String query = "SELECT cp_book.publisherID FROM publisher INNER JOIN cp_book ON cp_book.publisherID = publisher.id AND publisher.name LIKE ?";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, pub);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        pubid = resultSet.getInt("publisherID");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        connectDB.disconnect();
        return pubid;
    }

    public int getByPublisherID(String pub) throws SQLException {
        int pubid = 0;
        String query = "SELECT id FROM publisher WHERE name LIKE ?";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, pub);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        pubid = resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectDB.disconnect();
        return pubid;
    }


public void saveInfo(Warehouse kho) throws SQLException {
        String query = "INSERT INTO cp_book (ISBN, bookID, borrowNum, storeNum, edition, publisherID, Cost, img) VALUES (?,?,0,?,?,?,?,?)";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, kho.getIsbn());
                    preparedStatement.setInt(2, kho.getBookID());
                    preparedStatement.setInt(3, kho.getStoreNum());
                    preparedStatement.setString(4, kho.getEdition());
                    preparedStatement.setInt(5, kho.getPublisherID());
                    preparedStatement.setLong(6, kho.getCost());
                    preparedStatement.setString(7, kho.getImg());
                    preparedStatement.executeUpdate();
                }
            } else {
                System.err.println("Không thể kết nối đến cơ sở dữ liệu.");
                // Xử lý lỗi kết nối
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi SQL
        }
        connectDB.disconnect();
    }

    public int getByIDBook() throws SQLException {
        int id = 0;
        String query = "SELECT TOP 1 id FROM book " +
                      "ORDER BY id DESC";
        connectDB.connect();
        try {
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        connectDB.disconnect();
        return id;
    }

    public void saveOldBook(Warehouse kho)  throws SQLException{
        String query = "UPDATE cp_book SET storeNum = storeNum + ?, edition = ?, publisherID = ?, Cost = ? WHERE ISBN = ?";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if(connection!=null)
            {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, kho.getStoreNum());
                    preparedStatement.setString(2, kho.getEdition());
                    preparedStatement.setInt(3, kho.getPublisherID());
                    preparedStatement.setLong(4, kho.getCost());
                    preparedStatement.setString(5, kho.getIsbn());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectDB.disconnect();
    }
}
