package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}