package BUS;

import DAO.BorrowCardDAO;
import DTO.entities.BorrowCard;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author WIN 10
 */
public class PayBUS {
    private BorrowCardDAO borrowCardDAO;
    public PayBUS() throws ClassNotFoundException {
        
    }
    
     public Vector<BorrowCard> getAll() throws Exception{
         borrowCardDAO = new BorrowCardDAO();
    	return borrowCardDAO.getAllBC();
    }
    public long CacluteDate(Date startDate, Date realDate) {
        return TimeUnit.DAYS.convert(realDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS);
    }
     
     public void RecoverBook(BorrowCard bc) throws ClassNotFoundException, SQLException, IOException{
         borrowCardDAO = new BorrowCardDAO();
         borrowCardDAO.RecoverBook(bc);
         
     } 
    
    public void BooksLost(BorrowCard bc, int lost, String ISBN) throws ClassNotFoundException, SQLException, IOException{
         borrowCardDAO = new BorrowCardDAO();
         borrowCardDAO.BooksLost(bc, lost, ISBN);
     }
     
     public void setRealReDate(int id, Date realDate) throws ClassNotFoundException, SQLException, IOException{
        borrowCardDAO = new BorrowCardDAO();
        borrowCardDAO.setRealReDate(id, (java.sql.Date) realDate);
     }
     
      public void banAcc(int id, Date fineDate) throws ClassNotFoundException, SQLException, IOException{
        borrowCardDAO = new BorrowCardDAO();
        borrowCardDAO.BanAcc(id, (java.sql.Date) fineDate);
     }
      
     public void UnlockAcc() throws ClassNotFoundException, SQLException, IOException{
        borrowCardDAO = new BorrowCardDAO();
        borrowCardDAO.UnlockAcc();
     }
     
     public void updatePenalty(int personID, int penalty) throws ClassNotFoundException, SQLException, IOException
     {
         borrowCardDAO = new BorrowCardDAO();
         borrowCardDAO.updatePenalty(personID, penalty);
     }
     
     public void updateDeposit(int bcID, int deposit) throws ClassNotFoundException, SQLException, IOException
     {
         borrowCardDAO = new BorrowCardDAO();
         borrowCardDAO.updateDeposit(bcID, deposit);
     }
}