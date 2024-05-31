/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PermissionDAO;
import DAO.RoleDAO;
import DAO.RolePermissionDAO;
import DTO.entities.Permission;
import DTO.entities.Role;
import DTO.entities.RolePermission;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WIN 10
 */
public class RolePermissionBUS {
    protected static ArrayList<RolePermission> list;    
    protected static ArrayList<Permission> listPer;

    protected static RolePermissionDAO rolePermissionDAO;

    /**
     *
     */
    protected static RoleDAO roleDAO;
    protected static PermissionDAO permissionDAO;
    
    protected static Role role;
    protected static ArrayList<RolePermission> canAccess;
    private static int quantity = 0;

    public RolePermissionBUS() throws ClassNotFoundException, SQLException, IOException {
        rolePermissionDAO = new RolePermissionDAO();
        role = new Role();
        permissionDAO = new PermissionDAO();
        list = new ArrayList<>(rolePermissionDAO.read());
        quantity = list.size();
    }

    public static ArrayList<RolePermission> getList() {
        return list;
    }
    
    public static int getQuantity() {
        quantity = list.size();
        return quantity;
    }
    
    public void savePermissions(List<List<Object>> dataList, String role_ID) throws ClassNotFoundException, SQLException, IOException {
        ArrayList<String> perID = new ArrayList<>();
        listPer = permissionDAO.getList();
        int stt = 0;
        for (List<Object> rowData : dataList) {
            RolePermission permission = new RolePermission();
            permission.setIsDelete(0);
            permission.setRoleID(role_ID);
            permission.setPermissionID(listPer.get(stt).getPermissionID());
            permission.setPerAccess((boolean) rowData.get(0) ? 1 : 0);
            permission.setPerCreate((boolean) rowData.get(1) ? 1 : 0);
            permission.setPerView((boolean) rowData.get(2) ? 1 : 0);
            permission.setPerEdit((boolean) rowData.get(3) ? 1 : 0);
            permission.setPerDelete((boolean) rowData.get(4) ? 1 : 0);
            rolePermissionDAO.create(permission);
            stt++;
        }
    }
    public static ArrayList<RolePermission> canAccessForm(String roleID) throws NoSuchAlgorithmException {
        RolePermission temp;
        canAccess= new ArrayList<>();
        for (RolePermission _rolePermission : list) {
            temp = (RolePermission) _rolePermission;
            if(roleID.equals(temp.getRoleID())){
                canAccess.add(temp);
            }
        }
        return canAccess;
    }
    public ArrayList<Integer> hasPermission(int action_ID , ArrayList<RolePermission> canAccess) throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException{
        ArrayList<Integer> listpermission = new ArrayList<>();
        for (RolePermission permission : canAccess) {
            if (permission.getPermissionID() == action_ID){ 
                listpermission.add(permission.getPerAccess());
                listpermission.add(permission.getPerCreate());                
                listpermission.add(permission.getPerView());
                listpermission.add(permission.getPerEdit());
                listpermission.add(permission.getPerDelete());
            }
        }
        return listpermission;
    }    
    public void updateRolePermissions(List<List<Object>> dataList, String roleName) throws ClassNotFoundException, SQLException, IOException {
        roleDAO = new RoleDAO();
        role = roleDAO.getRole(roleName);
        listPer = permissionDAO.getList();
        int stt = 0;
        for (List<Object> rowData : dataList) {
              RolePermission per =new RolePermission();
              per.setRoleID(role.getRoleID());
              per.setPermissionID(listPer.get(stt).getPermissionID());
              per.setPerAccess((boolean)rowData.get(0)?1:0);              
              per.setPerCreate((boolean)rowData.get(1)?1:0);
              per.setPerView((boolean)rowData.get(2)?1:0);
              per.setPerEdit((boolean)rowData.get(3)?1:0);
              per.setPerDelete((boolean)rowData.get(4)?1:0);
              rolePermissionDAO.update(per);
              stt++;
        }
    }
    public boolean hasPerAccess(String roleID, int action_ID){
        for(RolePermission permission : list){
            if(permission.getRoleID().equals(roleID) && permission.getPermissionID() == (action_ID)){
                return permission.getPerAccess() == 1;
            }
        }
        return false;
    }
    public boolean hasPerCreate(String roleID, int action_ID){
        for(RolePermission permission : list){
            if(permission.getRoleID().equals(roleID) && permission.getPermissionID() == (action_ID)){
                return permission.getPerCreate() == 1;
            }
        }
        return false;
    }
    public boolean hasPerView(String roleID, int action_ID){
        for(RolePermission permission : list){
            if(permission.getRoleID().equals(roleID) && permission.getPermissionID() == (action_ID)){
                return permission.getPerView() == 1;
            }
        }
        return false;
    }
    public boolean hasPerEdit(String roleID, int action_ID){
        for(RolePermission permission : list){
            if(permission.getRoleID().equals(roleID) && permission.getPermissionID() == (action_ID)){
                return permission.getPerEdit() == 1;
            }
        }
        return false;
    }
    public boolean hasPerDelete(String roleID, int action_ID){
        for(RolePermission permission : list){
            if(permission.getRoleID().equals(roleID) && permission.getPermissionID() == (action_ID)){
                return permission.getPerDelete()== 1;
            }
        }
        return false;
    }
}
