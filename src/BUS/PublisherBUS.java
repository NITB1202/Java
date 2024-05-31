/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PublisherDAO;
import DTO.entities.Publisher;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author WIN 10
 */
public class PublisherBUS {
    private  PublisherDAO pdao;
    private ConnectDB connectDB;
    public PublisherBUS() throws SQLException, IOException
    {
            ConnectDB connectDB = null;
            try {
        connectDB = new ConnectDB();
        pdao = new PublisherDAO(connectDB);
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    }
    public List<Publisher> getAllName() throws SQLException{
    return pdao.getAllName();
}
    public void saveInfo(Publisher p) throws SQLException, IOException
    {
            pdao = new PublisherDAO(connectDB);
            pdao.savePublisher(p);
    }
    public String getByName(String name) throws SQLException, IOException
    {
            pdao = new PublisherDAO(connectDB);
            return pdao.getByName(name);
    }
    public String getByNamePub(String name) throws SQLException, IOException
    {
            pdao = new PublisherDAO(connectDB);
            return pdao.getByNamePub(name);
    }
    public String getByIDPubName(String isbn) throws SQLException, IOException
    {
    		pdao = new PublisherDAO(connectDB);
    		return pdao.getByIDPubName(isbn);
    }
    public boolean deleteByPublisherName (String name) throws SQLException, IOException{
        pdao = new PublisherDAO(connectDB);
        pdao.deleteByName(name);
        return true;
    }
    public void disconnect() {
            pdao.disconnect();
    }
}
