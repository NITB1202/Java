package DAO;

import DTO.entities.Person;
import DTO.entities.Staff;
import DTO.entities.Account;
import connection.ConnectDB;
import java.io.FileNotFoundException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author WIN 10
 */
public class StaffDAO {
    
    protected ArrayList<Staff> list = new ArrayList<>();
    protected Staff staff = new Staff();
    protected Account user = new Account();
    private ConnectDB connectDB;

    public StaffDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }

    public ArrayList<Staff> getList() {
        return list;
    }

    public Staff getStaff() {
        return (Staff) staff;
    }
    
    public Vector<String> getRoleAD() throws Exception{
    	Vector<String> role=new Vector<String>();
    	connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	String sql="select roles.name from roles where roles.id!='AD' and isActive = 1";
                Statement stmt = connectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	role.add(rs.getString(1));
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
    	return role;
    }
    
    public Vector<String> getRoleAll() throws Exception{
    	Vector<String> role=new Vector<String>();
    	connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	String sql="select roles.name from roles Where isActive = 1";
                Statement stmt = connectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	role.add(rs.getString(1));
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
    	return role;
    }
    
    public Vector<String> getRoleQL() throws Exception{
    	Vector<String> role=new Vector<String>();
    	connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	String sql="select roles.name from roles where roles.id!='AD' AND roles.id!='QL' and isActive = 1";
                Statement stmt = connectDB.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                	role.add(rs.getString(1));
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
    	return role;
    }
    
    public boolean checkTel(String tel) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
        boolean flag=true;
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	String sql="select * from staff where tel=? and isActive=1";
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
            	String sql="select * from staff where tel=? and id!=? and isActive = 1";
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
    public boolean addStaff(Staff a, Account u) throws Exception{
        boolean flag=false;
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	ConnectDB.conn.setAutoCommit(false);
                String sql="Insert into staff(name,tel,address,managerID) values (?,?,?,?)";
                PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,a.getName());
                preparedStatement.setString(2,a.getTel());
                preparedStatement.setString(3,a.getAddress());
                preparedStatement.setInt(4, a.getManageID());
                if(preparedStatement.executeUpdate()>0){
                	int generatedID=-1; 
                	ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        generatedID = generatedKeys.getInt(1);
                    }
                	String sql1="Insert into account(userID,userName,password,positionID,daycreate) values (?,?,?,?,?)";
                	PreparedStatement prep1= connectDB.conn.prepareStatement(sql1);
                	prep1.setInt(1, generatedID);
                	prep1.setString(2,u.getUsername());
                	prep1.setString(3,u.hashPassword(u.getPwd()));
                	LocalDate ld=LocalDate.now();
                	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                	String formattedDate = ld.format(formatter); 
                	prep1.setString(5,formattedDate);
                	String sql2="Select id from roles where name=N'"+u.getRoleID()+"'";
                	String roleID="";
                	Statement stmt = ConnectDB.conn.createStatement();
                	ResultSet rs2=stmt.executeQuery(sql2);
                	while(rs2.next()) {
                    	roleID=rs2.getString(1);
                    }
                	prep1.setString(4,roleID);
                	if(prep1.executeUpdate()>0){
                		flag=true;
                	}
                }
                connectDB.conn.commit();
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean updateOneStaff(Staff a, Account u) throws Exception {
    	boolean flag=false;
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
                System.out.println("pass:"+u.getPwd());
            	connectDB.conn.setAutoCommit(false);
            	String sql="update staff set name=? , tel=? , address=? where id=? ";
            	PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
            	preparedStatement.setString(1,a.getName());
                preparedStatement.setString(2,a.getTel());
                preparedStatement.setString(3,a.getAddress());
                preparedStatement.setInt(4, a.getPersonID());
                if(preparedStatement.executeUpdate()>0){
                	if(u.getPwd().trim().equals("") || u.getPwd()==null) {
                            System.out.println(1.3);
                            String sql1="update account set userName=? , positionID=?  where userID=? ";
                            PreparedStatement prep1= connectDB.conn.prepareStatement(sql1);
                            prep1.setString(1,u.getUsername());
                            prep1.setString(2,u.getRoleID());
                            prep1.setInt(3,u.getPersonID());
                            if(prep1.executeUpdate()>0){
                    		flag=true;
                            }
                	}else {
                            System.out.println(2.3);
                            String sql1="update account set userName=? , password=? ,positionID=?  where userID=? ";
                            PreparedStatement prep1= connectDB.conn.prepareStatement(sql1);
                            prep1.setString(1,u.getUsername());
                            prep1.setString(2,u.hashPassword(u.getPwd()));
                            prep1.setString(3,u.getRoleID());
                            prep1.setInt(4,a.getPersonID());
                            if(prep1.executeUpdate()>0){
                    		flag=true;
                            }
                	}	
                }
                connectDB.conn.commit();
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public boolean eraShowStaff(Staff a) throws Exception{
    	boolean flag=true;
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	connectDB.conn.setAutoCommit(false);
            	String sql="update staff set isActive=0 where id=? ";
            	PreparedStatement preparedStatement= connectDB.conn.prepareStatement(sql);
                preparedStatement.setInt(1, a.getPersonID());
                if(preparedStatement.executeUpdate()>0){
                	String sql1="update account set isActive=0 where userID=? ";
                	PreparedStatement prep1= connectDB.conn.prepareStatement(sql1);
                	prep1.setInt(1, a.getPersonID());
                	if(prep1.executeUpdate()>0){
                		flag=true;
                	}
                }
                connectDB.conn.commit();
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return flag;
    }
    
    public List<Staff> getAllStaf() throws ClassNotFoundException, SQLException, IOException {
        List<Staff> list = new ArrayList<>();
		String query = "SELECT * FROM staff";
        connectDB.connect();
        try (
            PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Staff sc = new Staff();
                sc.setPersonID(resultSet.getInt("id"));
                sc.setManageID(resultSet.getInt("managerID"));
                sc.setName(resultSet.getString("name"));
                sc.setAddress(resultSet.getString("address"));
                sc.setTel(resultSet.getString("tel"));
                sc.setStatus(resultSet.getInt("status"));
                list.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Staff findbyID (int id) throws Exception {
    	Staff a=new Staff();
    	connectDB.connect();
    	if(ConnectDB.conn!=null){
            try{
            	String sql="select Staff.id as sId,Staff.name as sName,tel,address from Staff where Staff.id=?;";
            	PreparedStatement preparedStatement= ConnectDB.conn.prepareStatement(sql);
            	preparedStatement.setInt(1, id);
            	ResultSet rs=preparedStatement.executeQuery();
                while(rs.next()) {
                    a.setPersonID(rs.getInt(1));
                    a.setName(rs.getString(2));
                    a.setTel(rs.getString(3));
                    a.setAddress(rs.getString(4));
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
    	return a;
    }
}
