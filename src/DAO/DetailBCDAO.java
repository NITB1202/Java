/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.entities.DetailBC;
import connection.ConnectDB;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author User
 */
public class DetailBCDAO {
    ConnectDB connectDB;
    public DetailBCDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }
    public Vector<DetailBC> getAll() throws ClassNotFoundException, SQLException, IOException{
        Vector<DetailBC> listBookinfo=new Vector<DetailBC>();
        connectDB.connect();
        if(connectDB.conn !=null){
            try{
                String sql="SELECT detail_borrow_card.*, book.name AS Bookname, author.name AS Authorname FROM cp_Book,bBook, detail_borrow_card, borrow_card, book_author, author WHERE borrow_card.id=detail_borrow_card.bcID AND cp_book.ISBN=detail_borrow_card.ISBN AND cp_book.ISBN=book_author.ISBN AND cp_book.bookID=book.id AND book_author.authorID=author.id";
                PreparedStatement stmt= connectDB.conn.prepareStatement(sql);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    DetailBC obj=new DetailBC();
                    obj.setID(rs.getInt("id"));
                    obj.setISBN("ISBN");
                    obj.setDescription("description");
                    obj.setBookname(rs.getString("Bookname"));
//                    obj.setAuthorname(rs.getString("Authorname"));
                     String sql2 = "Select author.name From book_author, author Where  author.authorID = book_author.id AND book_author.ISBN = ?";
                     PreparedStatement stmt2= connectDB.conn.prepareStatement(sql2);
                     stmt2.setString(1,obj.getISBN());
                     ResultSet rs2=stmt2.executeQuery();
                     Vector<String> Authorname =  new Vector<String>();
                     while (rs2.next()){
                         Authorname.add(rs2.getString(1));
                     }
                     obj.setAuthorname(Authorname);
                    obj.setNum(rs.getInt("num"));
                    obj.setLost(rs.getInt("lost"));
                    listBookinfo.add(obj);
                }
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                connectDB.disconnect();
            }
        }
        return listBookinfo;
    }
    public void addDetailBC(int bcID,String ISBN,int num) throws ClassNotFoundException, SQLException, IOException
    {
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
                Statement st = connectDB.conn.createStatement();
             String sql ="INSERT INTO detail_borrow_card (bcID,ISBN,num) VALUES ("+bcID+",'"+ISBN+"',"+num+")";
                st.executeUpdate(sql);           
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                connectDB.disconnect();
            }
        }
    }
    public Vector<DetailBC> getinfo(int id) throws ClassNotFoundException, SQLException, IOException{
        Vector<DetailBC> listBookinfo=new Vector<DetailBC>();
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
                String sql="SELECT detail_borrow_card.*, book.name AS Bookname, author.name AS Authorname FROM cp_book, book, detail_borrow_card, borrow_card, book_author, author WHERE borrow_card.id=detail_borrow_card.bcID AND cp_book.ISBN=detail_borrow_card.ISBN AND cp_book.ISBN=book_author.ISBN AND cp_book.bookID=book.id AND book_author.authorID=author.id AND borrow_card.id="+id;
                PreparedStatement stmt=connectDB.conn.prepareStatement(sql);
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    DetailBC obj=new DetailBC();
                    obj.setID(rs.getInt("bcID"));
                    obj.setISBN(rs.getString("ISBN"));
                    obj.setDescription(rs.getNString("description"));
                    obj.setBookname(rs.getNString("Bookname"));
                    String sql2 = "Select author.name From book_author, author Where  author.id = book_author.authorID AND book_author.ISBN = ?";
                    PreparedStatement stmt2= connectDB.conn.prepareStatement(sql2);
                    stmt2.setString(1,obj.getISBN());
                    ResultSet rs2=stmt2.executeQuery();
                    Vector<String> Authorname =  new Vector<String>();
                    while (rs2.next()){
                        Authorname.add(rs2.getString(1));
                    }
                    obj.setAuthorname(Authorname);
                    obj.setNum(rs.getInt("num"));
                    obj.setLost(rs.getInt("lost"));
                    listBookinfo.add(obj);
                }
            }catch(SQLException e){
                System.out.println(e);
            }finally{
                connectDB.disconnect();
            }
        }
        return listBookinfo;
    }
}
