package BUS;

import DAO.AccountDAO;
import DTO.entities.Person;
import java.util.ArrayList;
import DTO.entities.Account;
import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Vector;


public class AccountBUS {
    protected  ArrayList<Account> list;

    public AccountBUS() throws ClassNotFoundException, SQLException, IOException {
        list = new ArrayList<>((new AccountDAO()).getList());
    }

    public ArrayList<Account> getList() {
        return list;
    }
    
    public int getQuantity() {
    	return list.size();
    }
    
    //Get all information for account
    public Account signIn(Account user) throws NoSuchAlgorithmException {
        String hashPass = Account.hashPassword(user.getPwd());
        for (Account account : list)
        	if(account.getUsername().equals(user.getUsername()) && account.getPwd().equals(hashPass))
        		return account;
        return null;
    }
    
    public boolean checkUnique(Account user){
        for(Account account : list){
            if(user.getUsername().equals(account.getUsername())) return false;
        }
        return true;
    }
    
}