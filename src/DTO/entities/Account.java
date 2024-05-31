/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

import assets.EnumCheck.PwdValidStatus;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author WIN 10
 */
public class Account extends Person{
    protected String username;
    protected String pwd;
    protected String roleID;
    protected Date dayCreate;

    public Account() {
    }

    public Account(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }
    
    
    public Account(int personID,String username, String pwd, String roleID, Date dayCreate,  int status) {
        super(personID, status);
        this.username = username;
        this.pwd = pwd;
        this.roleID = roleID;
        this.dayCreate = dayCreate;
    }

    public Account(Account user) {
        super(user.getPersonID(), user.getName(), user.getTel(), user.getAddress(), user.getStatus());
        this.username = username;
        this.pwd = pwd;
        this.roleID = roleID;
        this.dayCreate = dayCreate;
    }

    public Account(String username, String pwd, String roleID, int personID, String name, String tel, String address, int status) {
        super(personID, name, tel, address, status);
        this.username = username;
        this.pwd = pwd;
        this.roleID = roleID;
    }
    public Account(String username, String pwd, String roleID, int personID, String name, String tel, String address) {
        super(personID, name, tel, address);
        this.username = username;
        this.pwd = pwd;
        this.roleID = roleID;
    }

    public Account(String username, String password, String role) {
        this.username = username;
        this.pwd = password;
        this.roleID = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public Date getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(Date dayCreate) {
        this.dayCreate = dayCreate;
    }
    
    
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Tạo một instance của MessageDigest với thuật toán SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Tạo một byte array từ chuỗi mật khẩu
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);

        // Hash chuỗi mật khẩu
        byte[] hash = digest.digest(bytes);

        // Convert hash thành chuỗi thập lục phân
        String hashedPassword = Base64.getEncoder().encodeToString(hash);
        return hashedPassword;
    }

    
    public static PwdValidStatus checkPwdValid(String pwd){
        if(pwd.length()<8) return PwdValidStatus.VERYSHORT;
        
        boolean hasNumber = false;
        for(char c : pwd.toCharArray()){
            if(Character.isDigit(c)){
                hasNumber = true;
                break;
            }                
        }
        if(!hasNumber) return PwdValidStatus.MISSINGNUMBER;
        
        boolean hasLetter = false;
        for(char c: pwd.toCharArray()){
            if(Character.isLetter(c)){
                hasLetter = true;
                break;
            }
        }
        if(!hasLetter) return PwdValidStatus.MISSINGLETTER;
        return PwdValidStatus.VALID;
    }
    public boolean compare(String username,String password,String role) throws Exception {
        
        if(!this.getUsername().equals(username)) {
            System.out.println(0.2);
                return false;
        }
        if(!this.getRoleID().equals(role)) {
            System.out.println(1.2);
                return false;
        }
        System.out.println("pass:"+password);
        if(!password.trim().equals("")) {
                return false;
        }
        System.out.println(3.2);
        return true;
    }   

}
