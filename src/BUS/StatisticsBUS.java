/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author Tính
 */
import DAO.StatisticsDAO;
import DTO.entities.Reader;
import DTO.entities.StatisticDTO;
import DTO.entities.TopBorrowedBook;

import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatisticsBUS {
    private StatisticsDAO dao;
    
    public StatisticsBUS() throws ClassNotFoundException, SQLException, IOException{
        dao = new StatisticsDAO();
    }
    
    public Vector<StatisticDTO> getAll() throws ClassNotFoundException, SQLException{
        return dao.getAll();
    }
    
    public Vector<TopBorrowedBook> TopSachMuon() throws ClassNotFoundException, SQLException{
        return dao.topLuotMuon();
    }
    
}