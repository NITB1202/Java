/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import connection.ConnectDB;
import DTO.entities.Book1;
import DTO.entities.Publisher;
import DTO.entities.Book;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class BookDAO extends ConnectDB {
    protected Book1 book1;
    ConnectDB connectDB;
    public BookDAO() throws ClassNotFoundException, SQLException, IOException {
        connectDB = new ConnectDB();
    }
    public BookDAO(ConnectDB connectDB) throws ClassNotFoundException, SQLException, IOException{
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
    public ArrayList<Book1> getAll() throws ClassNotFoundException, SQLException, IOException {
        ArrayList<Book1> result = new ArrayList<>();
        connectDB.connect();
        if (connectDB.conn != null) {
            try {
                String sql = "SELECT cp_book.*,book.id,book.name,publisher.id,publisher.name,author.name FROM cp_book,book,publisher,author,book_author WHERE cp_book.bookID=book.id AND cp_book.isActive=1 AND cp_book.publisherID=publisher.id AND cp_book.ISBN=book_author.ISBN AND book_author.authorID=author.id";
                
                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connectDB.conn.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    boolean exist=false;
                   
                    for (Book1 i:result)
                    {
                        if (i.getISBN().equals(rs.getString("ISBN"))==true)
                        {
                            i.setAuthor(rs.getString(14));
                            exist=true;
                        }
                    }
                    if (exist==false)
                    {
                    String ISBN= rs.getString("ISBN");
                    String tenSach=rs.getString("name");
                    int storeNum=rs.getInt("storeNum");
                    int borrowNum=rs.getInt("borrowNum");
                    String publisher=rs.getString(13);
                    String author=rs.getString(14);
                    long Cost=rs.getLong("Cost");
                    String img=rs.getString("img");
                    String edition = rs.getString("edition");
                    
                    Book1 book=new Book1(ISBN,tenSach,edition,storeNum,borrowNum,publisher,Cost,img);
                    book.setAuthor(author);
                    result.add(book);
                    }
                }
            } catch (SQLException ex) {
                //Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                connectDB.disconnect();
            }
        }
        return result;
}
    
    public ArrayList<Book1> getAllIncludeVersion() throws ClassNotFoundException, SQLException, IOException {
        ArrayList<Book1> result = new ArrayList<>();
        connectDB.connect();
        if (connectDB.conn != null) {
            try {
                String sql = "SELECT cp_book.*,book.id,book.name,publisher.id,publisher.name,author.name FROM cp_book,book,publisher,author,book_author WHERE cp_book.bookID=book.id AND cp_book.isActive=1 AND cp_book.publisherID=publisher.id AND cp_book.ISBN=book_author.ISBN AND book_author.authorID=author.id";
                
                //Bước 2: tạo đối tượng preparedStatement
                PreparedStatement stmt = connectDB.conn.prepareStatement(sql);  

                ResultSet rs = stmt.executeQuery();
                
                //Bước 3: lấy dữ liệu
                while(rs.next()) {
                    boolean exist=false;
                   
                    for (Book1 i:result)
                    {
                        if (i.getISBN().equals(rs.getString("ISBN"))==true)
                        {
                            i.setAuthor(rs.getString(14));
                            exist=true;
                        }
                    }
                    if (exist==false)
                    {
                    String ISBN= rs.getString("ISBN");
                    String ver= rs.getString("edition");
                    String tenSach=rs.getString("name");
                    int storeNum=rs.getInt("storeNum");
                    int borrowNum=rs.getInt("borrowNum");
                    String publisher=rs.getString(13);
                    String author=rs.getString(14);
                    long Cost=rs.getLong("Cost");
                    String img=rs.getString("img");
                    
                    Book1 book=new Book1(ISBN,tenSach,ver,storeNum,borrowNum,publisher,Cost,img);
                    book.setAuthor(author);
                    result.add(book);
                    }
                }
            } catch (SQLException ex) {
                //Logger.getLogger(ChiTietPhieuNhapDAL.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                connectDB.disconnect();
            }
        }
        return result;
    }
    
    public ArrayList<Book1> allOutSearch(String fStr) throws ClassNotFoundException, SQLException, IOException{
    	ArrayList<Book1> arr = new ArrayList<Book1>();
        connectDB.connect();
        if(connectDB.conn!=null){
            try{
            	String[] str = fStr.split(" ");
            	StringBuilder query = new StringBuilder("SELECT cp_book.*,book.id,book.name,publisher.id,publisher.name,author.name FROM cp_book,book,publisher,author,book_author WHERE cp_book.bookID=book.id AND cp_book.isActive=1 AND cp_book.publisherID=publisher.id AND cp_book.ISBN=book_author.ISBN AND book_author.authorID=author.id ");
                for (int i = 0; i < str.length; i++) {
                	query.append("AND CONCAT(cp_book.ISBN, ' ', book.name, ' ', publisher.name, ' ', cp_book.edition) LIKE CONCAT( '%',?,'%') ");
                }
                PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query.toString());
                for (int i = 0; i < str.length; i++) {
                     preparedStatement.setString(i+1, str[i]);
                }
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()) {
                    boolean exist=false;
                   
                    for (Book1 i:arr)
                    {
                        if (i.getISBN().equals(rs.getString("ISBN"))==true)
                        {
                            i.setAuthor(rs.getString(14));
                            exist=true;
                        }
                    }
                    if (exist==false)
                    {
                    String ISBN= rs.getString("ISBN");
                    String ver= rs.getString("edition");
                    String tenSach=rs.getString("name");
                    int storeNum=rs.getInt("storeNum");
                    int borrowNum=rs.getInt("borrowNum");
                    String publisher=rs.getString(13);
                    String author=rs.getString(14);
                    long Cost=rs.getLong("Cost");
                    String img=rs.getString("img");
                    
                    Book1 book=new Book1(ISBN,tenSach,ver,storeNum,borrowNum,publisher,Cost,img);
                    book.setAuthor(author);
                    arr.add(book);
                    }
                }
            } catch(SQLException ex){
                ex.printStackTrace();
            } finally {
                connectDB.disconnect();
            }
        }
        return arr;
    }
    
    public void updateStoreNumBooks(String ISBN,int storeNum,int borrowNum) throws ClassNotFoundException, SQLException
    {
       connectDB.connect();
        
        if(connectDB.conn != null){
            try{
                Statement st = connectDB.conn.createStatement();
             String sql ="Update cp_Book SET storeNum="+storeNum+" ,borrowNum="+borrowNum+" WHERE ISBN='"+ISBN+"'";
                st.executeUpdate(sql);
            }catch(SQLException e){
                System.out.println(e);
            }finally{
               connectDB.disconnect();
               
            }
        }
    }
     
    public List<Book> getAllName() throws SQLException {
        List<Book> list = new ArrayList<>();
        String query = "SELECT name FROM book";
        connectDB.connect();    
        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Book b = new Book();
                b.setName(resultSet.getString("name"));
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Book> getAllBook() throws SQLException {
        List<Book> list = new ArrayList<>();
        String query = "SELECT * FROM book";
        connectDB.connect();    
        try (Connection connection = connectDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Book b = new Book();
                b.setId(resultSet.getInt("id"));
                b.setName(resultSet.getString("name"));
                b.setStatus(resultSet.getBoolean("isActive"));
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public String getByNameBook(String name) {
        String status = null;
        String query = "SELECT name FROM book WHERE name COLLATE Latin1_General_CI_AI = ?";

        try {
            connectDB.connect();
            Connection connection = connectDB.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        status = resultSet.getString("name");
                    }
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return status;
    }
    
    public void saveInfo(Book1 b) throws ClassNotFoundException, IOException {
        String query = "INSERT INTO book (name) VALUES (?)";
        try {
            connectDB.connect();
            if(connectDB.conn!=null)
            {
            	try (PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query)) {
                    preparedStatement.setString(1, b.getTenSach());
                    preparedStatement.executeUpdate();
                }
            }
            connectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveInfo(Book b) throws ClassNotFoundException, IOException {
        String query = "INSERT INTO book (name) VALUES (?)";
        try {
            connectDB.connect();
            if(connectDB.conn!=null)
            {
            	try (PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query)) {
                    preparedStatement.setString(1, b.getName());
                    preparedStatement.executeUpdate();
                }
            }
            connectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean delBook(String ISBN){
        boolean flag=false;
        String query = "update cp_book set isActive=0 where ISBN=?";
        try {
            connectDB.connect();
            if(connectDB.conn!=null)
            {
            	try (PreparedStatement preparedStatement = connectDB.conn.prepareStatement(query)) {
                    preparedStatement.setString(1, ISBN);
                    if(preparedStatement.executeUpdate()>0){
                        flag=true;
                    }
                }
            }
            connectDB.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}