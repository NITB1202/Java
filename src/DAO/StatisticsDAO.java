/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Tính
 */
import DTO.entities.StatisticDTO;
import DTO.entities.TopBorrowedBook;
import connection.ConnectDB;
import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import jdk.jfr.Percentage;


public class StatisticsDAO {
    ConnectDB connectDB;
    
    public StatisticsDAO() throws ClassNotFoundException, SQLException, IOException{
        connectDB = new ConnectDB();
    }
    
public Vector<StatisticDTO> getAll() throws ClassNotFoundException, SQLException {
    Vector<StatisticDTO> datas = new Vector<>();
    connectDB.connect();

    if (ConnectDB.conn != null) {
        try {
            // Truy vấn 1
            String sql1 = "SELECT MONTH(bc.startDate) AS thang, YEAR(bc.startDate) AS nam, " +
                          "COUNT(DISTINCT CASE WHEN DATEDIFF(DAY, bc.startDate, bc.realReDate) <= 14 THEN bc.id END) AS tradunghan, COUNT(DISTINCT bc.id) AS tongphieumuon " +
                          "FROM detail_borrow_card dBC JOIN cp_book cpB ON dBC.ISBN = cpB.ISBN " +
                          "JOIN borrow_card bc ON bc.id = dBC.bcID GROUP BY MONTH(bc.startDate), YEAR(bc.startDate)";
            PreparedStatement pst1 = ConnectDB.conn.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next()) {
                int year = rs1.getInt("nam");
                int month = rs1.getInt("thang");
                int traDungHan = rs1.getInt("tradunghan");
                int tongPhieuMuon = rs1.getInt("tongphieumuon");

                StatisticDTO statisticDTO = checkThang(datas, year, month);

                if (statisticDTO == null) {
                    statisticDTO = new StatisticDTO(year, month, traDungHan, 0, tongPhieuMuon, 0);
                    datas.add(statisticDTO);
                } else {
                    statisticDTO.setTraDungHan(traDungHan);
                    statisticDTO.setTongPhieuMuon(tongPhieuMuon);
                }
            }

            // Truy vấn 2
            String sql2 = "SELECT YEAR(bc.realReDate) AS nam, MONTH(bc.realReDate) AS thang, " +
                          "SUM(dBC.lost * cpB.Cost) AS tienthu, " +
                          "SUM(dBC.lost) AS sosachmat FROM dbo.borrow_card bc JOIN dbo.detail_borrow_card dBC ON dBC.bcID = bc.id " +
                          "JOIN dbo.cp_book cpB ON cpB.ISBN = dBC.ISBN GROUP BY YEAR(bc.realReDate), MONTH(bc.realReDate)";
            PreparedStatement pst2 = ConnectDB.conn.prepareStatement(sql2);
            ResultSet rs2 = pst2.executeQuery();
            int index = 0;
            while (rs2.next()) {
                int year = rs2.getInt("nam");
                int month = rs2.getInt("thang");
                double tienThu = rs2.getDouble("tienthu");
                int lostBooks = rs2.getInt("sosachmat");

                StatisticDTO statisticDTO = checkThang(datas, year, month);

                if (statisticDTO == null) {
                    statisticDTO = new StatisticDTO(year, month, 0, tienThu, 0, lostBooks);
                    datas.add(statisticDTO);
                } else {
                    statisticDTO.setTienThu(tienThu);
                    statisticDTO.setSoSachMat(lostBooks);
                }
                index++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectDB.disconnect();
        }
    }
    return datas;
}

    private StatisticDTO checkThang(Vector<StatisticDTO> datas, int nam, int thang) {
        for (StatisticDTO statisticDTO : datas) {
            if (statisticDTO.getNam() == nam && statisticDTO.getThang() == thang) {
                return statisticDTO;
            }
        }
        return null;
    }
    
    
    public Vector<TopBorrowedBook> topLuotMuon() throws ClassNotFoundException, SQLException{
        Vector<TopBorrowedBook> top = new Vector<>();
        connectDB.connect();
        if(ConnectDB.conn != null){
            try {
                String sql = "SELECT b.name AS bName, a.name AS aName, p.name AS pName, COUNT(dBC.ISBN)*dBC.num AS TongSoLuotMuon\n" +
                                "FROM borrow_card bc\n" +
                                "JOIN detail_borrow_card dBC ON dBC.bcID = bc.id\n" +
                                "JOIN cp_book cpB ON  cpB.ISBN = dBC.ISBN\n" +
                                "JOIN book b ON b.id = cpB.bookID\n" +
                                "JOIN author a ON a.id = b.id\n" +
                                "JOIN publisher p ON p.id = cpB.publisherID\n" +
                                "GROUP BY b.name, a.name, p.name, dBC.ISBN, dBC.num\n" +
                                "ORDER BY TongSoLuotMuon DESC";
                PreparedStatement pst = ConnectDB.conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    TopBorrowedBook b = new TopBorrowedBook();
                    b.setNameBook(rs.getString(1));
                    b.setBookAuthor(rs.getString(2));
                    b.setNXB(rs.getString(3));
                    b.setSoLuotMuon(rs.getInt(4));
                    top.add(b);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return top;
    }
        
}
