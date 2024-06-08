/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.Staff;
import DTO.entities.SupplyCard;
import DTO.entities.SupplyCardDetail;
import DTO.entities.SupplyCardWithStaff;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author WIN 10
 */
public class SupplyCardDetailDAO {
    private ConnectDB connectDB;
    public SupplyCardDetailDAO(ConnectDB connectDB) throws ClassNotFoundException, SQLException, IOException{
    	try {
			this.connectDB = new ConnectDB();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public List<SupplyCardDetail> getAllSupplyCardDetail() {
        List<SupplyCardDetail> supplyCardDetailList = new ArrayList<>();
        String query = "SELECT * FROM detail_supply_card";

        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SupplyCardDetail supplyCardDetail = new SupplyCardDetail();
                supplyCardDetail.setScID(resultSet.getInt("scID"));
                supplyCardDetail.setISBN(resultSet.getString("ISBN"));
                supplyCardDetail.setNum(resultSet.getInt("num"));

                supplyCardDetailList.add(supplyCardDetail); // Thêm dữ liệu SupplyCardDetail vào danh sách
            }

            connection.close(); // Đóng kết nối sau khi sử dụng

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return supplyCardDetailList; // Trả về danh sách SupplyCardDetail
    }

    public void saveInfo(SupplyCardDetail supplyCardDetail){
       String query = "INSERT INTO detail_supply_card (scID, ISBN, num) VALUES (?,?,?)";

	    try {
	        connectDB.connect();
	        Connection connection = connectDB.getConnection();

	        if (connection != null) {
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setInt(1, supplyCardDetail.getScID());
	                preparedStatement.setString(2, supplyCardDetail.getISBN());
	                preparedStatement.setInt(3, supplyCardDetail.getNum());
	                preparedStatement.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
    public List<SupplyCardDetail> getSupplyCardDetailsById(int id) {
    	List<SupplyCardDetail> supplyCardDetailList = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
                    queryBuilder.append("SELECT detail_supply_card.scID, detail_supply_card.ISBN, detail_supply_card.num ")
                                .append("FROM supply_card ")
                                .append("INNER JOIN detail_supply_card ON supply_card.id = detail_supply_card.scID ")
                                .append("WHERE supply_card.id = ?");
                    String query = queryBuilder.toString();


        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SupplyCardDetail supplyCardDetail = new SupplyCardDetail();
                supplyCardDetail.setScID(resultSet.getInt("scID"));
                supplyCardDetail.setISBN(resultSet.getString("ISBN"));
                supplyCardDetail.setNum(resultSet.getInt("num"));

                supplyCardDetailList.add(supplyCardDetail); // Thêm dữ liệu SupplyCardDetail vào danh sách
            }

            connection.close(); // Đóng kết nối sau khi sử dụng

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return supplyCardDetailList; // Trả về danh sách SupplyCardDetail
    }
}
