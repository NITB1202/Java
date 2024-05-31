/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

import java.util.Random;

/**
 *
 * @author WIN 10
 */
public class Role {
    protected String roleID;
    protected String roleName;
    protected int isDeleted;

    
    public Role() {
    }

    public Role(String roleID, String roleName, int isDeleted) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.isDeleted= isDeleted;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
    public static String generateID() {
        Random random = new Random();
        // Tạo một mảng chứa các chữ cái
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        // Tạo một StringBuilder để xây dựng ID
        StringBuilder idBuilder = new StringBuilder();
        // Tạo ngẫu nhiên hai chữ cái
        for (int i = 0; i < 2; i++) {
            char randomLetter = letters[random.nextInt(letters.length)];
            idBuilder.append(randomLetter);
        }

        // Trả về ID đã tạo
        return idBuilder.toString();
    }
}
