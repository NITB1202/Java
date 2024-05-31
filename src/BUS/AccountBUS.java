package BUS;

import DAO.AccountDAO;
import DTO.entities.Person;
import java.util.ArrayList;
import DTO.entities.Account;
import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author agond
 */
public class AccountBUS {
    protected static ArrayList<Person> list;
    protected static AccountDAO userDAO;
    private static int quantity = 0;

    public AccountBUS() throws ClassNotFoundException, SQLException, IOException {
        userDAO = new AccountDAO();
        list = new ArrayList<>(userDAO.getList());
        quantity = list.size();
    }

    public static ArrayList<Person> getList() {
        return list;
    }
    
    public static int getQuantity() {
        quantity = list.size();
        return quantity;
    }
    
    public static Account signIn(Account user) throws NoSuchAlgorithmException {
        user.setPwd(Account.hashPassword(user.getPwd()));
        Account temp;
        for (Person _user : list) {
            temp = (Account) _user;
            if (user.getUsername().equals(temp.getUsername()) && user.getPwd().equals(temp.getPwd())) {    
                return temp;
            }
        }
        return null;
    }

    
    public static boolean checkUnique(Account user){
        Account temp;
        for(Person _user : list){
            temp = (Account)_user;
            if(user.getUsername().equals(temp.getUsername())) return false;
        }
        return true;
    }
    
}