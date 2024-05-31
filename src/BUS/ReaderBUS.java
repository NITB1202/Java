/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ReaderDAO;
import DTO.entities.Reader;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class ReaderBUS {
    protected static ReaderDAO readerDAO;

    public ReaderBUS() throws ClassNotFoundException, SQLException {
        
    }
     public Vector<Reader> getAll() throws ClassNotFoundException, SQLException {
        try {
            readerDAO = new ReaderDAO();
            return readerDAO.getAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
         return null;
     }
     public Vector<Reader> getAllReaderCanBorrow() throws ClassNotFoundException, SQLException {
        try {
            readerDAO = new ReaderDAO();
            return readerDAO.getAllReaderCanBorrow();
        } catch (IOException e) {
            e.printStackTrace();
        }
         return null;
     }
     public Vector<Reader> getReaderByName(String Name) throws ClassNotFoundException, SQLException
     {
          try {
            return readerDAO.getReaderByName(Name);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
     }
     public void updateFineDate() throws ClassNotFoundException, SQLException, IOException {
         readerDAO = new ReaderDAO();
    	readerDAO.updateFineDate();
    }

    public boolean checkTel(String tel) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
        readerDAO = new ReaderDAO();
    	return readerDAO.checkTel(tel);
    }
    
    public boolean checkTelExcept(String tel,int id) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException {
        readerDAO = new ReaderDAO();
    	return readerDAO.checkTelExcept(tel,id);
    }
    
    public Reader findbyID(int id) throws Exception {
        readerDAO = new ReaderDAO();
    	return readerDAO.findbyID(id);
    }
    
    public String addReader(Reader a) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        readerDAO = new ReaderDAO();
        if(readerDAO.addReader(a)){
            return "Thêm độc giả thành công";
        }else{
            return "Lỗi! Thêm độc giả không thành công";
        }
    }
    
    public String updateOneReader(Reader a) throws Exception {
        readerDAO = new ReaderDAO();
    	if(readerDAO.updateOneReader(a)){
            return "Sửa thông tin thành công";
        }else{
            return "Lỗi! Sửa thông tin không thành công";
        }
    }
    
    public String eraShowReader(Reader a) throws Exception {
        readerDAO = new ReaderDAO();
    	if(readerDAO.eraShowReader(a)){
            return "Xoá độc giả thành công";
        }else{
            return "Lỗi! Xoá độc giả không thành công";
        }
    }
    
    public Vector<Reader> allOutSearch(String fStr) throws Exception{
        readerDAO = new ReaderDAO();
        return readerDAO.allOutSearch(fStr);
    }
    public Vector<Reader> getReaderById(String infoID) throws ClassNotFoundException, SQLException, IOException {
        readerDAO = new ReaderDAO();
        return readerDAO.getReaderById(infoID);
    }
}
