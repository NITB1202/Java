/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.SupplierDAO;
import DTO.entities.Supplier;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author WIN 10
 */
public class SupplierBUS {
    private  SupplierDAO ad;
    private ConnectDB connectDB;
    public SupplierBUS() throws SQLException, IOException, ClassNotFoundException
    {
        this.connectDB = null;
        try {
            connectDB = new ConnectDB();
            ad = new SupplierDAO(connectDB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getByNameAuthor(String name) throws SQLException, IOException
    {
            ad = new SupplierDAO();
            return ad.getBySupplierName(name);
    }
    public List<Supplier> getAllName() throws SQLException{
        return ad.getAllName();
    }
    public void saveInfo(Supplier a) throws SQLException, IOException
    {
            ad = new SupplierDAO();
            ad.saveSupplier(a);

    }
    public int getBySupplierID(String sub) throws SQLException, IOException
    {
    	ad = new SupplierDAO(connectDB);
    	return ad.getBySupplierID(sub);
    }
    public void saveSupplier(Supplier a) throws SQLException, IOException
    {
    	ad = new SupplierDAO(connectDB);
    	ad.saveSupplier(a);
    }
    public void updateSupplier(Supplier a) throws SQLException, IOException
    {
    	ad = new SupplierDAO(connectDB);
    	ad.update(a);
    }
    public boolean deteleBySupplierName(String name) throws  SQLException, IOException{
        ad = new SupplierDAO();
        ad.deleteByName(name);
        return true;
    }
    public void disconnect() {
        ad.disconnect();
    }
}