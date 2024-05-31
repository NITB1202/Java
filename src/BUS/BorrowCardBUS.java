/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;
import DAO.BorrowCardDAO;
import DTO.entities.BorrowCard;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
/**
 *
 * @author User
 */
public class BorrowCardBUS {

    public BorrowCardBUS() {
    }
    private BorrowCardDAO borrowCardDAO;
    public Vector<BorrowCard> getAllTicket() throws ClassNotFoundException, SQLException, IOException{
        borrowCardDAO = new BorrowCardDAO();
        System.out.println("getAllTicketBUS");
        return borrowCardDAO.getAll();
    }
    public int addTicket(String startDate,String expReDate,long deposit,int readerID,int staffID) throws ClassNotFoundException, SQLException, IOException
    {
        borrowCardDAO = new BorrowCardDAO();
         return borrowCardDAO.addTicket(startDate, expReDate, deposit, readerID, staffID);
    }
    
    public Vector<BorrowCard> getByCondition(String condition) throws ClassNotFoundException, SQLException, IOException{
        borrowCardDAO = new BorrowCardDAO();
        return borrowCardDAO.getByCondition(condition);
    }
    public Vector<BorrowCard> getAll(int id) throws Exception{
        borrowCardDAO = new BorrowCardDAO();
    	return borrowCardDAO.getAll(id);
    }
    public Vector<BorrowCard> getReturn(int id) throws Exception{
    	Vector<BorrowCard> tmp= borrowCardDAO.getAll(id);
    	Vector<BorrowCard> arr=new Vector<BorrowCard>();
    	for(int i=0;i<tmp.size();i++) {
    		if(tmp.get(i).getRealReDate()!=null) {
    			arr.add(tmp.get(i));
    		}
    	}
    	return arr;
    }
    
    public Vector<BorrowCard> getNoReturn(int id) throws Exception{
    	Vector<BorrowCard> tmp= borrowCardDAO.getAll(id);
    	Vector<BorrowCard> arr=new Vector<BorrowCard>();
    	for(int i=0;i<tmp.size();i++) {
    		if(tmp.get(i).getRealReDate()==null) {
    			arr.add(tmp.get(i));
    		}
    	}
    	return arr;
    }
}
