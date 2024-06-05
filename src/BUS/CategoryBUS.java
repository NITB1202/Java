/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.AuthorDAO;
import DAO.BookCategoryDAO;
import DAO.CategoryDAO;
import DTO.entities.BookCategory;
import DTO.entities.Category;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WIN 10
 */
public class CategoryBUS {
    private  CategoryDAO cdao;
    private  BookCategoryDAO bcadao;
    private ConnectDB connectDB;
    public CategoryBUS() throws SQLException, IOException
    {
        ConnectDB connectDB = null;
        try {
            connectDB = new ConnectDB();
            cdao = new CategoryDAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Category> getAll() throws SQLException{
        return cdao.getAll();
    }
    public String getByName(String isbn) throws SQLException, IOException
    {
    	cdao = new CategoryDAO(connectDB);
    	return cdao.getByName(isbn);
    }
    public void saveInfo(Category c) throws SQLException, IOException, ClassNotFoundException
    {
            cdao = new CategoryDAO(connectDB);
            cdao.saveProvider(c);

    }
    public String getByNameCategory(String name) throws SQLException, IOException, ClassNotFoundException
    {
            cdao = new CategoryDAO(connectDB);
            return cdao.getByNameCategory(name);
    }
    public void saveBookCategory(BookCategory bookcategory) throws SQLException, IOException
    {
    		bcadao = new BookCategoryDAO(connectDB);
    		bcadao.saveBookCategory(bookcategory);
    }
    public int getByCategoryID(String name) throws SQLException, IOException
    {
	    	cdao = new CategoryDAO(connectDB);
	    	return cdao.getByCategoryID(name);
    }
    public boolean deleteByCategoryName (String name) throws SQLException, IOException, ClassNotFoundException {
        cdao = new CategoryDAO(connectDB);
        cdao.deleteByName(name);
        return true;
    }
    
    public Vector<String> getCategoryByISBN(String ISBN){
        return cdao.getCategoryByISBN(ISBN);
    }
    
    public void disconnect() {
            cdao.disconnect();
    }
	public boolean updateCategoryName(String oldCategoryName, String newCategoryName) {
		return cdao.updateCategoryName(oldCategoryName, newCategoryName);
	}
}