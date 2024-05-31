/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.DetailBCDAO;
import DTO.entities.DetailBC;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
/**
 *
 * @author User
 */
public class DetailBCBUS {
    DetailBCDAO detailbcdal;

    
    public Vector<DetailBC> getinfo(int id) throws ClassNotFoundException, SQLException, IOException{
        detailbcdal = new DetailBCDAO();
        return detailbcdal.getinfo(id);
    }
     public void addDetailBC(int bcID,String ISBN,int num) throws ClassNotFoundException, SQLException, IOException
    {
        detailbcdal = new DetailBCDAO();
        detailbcdal.addDetailBC(bcID, ISBN, num);
        
    }
}
