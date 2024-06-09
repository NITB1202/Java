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
	    public String getISBNForBook(String bookName) throws SQLException {
	        String isbns = null;
	        String query = "SELECT ISBN AS isbn_name\r\n"
	        		+ "FROM cp_book, book\r\n"
	        		+ "WHERE bookID =id AND name COLLATE Latin1_General_CI_AI = ?";
	        connectDB.connect();
	        try (Connection conn = connectDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, bookName);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                isbns = rs.getString("isbn_name");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return isbns;
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
    
    public void updateBook(String oldName, String newName, String oldIsbn, String newIsbn, String edition, String img, int publisherID, int authorID, int categoryID, int cost) throws SQLException
    {
    	String query1 = "UPDATE book SET name = ? WHERE name COLLATE Latin1_General_CI_AI = ?";
    	String query2 = "UPDATE cp_book SET edition = ?, publisherID = ?, img = ?, cost = ? WHERE ISBN = ?";
    	String query3 = "UPDATE book_author SET authorID = ? WHERE ISBN = ?";
    	String query4 = "UPDATE book_category SET categoryID = ? WHERE ISBN = ?";
    	String query5 = "UPDATE cp_book SET ISBN = ? WHERE ISBN = ?";
    	
        connectDB.connect();
        
        if(connectDB.conn!=null)
        {
        	PreparedStatement ps = connectDB.conn.prepareStatement(query1);
        	ps.setString(1, newName);
        	ps.setString(2, oldName);
        	if(ps.executeUpdate()>0)
        		System.out.println("query 1 update successful");
        	
        	ps = connectDB.conn.prepareStatement(query2);
        	ps.setString(1, edition);
        	ps.setInt(2,publisherID);
        	ps.setString(3,img);
        	ps.setInt(4, cost);
        	ps.setString(5, oldIsbn);
        	if(ps.executeUpdate()>0)
        		System.out.println("query 2 update successful");
        	
        	ps = connectDB.conn.prepareStatement(query3);
        	ps.setInt(1, authorID);
        	ps.setString(2,oldIsbn);
        	if(ps.executeUpdate()>0)
        		System.out.println("query 3 update successful");
        	
        	ps = connectDB.conn.prepareStatement(query4);
        	ps.setInt(1, categoryID);
        	ps.setString(2, oldIsbn);
        	if(ps.executeUpdate()>0)
        		System.out.println("query 4 update successful");
        	
        	ps = connectDB.conn.prepareStatement(query5);
        	ps.setString(1,newIsbn);
        	ps.setString(2, oldIsbn);
        	
        	if(ps.executeUpdate()>0)
        		System.out.println("query 5 update successful");
        	
        	ps.close();
        }
        
        connectDB.disconnect();
    }
    
    public void insertBook(String name, String isbn,String edition,int publisherID,int cost,String img, int authorID, int categoryID ) throws SQLException
    {
    	String query1 = "INSERT INTO book(name) VALUES (?)"; 
    	String query2 = "SELECT id FROM book WHERE name COLLATE Latin1_General_CI_AI = ?";
    	String query3 = "INSERT INTO cp_book VALUES(?,?,0,0,?,?,1,?,?)";
    	String query4 = "INSERT INTO book_author VALUES(?,?)";
    	String query5 = "INSERT INTO book_category VALUES(?,?)";
    	
        connectDB.connect();
        
        if(connectDB.conn!=null)
        {
        	PreparedStatement ps = connectDB.conn.prepareStatement(query1);
        	ps.setString(1,name);
        	if(ps.executeUpdate()>0)
        		System.out.println("query 1 insert successful");
        	
        	ps = connectDB.conn.prepareStatement(query2);
        	ps.setString(1,name);
        	ResultSet rs = ps.executeQuery();
        	int id = -1;
        	if(rs.next())
        		id = Integer.parseInt(rs.getString("id"));
        	
        	if(id == -1)
        	{
        		System.out.println("can't get id");
        		rs.close();
        		connectDB.disconnect();
        		return;
        	}
        	else
        		System.out.println("query 2 select successful");
        	
        	rs.close();
        	
        	ps = connectDB.conn.prepareStatement(query3);
        	ps.setString(1,isbn);
        	ps.setInt(2, id);
        	ps.setString(3,edition);
        	ps.setInt(4,publisherID);
        	ps.setInt(5,cost);
        	ps.setString(6, img);
        	if(ps.executeUpdate()>0)
        		System.out.println("query 3 insert successful");
        	
        	ps = connectDB.conn.prepareStatement(query4);
        	ps.setInt(1,authorID);
        	ps.setString(2, isbn);
        	if(ps.executeUpdate()>0)
        		System.out.println("query 4 insert successful");
        	
        	ps = connectDB.conn.prepareStatement(query5);
        	ps.setInt(1,categoryID);
        	ps.setString(2, isbn);
        	if(ps.executeUpdate()>0)
        		System.out.println("query 5 insert successful");
        	
        	rs.close();
        	connectDB.disconnect();
        }
    	
    }
}