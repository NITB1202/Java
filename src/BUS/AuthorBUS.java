package BUS;

import DAO.AuthorDAO;
import DAO.BookAuthorDAO;
import DTO.entities.Author;
import DTO.entities.BookAuthor;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author WIN 10
 */
public class AuthorBUS {
    private  AuthorDAO ad;
    private BookAuthorDAO bad;
    private ConnectDB connectDB;
    public AuthorBUS() throws SQLException, IOException
    {
        ConnectDB connectDB = null;
        try {
            connectDB = new ConnectDB();
            ad = new AuthorDAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public String getByNameAuthor(String name) throws SQLException, IOException
    {
            ad = new AuthorDAO(connectDB);
            return ad.getByNameAuthor(name);
    }
    public List<Author> getAllName() throws SQLException{
        return ad.getAllName();
    }
    public String getByName(String isbn) throws SQLException, IOException
    {
    		ad = new AuthorDAO(connectDB);
    		return ad.getByName(isbn);
    }
    public void saveInfo(Author a) throws SQLException, IOException
    {
            ad = new AuthorDAO(connectDB);
            ad.saveAuthor(a);

    }
    public boolean deteleByAuthorName(String name) throws  SQLException, IOException{
        ad = new AuthorDAO(connectDB);
        ad.deleteByName(name);
        return true;
    }
    public int getByAuthorID(String name) throws SQLException, IOException
    {
    	ad = new AuthorDAO(connectDB);
    	return ad.getByAuthorID(name);
    }
    public void saveBookAuthor(BookAuthor ba) throws SQLException, IOException
    {
    	bad = new BookAuthorDAO(connectDB);
    	bad.saveBookAuthor(ba);
    }
    public void disconnect() {
        ad.disconnect();
    }
}