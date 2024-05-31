package DTO.entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author WIN 10
 */
public class RolePermission {
    protected String roleID;
    protected int permissionID;
    protected int perAccess;
    protected int perCreate;
    protected int perView;
    protected int perEdit;
    protected int perDelete;
    protected int isDelete = 0;

    public RolePermission(String roleID, int permissionID) {
        this.roleID = roleID;
        this.permissionID = permissionID;
        this.perAccess = perAccess;
        this.perCreate = perCreate;
        this.perView = perView;
        this.perEdit = perEdit;
        this.perDelete = perDelete;
        
    }

    public RolePermission() {
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public int getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }

    public int getPerAccess() {
        return perAccess;
    }

    public void setPerAccess(int perAccess) {
        this.perAccess = perAccess;
    }

    public int getPerCreate() {
        return perCreate;
    }

    public void setPerCreate(int perCreate) {
        this.perCreate = perCreate;
    }

    public int getPerView() {
        return perView;
    }

    public void setPerView(int perView) {
        this.perView = perView;
    }

    public int getPerEdit() {
        return perEdit;
    }

    public void setPerEdit(int perEdit) {
        this.perEdit = perEdit;
    }

    public int getPerDelete() {
        return perDelete;
    }

    public void setPerDelete(int perDelete) {
        this.perDelete = perDelete;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
    
}
