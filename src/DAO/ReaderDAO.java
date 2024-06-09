/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import connection.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.entities.Reader;
import java.sql.Statement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
/**
 *
 * @author ADMIN
 */
public class ReaderDAO extends ConnectDB {
    private ConnectDB connectDB;

    public ReaderDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }

     public Vector<Reader> getAll() throws ClassNotFoundException, SQLException, IOException {
        Vector<Reader> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null) {
            try {
                String sql = "SELECT reader.* FROM Reader where isActive = 1";
                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connectDB.conn.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    Reader u = new Reader();
                    u.setPersonID(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    Date lDate = rs.getDate(5);
                    if(lDate==null) {
                    	u.setFineDate(null);
                    }else {
                    	u.setFineDate(lDate.toLocalDate());
                    }
                    result.add(u);
                    u.setPenalty(rs.getInt(7));
                }
            } catch (SQLException ex) {
                //Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                connectDB.disconnect();
            }
        }
        return result;
}
          public Vector<Reader> getAllReaderCanBorrow() throws ClassNotFoundException, SQLException, IOException {
        Vector<Reader> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null) {
            try {
                String sql = "SELECT reader.* FROM Reader where isActive = 1 and fineDate is null";
                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connectDB.conn.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    Reader u = new Reader();
                    u.setPersonID(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    Date lDate = rs.getDate(5);
                    if(lDate==null) {
                    	u.setFineDate(null);
                    }else {
                    	u.setFineDate(lDate.toLocalDate());
                    }
                    result.add(u);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                connectDB.disconnect();
            }
        }
        return result;
}
     public Vector<Reader> getReaderById(String id) throws ClassNotFoundException, SQLException {
        Vector<Reader> result = new Vector<>();
        connectDB.connect();
        if (ConnectDB.conn != null) {
            try {
                String sql = "SELECT Reader.* FROM Reader where isActive =1 and fineDate is null AND id LIKE N'%"+id+"%'";
                PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Reader reader = new Reader();
                    reader.setPersonID(rs.getInt(1));
                    reader.setName(rs.getString(2));
                    reader.setTel(rs.getString(3));
                    reader.setAddress(rs.getString(4));
                    Date lDate = rs.getDate(5);
                    result.add(reader);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                 connectDB.disconnect();
            }
        }
        return result;
}
      public Vector<Reader> getReaderByName(String Name) throws ClassNotFoundException, SQLException, IOException {
        Vector<Reader> result = new Vector<>();
        connectDB.connect();
        
        if (connectDB.conn != null) {
            
            try {
                String sql = "SELECT reader.* FROM reader where isActive =1 AND name LIKE N'%"+Name+"%'";
                
                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connectDB.conn.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    Reader reader = new Reader();
                    reader.setPersonID(rs.getInt(1));
                    reader.setName(rs.getString(2));
                    reader.setTel(rs.getString(3));
                    reader.setAddress(rs.getString(4));
                    Date lDate = rs.getDate(5);
                    if(lDate != null)
                    	reader.setFineDate(lDate.toLocalDate());
                    result.add(reader);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
     public boolean addReader(Reader a) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        boolean flag=false;
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
                String sql="Insert into reader(name,tel,address) values (?,?,?)";
                PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
                preparedStatement.setString(1, a.getName());
                preparedStatement.setString(2, a.getTel());
                preparedStatement.setString(3, a.getAddress());
                if(preparedStatement.executeUpdate()>0){
                    flag=true;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public void updateFineDate() throws ClassNotFoundException, SQLException, IOException {
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	LocalDate cuDate=LocalDate.now();
            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = cuDate.format(formatter);
            	String sql="update reader set fineDate=null where fineDate<='"+formattedDate+"'";
            	Statement stm= connectDB.conn.createStatement();
            	stm.executeUpdate(sql);
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
    }
    
    public Reader findbyID (int id) throws Exception {
    	Reader a=new Reader();
        connectDB.connect();
    	if( connectDB.conn!=null){
            try{
            	String sql="select * from reader where id=?";
            	PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
            	preparedStatement.setInt(1, id);
            	ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                	a.setPersonID(rs.getInt(1));
                	a.setName(rs.getString(2));
                	a.setTel(rs.getString(3));
                    a.setAddress(rs.getString(4));
                    Date lDate = rs.getDate(5);
                    if(lDate != null)
                    	a.setFineDate(lDate.toLocalDate());
                    a.setPenalty(rs.getInt(7));
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
    	return a;
    }

    public boolean checkTel(String tel) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        boolean flag=true;
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	String sql="select * from reader where tel=?";
            	PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
                preparedStatement.setString(1, tel);
                ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                	flag=false;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean checkTelExcept(String tel,int id) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        boolean flag=true;
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	String sql="select * from reader where tel=? and id!=?";
            	PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
                preparedStatement.setString(1, tel);
                preparedStatement.setInt(2, id);
                ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                	flag=false;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean updateOneReader(Reader a) throws Exception {
    	boolean flag=true;
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	String sql="update reader set name=? , tel=? , address=? where id=? ";
            	PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
                preparedStatement.setString(1, a.getName());
                preparedStatement.setString(2, a.getTel());
                preparedStatement.setString(3, a.getAddress());
                preparedStatement.setInt(4, a.getPersonID());
                if(preparedStatement.executeUpdate()>0){
                    flag=true;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean eraShowReader(Reader a) throws Exception{
    	boolean flag=false;
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	String sql="update reader set isActive=0 where id=? ";
            	PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
                preparedStatement.setInt(1, a.getPersonID());
                if(preparedStatement.executeUpdate()>0){
                    flag=true;
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public Vector<Reader> allOutSearch(String fStr) throws ClassNotFoundException, SQLException, IOException{
    	Vector<Reader> arr = new Vector<Reader>();
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	String[] str = fStr.split(" ");
            	StringBuilder query = new StringBuilder("SELECT * FROM reader WHERE isActive=1 ");
                for (int i = 0; i < str.length; i++) {
                	query.append("AND CONCAT(id, ' ', name, ' ', tel, ' ', address) LIKE CONCAT( '%',?,'%') ");
                }
                PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query.toString());
                for (int i = 0; i < str.length; i++) {
                     preparedStatement.setString(i+1, str[i]);
                }
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                	Reader u = new Reader();
                    u.setPersonID(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    java.sql.Date lDate = rs.getDate(5);
                    if(lDate==null) {
                    	u.setFineDate(null);
                    }else {
                    	u.setFineDate(lDate.toLocalDate());
                    }
                    arr.add(u);
                }

            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return arr;
    }
    
    public int getNumOfBorrowBook(String readerName) throws SQLException
    {
        int numOfBorrowedBooks = 0;
        connectDB.connect();
        if(connectDB.conn!=null)
        {
        	String query = "SELECT SUM(num) as SLDangMuon\r\n"
        			+ "FROM borrow_card bc, reader, detail_borrow_card\r\n"
        			+ "WHERE bc.isActive = 1\r\n"
        			+ "AND bc.readerID = reader.id\r\n"
        			+ "AND bc.id = bcID\r\n"
        			+ "AND name COLLATE Latin1_General_CI_AI = ?\r\n"
        			+ "GROUP BY name";
        	PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query);
            preparedStatement.setString(1,readerName);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            	numOfBorrowedBooks = rs.getInt("SLDangMuon");
            rs.close();
            preparedStatement.close();
            connectDB.disconnect();
        }
        return numOfBorrowedBooks;
    }
    
    public int getPenalty(String readerName) throws SQLException
    {
    	int penalty = -1;
    	connectDB.connect();
        if(connectDB.conn!=null)
        {
        	String query = "SELECT penalty FROM reader WHERE name COLLATE Latin1_General_CI_AI = ?";
        	PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query);
            preparedStatement.setString(1,readerName);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            	penalty = rs.getInt("penalty");
            rs.close();
            preparedStatement.close();
            connectDB.disconnect();
        }
        return penalty;
    }
    
    public void payPenalty(int readerID) throws SQLException
    {
    	String query = "UPDATE reader SET fineDate = NULL, penalty = 0 WHERE id = ?";
    	connectDB.connect();
        if(connectDB.conn!=null)
        {
        	PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query);
            preparedStatement.setInt(1,readerID);
            if(preparedStatement.executeUpdate()>0)
            	System.out.println("Update tien phat thanh cong");
            preparedStatement.close();
        }
        connectDB.disconnect();
    }
    
//    public LocalDate getFineDate(String readerName)
//    {
//    	connectDB.connect();
//        if(connectDB.conn!=null)
//        {
//        	String query = "SELECT fineDate FROM reader WHERE name COLLATE Latin1_General_CI_AI = ?";
//        	PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query);
//            preparedStatement.setString(1,readerName);
//            ResultSet rs = preparedStatement.executeQuery();
//            if(rs.next())
//            {
//            	String dateString = rs.getString("fineDate");
//                // Chuyển đổi chuỗi ngày sang LocalDate
//                LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
//            }
//            rs.close();
//            preparedStatement.close();
//            connectDB.disconnect();
//        }
//    }
}
