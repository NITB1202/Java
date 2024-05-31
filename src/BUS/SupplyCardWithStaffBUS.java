
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.StaffDAO;
import DAO.SupplyCardDAO;
import DTO.entities.Staff;
import DTO.entities.SupplyCard;
import DTO.entities.SupplyCardWithStaff;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WIN 10
 */
public class SupplyCardWithStaffBUS {
    private SupplyCardDAO supplyCardDAO;
    private StaffDAO staffDAO;
    private ConnectDB connectDB;
    public SupplyCardWithStaffBUS(ConnectDB connectDB) throws SQLException, IOException{
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
    
    public SupplyCardWithStaffBUS(SupplyCardDAO supplyCardDAO, StaffDAO staffDAO) {
        this.supplyCardDAO = supplyCardDAO;
        this.staffDAO = staffDAO;
    }
    
    public List<SupplyCardWithStaff> getAllSupplyCardWithStaff() throws ClassNotFoundException, SQLException, IOException {
        List<SupplyCardWithStaff> supplyCardsWithStaff = new ArrayList<>();
        List<SupplyCard> supplyCards = supplyCardDAO.getAllSC(); // Gọi phương thức từ lớp DAO để lấy danh sách supply_Card
        List<Staff> staffList = staffDAO.getAllStaf(); // Gọi phương thức từ lớp DAO để lấy danh sách Staff

        for (SupplyCard supplyCard : supplyCards) {
            // Tìm thông tin nhân viên dựa trên supplyCard.getStaffId() từ danh sách nhân viên
            Staff staff = findStaffById(staffList, supplyCard.getStaffID());

            // Tạo đối tượng SupplyCardWithStaffBUS bằng cách kết hợp thông tin từ SupplyCard và Staff
            SupplyCardWithStaff supplyCardWithStaff = new SupplyCardWithStaff(supplyCard, staff);
            supplyCardsWithStaff.add(supplyCardWithStaff);
        }

        return supplyCardsWithStaff;
    }

    private Staff findStaffById(List<Staff> staffList, int staffId) {
        for (Staff staff : staffList) {
            if (staff.getPersonID()== staffId) {
                return staff;
            }
        }
        return null; 
    }
}
