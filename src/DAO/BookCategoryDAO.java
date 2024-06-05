package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.entities.BookAuthor;
import DTO.entities.BookCategory;
import connection.ConnectDB;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author WIN 10
 */
public class BookCategoryDAO {
	private ConnectDB connectDB;
    public BookCategoryDAO(ConnectDB connectDB) throws SQLException, IOException{
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
    public void saveBookCategory(BookCategory bookcategory) {
        String query = "INSERT INTO book_category (categoryID, ISBN) VALUES (?, ?)";
        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, bookcategory.getCategoryID());
                    preparedStatement.setString(2, bookcategory.getISBN());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
    
    public List<String> getCategoriesForBook(String bookName) throws SQLException {
        List<String> categories = new ArrayList<>();
        String query = "SELECT c.name AS category_name " +
                "FROM book_category bc " +
                "JOIN category c ON bc.categoryID = c.id " +
                "JOIN book_author ba ON ba.ISBN = bc.ISBN " +
                "JOIN book b ON ba.authorID = b.id " +
                "WHERE b.name = ?";
        connectDB.connect();
        try (Connection conn = connectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bookName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categories.add(rs.getString("category_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    public List<String> getEditionsForBook(String bookName) throws SQLException {
        List<String> editions = new ArrayList<>();
        String query = "SELECT cb.edition AS edition_name " +
                       "FROM book b " +
                       "JOIN cp_book cb ON cb.bookID = b.id " +
                       "WHERE b.name = ?";
        connectDB.connect();
        try (Connection conn = connectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bookName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                editions.add(rs.getString("edition_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return editions;
    }
}