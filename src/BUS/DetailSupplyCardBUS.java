package BUS;

import DAO.SupplyCardDetailDAO;
import DTO.entities.SupplyCardDetail;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author WIN 10
 */
public class DetailSupplyCardBUS {
    private  SupplyCardDetailDAO dd;
    private SupplyCardDetail dsc;
    private ConnectDB connectDB;
    public DetailSupplyCardBUS() throws IOException
    {
        ConnectDB connectDB = null;
        try {
            dd = new SupplyCardDetailDAO(this.connectDB);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveDSC(SupplyCardDetail dsc) throws ClassNotFoundException, SQLException, IOException
    {
            dd = new SupplyCardDetailDAO(this.connectDB);
            dd.saveInfo(dsc);
    }
}
