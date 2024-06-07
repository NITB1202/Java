package DAO;

import DTO.entities.Person;
import DTO.entities.Account;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AccountDAO {
	
    protected ArrayList<Account> listAccount = new ArrayList<>();
    private ConnectDB connectDB;

    public AccountDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
        read();
    }

    public ArrayList<Account> getList() {
        return listAccount;
    }

    public void read() throws IOException, ClassNotFoundException, SQLException{
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
            String sql = "Select account.userID, username, password, positionID, dayCreate, account.isActive from account";
            Statement stmt = connectDB.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Account user = new Account();
                user.setPersonID(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPwd(rs.getString(3));
                user.setRoleID(rs.getString(4));
                user.setDayCreate(rs.getDate(5));
                user.setStatus(rs.getInt(6));
                listAccount.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectDB.disconnect(context);
    }
    
    public boolean create(Account user) throws ClassNotFoundException, SQLException {
        String context = this.getClass().getName();
        connectDB.connect(context);
        try {
            String sql = "INSERT INTO account(userID, userName, password, positionID, dayCreate, isActive) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connectDB.conn.prepareStatement(sql);
            pstmt.setInt(1, user.getPersonID());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPwd());
            pstmt.setString(4, user.getRoleID());
            java.sql.Timestamp dayCreate = new java.sql.Timestamp(user.getDayCreate().getTime());
            pstmt.setTimestamp(5, dayCreate);   
            pstmt.setInt(6, user.getStatus());
            pstmt.executeUpdate();
            listAccount.add(user);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        connectDB.disconnect(context);
        return false;
    }
    public Vector<Account> getAllQL() throws Exception{
        Vector<Account> arr = new Vector<Account>();
        String context = this.getClass().getName();
        connectDB.connect(context);        
        if(connectDB.conn!=null){
            try{
                String sql="select staff.id as sId,staff.name as sName,tel,address,userName,roles.name as rName from staff join account on staff.id=account.userID join roles on account.positionID=roles.id where staff.isActive=1 and roles.id!='AD';";
                Statement stmt = connectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    Account u = new Account();
                    u.setPersonID(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    u.setUsername(rs.getString(5));
                    u.setRoleID(rs.getString(6));
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
    public Vector<Account> getAllAD() throws Exception{
        Vector<Account> arr = new Vector<Account>();
        String context = this.getClass().getName();
        connectDB.connect(context);   
        if(connectDB.conn!=null){
            try{
                String sql="select staff.id as sId,staff.name as sName,tel,address,userName,roles.name as rName from staff join Account on staff.id=Account.userID join roles on Account.positionID=roles.id where staff.isActive=1;";
                Statement stmt = connectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    Account u = new Account();
                    u.setPersonID(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    u.setUsername(rs.getString(5));
                    u.setRoleID(rs.getString(6));
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
    public Account findbyID (int id) throws Exception {
    	Account a=new Account();
    	String context = this.getClass().getName();
        connectDB.connect(context);   
    	if( connectDB.conn!=null){
            try{
            	String sql="select staff.id as sId,staff.name as sName,tel,address,userName,roles.id as rId from staff join account on staff.id=account.userID join roles on account.positionID=roles.id where staff.id=?;";
            	PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
            	preparedStatement.setInt(1, id);
            	ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                    a.setPersonID(rs.getInt(1));
                    a.setName(rs.getString(2));
                    a.setTel(rs.getString(3));
                    a.setAddress(rs.getString(4));
                    a.setUsername(rs.getString(5));
                    a.setRoleID(rs.getString(6));
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
    	return a;
    }
    public Vector<Account> allOutSearchQL(String fStr) throws ClassNotFoundException, SQLException, IOException{
    	Vector<Account> arr = new Vector<Account>();
        String context = this.getClass().getName();
        connectDB.connect(context);   
        if( connectDB.conn!=null){
            try{
            	String[] str = fStr.split(" ");
            	StringBuilder query = new StringBuilder("select staff.id,staff.name,tel,address,userName,roles.name from staff join account on staff.id=account.userID join roles on account.positionID=roles.id where staff.isActive=1 and roles.id!='AD' ");
                for (int i = 0; i < str.length; i++) {
                	query.append("AND CONCAT(staff.id, ' ', staff.name, ' ', tel, ' ', address, ' ',userName) LIKE CONCAT( '%',?,'%') ");
                }
                PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query.toString());
                for (int i = 0; i < str.length; i++) {
                     preparedStatement.setString(i+1, str[i]);
                }
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Account u = new Account();
                    u.setPersonID(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    u.setUsername(rs.getString(5));
                    u.setRoleID(rs.getString(6));
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
    public Vector<Account> allOutSearchAD(String fStr) throws ClassNotFoundException, SQLException, IOException{
    	Vector<Account> arr = new Vector<Account>();
        String context = this.getClass().getName();
        connectDB.connect(context);   
        if(connectDB.conn!=null){
            try{
            	String[] str = fStr.split(" ");
            	StringBuilder query = new StringBuilder("select staff.id ,staff.name,tel,address,userName,roles.name from staff join account on staff.id=account.userID join roles on account.positionID=roles.id where staff.isActive=1 ");
                for (int i = 0; i < str.length; i++) {
                	query.append("AND CONCAT(staff.id, ' ', staff.name, ' ', tel, ' ', address, ' ',userName) LIKE CONCAT( '%',?,'%') ");
                }
                PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query.toString());
                for (int i = 0; i < str.length; i++) {
                     preparedStatement.setString(i+1, str[i]);
                }
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Account u = new Account();
                    u.setPersonID(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setTel(rs.getString(3));
                    u.setAddress(rs.getString(4));
                    u.setUsername(rs.getString(5));
                    u.setRoleID(rs.getString(6));
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
    
    public String getRole(String id) throws SQLException{
        String role="";
        String context = this.getClass().getName();
        connectDB.connect(context);   
        if(connectDB.conn!=null){
            try{
            	String sql="select name from roles where id=?";
                PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
            	preparedStatement.setString(1, id);
            	ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                    role=rs.getString(1);
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return role;
    }
    
    public String getRoleID(String name) throws SQLException{
        String role="";
        String context = this.getClass().getName();
        connectDB.connect(context);   
        if(connectDB.conn!=null){
            try{
            	String sql="select id from roles where name=?";
                PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
            	preparedStatement.setString(1, name);
            	ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                    role=rs.getString(1);
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return role;
    }
}
