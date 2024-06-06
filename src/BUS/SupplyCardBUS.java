/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BorrowCardDAO;
import DAO.SupplyCardDAO;
import DAO.WarehouseDAO;
import DTO.entities.BorrowCard;
import DTO.entities.SupplyCard;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author WIN 10
 */
public class SupplyCardBUS {
    private  SupplyCardDAO scd;
    private  WarehouseDAO kdao;
    private ConnectDB connectDB;
    public SupplyCardBUS() throws SQLException, IOException
    {
        this.connectDB = null;
        try {
            connectDB = new ConnectDB();
            this.scd = new SupplyCardDAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public String getByImg(String name) throws SQLException, IOException
    {
            kdao = new WarehouseDAO(connectDB);
            return kdao.getByImg(name);
    }
    public String getByEdition(String name) throws SQLException, IOException
    {
    		kdao = new WarehouseDAO(connectDB);
    		return kdao.getByEdition(name);
    }
    public String getByEditionFromISBN(String name) throws SQLException, IOException
    {
    		kdao = new WarehouseDAO(connectDB);
    		return kdao.getByEditionFromISBN(name);
    }
    
    public long getByCost(String name) throws SQLException, IOException
    {
    		kdao = new WarehouseDAO(connectDB);
    		return kdao.getByCost(name);
    }
    public long getByCostFromISBN(String name) throws SQLException, IOException
    {
    		kdao = new WarehouseDAO(connectDB);
    		return kdao.getByCostFromISBN(name);
    }
    public Vector<SupplyCard> getByCondition(String condition) throws ClassNotFoundException, SQLException, IOException{
    	scd = new SupplyCardDAO(connectDB);
        return scd.getByCondition(condition);
    }
    public Vector<SupplyCard> getAllTicket() throws ClassNotFoundException, SQLException, IOException{
        scd = new SupplyCardDAO(connectDB);
        return scd.getAll();
    }
    
    
    public String getByISBN(String name) throws SQLException, IOException
    {
            kdao = new WarehouseDAO(connectDB);
            return kdao.getByISBN(name);
    }
    public String getByImgFromISBN(String name) throws SQLException, IOException
    {
            kdao = new WarehouseDAO(connectDB);
            return kdao.getByImgFromISBN(name);
    } 
    public String getNameBook(String isbn) throws SQLException, IOException
    {
    		kdao = new WarehouseDAO(connectDB);
    		return kdao.getNameBook(isbn);
    }
    public int getByID() throws SQLException, IOException
    {
            scd = new SupplyCardDAO(connectDB);
            return scd.getByID();
    }

    public int getByNameBook(String name) throws SQLException, IOException
    {
            kdao = new WarehouseDAO(connectDB);
            return kdao.getByNameBook(name);
    }
    public int getByIDBook() throws SQLException, IOException
    {
            kdao = new WarehouseDAO(connectDB);
            return kdao.getByIDBook();
    }
    public int getByPublisherID(String pub) throws SQLException, IOException
    {
            kdao = new WarehouseDAO(connectDB);
            return kdao.getByPublisherID(pub);
    }
//    public String getByNameProvider(String name) throws SQLException, IOException
//    {
//            scd = new SupplyCardDAO(connectDB);
//            return scd.getByNameProvider(name);
//    }
    public List<SupplyCard> getAllSC(){
            return scd.getAllSC();
    }
    public SupplyCard getBySupDate(String supDate)
    {
            return scd.getBySupDate(supDate);
    }
    public void saveSC(SupplyCard sc) throws SQLException, IOException
    {
            scd = new SupplyCardDAO(connectDB);
            scd.saveInfo(sc);
    }
    public List<SupplyCard> getAllName() throws SQLException{
        return scd.getProvider();
    }
    public void disconnect() {
            scd.disconnect();
    }
}