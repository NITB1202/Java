/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.WarehouseDAO;
import DTO.entities.Warehouse;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WIN 10
 */
public class WarehouseBUS {
    private WarehouseDAO kdao;
    private ConnectDB connectDB;
    public WarehouseBUS() throws SQLException, IOException{
        ConnectDB connectDB = null;
        try {
            connectDB = new ConnectDB();
            kdao = new WarehouseDAO(connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(WarehouseBUS.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void savecpB(Warehouse kho) throws SQLException, IOException
    {
            kdao = new WarehouseDAO(connectDB);
            kdao.saveInfo(kho);
    }
    public void saveOldBook(Warehouse kho) throws SQLException, IOException
    {
            kdao = new WarehouseDAO(connectDB);
            kdao.saveOldBook(kho);
    }
}
