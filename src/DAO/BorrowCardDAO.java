/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.entities.Book;
import DTO.entities.Book1;
import connection.ConnectDB;
import DTO.entities.BorrowCard;
import DTO.entities.DetailBC;
import java.io.IOException;
import java.sql.*;
import java.util.*;
/**
 *
 * @author User
 */
public class BorrowCardDAO{
    ConnectDB connectDB;
    public BorrowCardDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }
    public Vector<BorrowCard> getAll() throws ClassNotFoundException, SQLException, IOException{
        Vector<BorrowCard> listticket=new Vector<BorrowCard>();
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
                String sql="SELECT borrow_card.*, reader.name AS Readername, staff.name AS Staffname FROM borrow_card, reader, staff WHERE borrow_card.readerID=reader.id AND borrow_card.staffID=staff.id";
                PreparedStatement stmt=connectDB.conn.prepareStatement(sql);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    BorrowCard obj=new BorrowCard();
                    obj.setID(rs.getInt("ID"));
                    obj.setReadername(rs.getNString("Readername"));
                    obj.setStaffname(rs.getNString("Staffname"));
                    obj.setStartDate(rs.getDate("startDate"));
                    obj.setExpReDate(rs.getDate("expReDate"));
                    obj.setRealReDate(rs.getDate("realReDate"));
                    obj.setdeposit(rs.getLong("deposit"));
                    obj.setstatus(rs.getBoolean("isActive"));
                    listticket.add(obj);
                    for(int i=0;i<listticket.size();i++) {
             		String tmp="select book.name, detail_borrow_card.num, detail_borrow_card.lost, cp_book.ISBN\r\n"
             				+ "from borrow_card join detail_borrow_card on borrow_card.id=detail_borrow_card.bcID \r\n"
             				+ "				join cp_book on detail_borrow_card.ISBN=cp_book.ISBN \r\n"
             				+ "				join book on cp_book.bookID=book.id \r\n"
             				+ "where borrow_card.id=?";
             		PreparedStatement prep1= connectDB.conn.prepareStatement(tmp);
             		prep1.setInt(1, listticket.get(i).getID());
                 	ResultSet rs1=prep1.executeQuery();
                 	Vector<DetailBC> sb=new Vector<DetailBC>();
                 	while(rs1.next()) {
                            DetailBC b=new DetailBC();
                            b.setBookname(rs1.getString(1));
                            b.setNum(rs1.getInt(2));
                            b.setLost(rs1.getInt(3));
                            b.setISBN(rs1.getString(4));
                            String t="select author.name\r\n"
                                            + "from cp_book join book_author on cp_book.ISBN=book_author.ISBN \r\n"
                                            + "			join author on book_author.authorID=author.id\r\n"
                                            + "where cp_book.ISBN=?";
                            PreparedStatement prep2= connectDB.conn.prepareStatement(t);
                            prep2.setString(1,b.getISBN());
                            ResultSet rs2=prep2.executeQuery();
                            Vector<String> au=new Vector<String>();
                            while(rs2.next()) {
                                    au.add(rs2.getString(1));
                            }
                            b.setAuthorname(au);
                            sb.add(b);
                 	}
                 	listticket.get(i).setNum(sb.size());
                 	listticket.get(i).setListBook(sb);
                    }
                    
                }
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                connectDB.disconnect();
            }
        }
        System.out.println(listticket);
        return listticket;
    }
    // FIX
    public int addTicket(String startDate,String expReDate,long deposit,int readerID,int staffID) throws ClassNotFoundException, SQLException, IOException
    {
         connectDB=new ConnectDB();
         int idBC=0;
         connectDB.connect();
        if(connectDB.conn !=null){
            try{
                Statement st = connectDB.conn.createStatement();
             String sql ="INSERT INTO borrow_card (startDate, expReDate, deposit, readerID, staffID) VALUES  ('"+startDate+"', '"+expReDate+"','"+deposit+"',"+readerID+","+ staffID+")";
                st.executeUpdate(sql);
                String sql2="SELECT TOP 1 id FROM borrow_card ORDER BY id DESC";
                PreparedStatement stmt2=connectDB.conn.prepareStatement(sql2);
                ResultSet rs2=stmt2.executeQuery();
                while(rs2.next()){
                idBC=rs2.getInt("id");
                }
                return idBC;
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                connectDB.disconnect();
            }
        }
        return idBC;
       
    }
    public Vector<BorrowCard> getByCondition(String condition) throws ClassNotFoundException, SQLException, IOException{
        Vector<BorrowCard> list=new Vector<BorrowCard>();
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
                String sql="SELECT borrow_card.*, reader.name AS Readername, staff.name AS Staffname FROM borrow_card, reader, staff WHERE borrow_card.readerID=reader.id AND borrow_card.staffID=staff.id AND "+condition;
                PreparedStatement stmt=connectDB.conn.prepareStatement(sql);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    BorrowCard obj=new BorrowCard();
                    obj.setID(rs.getInt("ID"));
                    obj.setReadername(rs.getNString("Readername"));
                    obj.setStaffname(rs.getNString("Staffname"));
                    obj.setStartDate(rs.getDate("startDate"));
                    obj.setExpReDate(rs.getDate("expReDate"));
                    obj.setRealReDate(rs.getDate("realReDate"));
                    obj.setdeposit(rs.getLong("deposit"));
                    obj.setstatus(rs.getBoolean("isActive"));
                    list.add(obj);
                }
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                connectDB.disconnect();
            }
        }
        return list;
    }
    
    public Vector<BorrowCard> getAll(int id) throws Exception{
    	Vector<BorrowCard> arr=new Vector<BorrowCard>();
         connectDB.connect();
         if(ConnectDB.conn!=null){
             try{
                 String sql="select borrow_card.id,startDate,realReDate,staff.name\r\n"
                 		+ "from reader join borrow_card on reader.id=borrow_card.readerID\r\n"
                 		+ "			join staff on borrow_card.staffID=staff.id\r\n"
                 		+ "where reader.id=?";
                PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
             	preparedStatement.setInt(1, id);
             	ResultSet rs=preparedStatement.executeQuery();
             	while(rs.next()) {
             		BorrowCard a = new BorrowCard();
                	a.setID(rs.getInt(1));
             		a.setStartDate(rs.getDate(2));
                	java.sql.Date realRE=rs.getDate(3);
                	if(realRE==null) {
                		a.setRealReDate(null);
                	}else {
                		a.setRealReDate(realRE);
                	}
                	a.setStaffname(rs.getString(4));
                	arr.add(a);
                }
             	for(int i=0;i<arr.size();i++) {
             		String tmp="select Book.name,detail_borrow_card.num, detail_borrow_card.lost, cp_book.ISBN\r\n"
             				+ "from borrow_card join detail_borrow_card on borrow_card.id=detail_borrow_card.bcID \r\n"
             				+ "				join cp_book on detail_borrow_card.ISBN=cp_book.ISBN \r\n"
             				+ "				join Book on cp_book.bookID=Book.id \r\n"
             				+ "where borrow_card.id=?";
             		PreparedStatement prep1= connectDB.conn.prepareStatement(tmp);
             		prep1.setInt(1, arr.get(i).getID());
                 	ResultSet rs1=prep1.executeQuery();
                 	Vector<DetailBC> sb=new Vector<DetailBC>();
                 	while(rs1.next()) {
                            DetailBC b=new DetailBC();
                            b.setBookname(rs1.getString(1));
                            b.setNum(rs1.getInt(2));
                            b.setLost(rs1.getInt(3));
                            b.setISBN(rs1.getString(4));
                            String t="select author.name\r\n"
                                            + "from cp_book join book_author on cp_book.ISBN=book_author.ISBN \r\n"
                                            + "			join author on book_author.authorID=author.id\r\n"
                                            + "where cp_book.ISBN=?";
                            PreparedStatement prep2= connectDB.conn.prepareStatement(t);
                            prep2.setString(1,b.getISBN());
                            ResultSet rs2=prep2.executeQuery();
                            Vector<String> au=new Vector<String>();
                            while(rs2.next()) {
                                    au.add(rs2.getString(1));
                            }
                            b.setAuthorname(au);
                            sb.add(b);
                 	}
                 	arr.get(i).setNum(sb.size());
                 	arr.get(i).setListBook(sb);
             	}
             } catch(SQLException ex){
                 ex.printStackTrace();
             } finally {
                 connectDB.disconnect();
             }
         }
    	return arr;
    }
    
    
// ReceiveBook
    public Vector<BorrowCard> getAllBC() throws ClassNotFoundException, SQLException, IOException{
        Vector<BorrowCard> listticket=new Vector<BorrowCard>();
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
                String sql="SELECT borrow_card.*, reader.name AS Readername, staff.name AS Staffname FROM borrow_card, reader, staff WHERE borrow_card.readerID=reader.id AND borrow_card.staffID=staff.id AND borrow_card.isActive = 1";
                PreparedStatement stmt=connectDB.conn.prepareStatement(sql);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    BorrowCard obj=new BorrowCard();
                    obj.setID(rs.getInt("ID"));
                    obj.setReadername(rs.getNString("Readername"));
                    obj.setStaffname(rs.getNString("Staffname"));
                    obj.setStartDate(rs.getDate("startDate"));
                    obj.setExpReDate(rs.getDate("expReDate"));
                    obj.setRealReDate(rs.getDate("realReDate"));
                    obj.setdeposit(rs.getLong("deposit"));
                    obj.setstatus(rs.getBoolean("isActive"));
                    listticket.add(obj);
                    for(int i=0;i<listticket.size();i++) {
             		String tmp="select book.name, detail_borrow_card.num, detail_borrow_card.lost, cp_book.ISBN,  cp_book.Cost\r\n"
             				+ "from borrow_card join detail_borrow_card on borrow_card.id=detail_borrow_card.bcID \r\n"
             				+ "				join cp_book on detail_borrow_card.ISBN=cp_book.ISBN \r\n"
             				+ "				join book on cp_book.bookID=book.id \r\n"
             				+ "where borrow_card.id=?";
             		PreparedStatement prep1= connectDB.conn.prepareStatement(tmp);
             		prep1.setInt(1, listticket.get(i).getID());
                 	ResultSet rs1=prep1.executeQuery();
                 	Vector<DetailBC> sb=new Vector<DetailBC>();
                 	while(rs1.next()) {
                            DetailBC b=new DetailBC();
                            b.setBookname(rs1.getString(1));
                            b.setNum(rs1.getInt(2));
                            b.setLost(rs1.getInt(3));
                            b.setISBN(rs1.getString(4));
                            b.setBookCost(rs1.getFloat(5));
                            String t="select author.name\r\n"
                                            + "from cp_book join book_author on cp_book.ISBN=book_author.ISBN \r\n"
                                            + "			join author on book_author.authorID=author.id\r\n"
                                            + "where cp_book.ISBN=?";
                            PreparedStatement prep2= connectDB.conn.prepareStatement(t);
                            prep2.setString(1,b.getISBN());
                            ResultSet rs2=prep2.executeQuery();
                            Vector<String> au=new Vector<String>();
                            while(rs2.next()) {
                                    au.add(rs2.getString(1));
                            }
                            b.setAuthorname(au);
                            sb.add(b);
                 	}
                 	listticket.get(i).setNum(sb.size());
                 	listticket.get(i).setListBook(sb);
                    }
                    
                }
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                connectDB.disconnect();
            }
        }
        System.out.println(listticket);
        return listticket;
    }
    
    public void RecoverBook(BorrowCard bc) throws ClassNotFoundException, SQLException{
        connectDB.connect();
        if(ConnectDB.conn != null){
            try {
                String sql = "UPDATE borrow_card SET isActive = 0 WHERE id =?";
                PreparedStatement pst = connectDB.conn.prepareCall(sql);
                pst.setInt(1, bc.getID());
                pst.executeUpdate();
                
                String sql1 = "UPDATE cp_book\n" +
                                "SET storeNum = storeNum + dBC.num, borrowNum = borrowNum - dBC.num\n" +
                                "FROM cp_book cpB\n" +
                                "JOIN detail_borrow_card dBC ON dBC.ISBN = cpB.ISBN \n" +
                                "JOIN borrow_card bc ON bc.id = dBC.bcID\n" +
                                "WHERE bc.id = ?";
                PreparedStatement pst1 = ConnectDB.conn.prepareCall(sql1);
                pst1.setInt(1, bc.getID());
                pst1.executeUpdate();
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
    }   
    
public void BooksLost(BorrowCard bc, int lost, String ISBN) throws ClassNotFoundException, SQLException {
    connectDB.connect();
    if (ConnectDB.conn != null) {
        try {
            String sql = "UPDATE cp_book\n" +
                    "SET storeNum = storeNum - ?\n" +
                    "FROM cp_book cpB\n" +
                    "JOIN detail_borrow_card dBC ON dBC.ISBN = cpB.ISBN \n" +
                    "JOIN borrow_card bc ON bc.id = dBC.bcID\n" +
                    "WHERE bc.id = ? AND dBC.ISBN = ?";
            PreparedStatement pst = connectDB.conn.prepareCall(sql);
            pst.setInt(1, lost);
            pst.setInt(2, bc.getID());
            pst.setString(3, ISBN);
            pst.executeUpdate();

            String sql1 = "UPDATE detail_borrow_card\n" +
                    "SET lost = lost + ?, num = num - ?\n" +
                    "FROM detail_borrow_card\n" +
                    "WHERE ISBN = ? AND bcID = ?";
            PreparedStatement pst1 = connectDB.conn.prepareCall(sql1);
            pst1.setInt(1, lost);
            pst1.setInt(2, lost);
            pst1.setString(3, ISBN);
            pst1.setInt(4, bc.getID());
            pst1.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectDB.disconnect();
        }
    }
}

    
    public void setRealReDate(int id, java.sql.Date realDate) throws SQLException, ClassNotFoundException, IOException{
        connectDB.connect();
        if(ConnectDB.conn != null){
        try {
            String sql = "UPDATE borrow_card SET realReDate = ? WHERE id = ?";
            PreparedStatement pst = ConnectDB.conn.prepareCall(sql); 
            java.sql.Date rDate = new java.sql.Date(realDate.getTime());
            pst.setDate(1, rDate);
            pst.setInt(2, id);
            pst.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
            connectDB.disconnect();
            }
        }
    }
    
    public void BanAcc(int id, java.sql.Date fineDate) throws SQLException, SQLException, SQLException, SQLException, SQLException{
        connectDB.connect();
        if(ConnectDB.conn != null){
            try {
                String sql = "UPDATE reader \n" +
                                "SET fineDate = ?\n" +
                                "FROM reader\n" +
                                "JOIN borrow_card bc ON bc.readerID = reader.id\n" +
                                "WHERE bc.id = ?";
                PreparedStatement pst = ConnectDB.conn.prepareCall(sql);
                java.sql.Date date = new java.sql.Date(fineDate.getTime());
                pst.setDate(1, date);
                pst.setInt(2, id);
                pst.executeUpdate();
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
    }
    
    public  void UnlockAcc() throws ClassNotFoundException, SQLException, IOException{
        connectDB.connect();
        if(ConnectDB.conn != null){
            try {
                String sql = "UPDATE reader \n" +
                                "SET fineDate = NULL\n" +
                                "WHERE DATEADD(DAY, 7, fineDate) <= GETDATE();";                             
                PreparedStatement pst = ConnectDB.conn.prepareCall(sql);
                pst.executeUpdate();
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               connectDB.disconnect();
            }
        }
    }
    
    public void updatePenalty(int personID, int penalty) throws SQLException
    {
    	 connectDB.connect();
         if(ConnectDB.conn != null){
             try {
                 String sql = "UPDATE reader SET penalty = ? WHERE id = ?";                             
                 PreparedStatement pst = ConnectDB.conn.prepareCall(sql);
                 pst.setInt(1, penalty);
                 pst.setInt(2, personID);
                 pst.executeUpdate();
             } catch (Exception e) {
                 e.printStackTrace();
             } finally {
                connectDB.disconnect();
             }
         }    	
    }
    
    public void updateDeposit(int bcID, int deposit) throws SQLException
    {
    	 connectDB.connect();
         if(ConnectDB.conn != null){
             try {
                 String sql = "UPDATE borrow_card SET deposit = ? WHERE id = ?";                       
                 PreparedStatement pst = ConnectDB.conn.prepareCall(sql);
                 pst.setInt(1, deposit);
                 pst.setInt(2, bcID);
                 pst.executeUpdate();
             } catch (Exception e) {
                 e.printStackTrace();
             } finally {
                connectDB.disconnect();
             }
         } 
    }
}
