/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

import java.time.LocalDate;

/**
 *
 * @author Tính
 */
public class StatisticDTO {
    private int nam;
    private int thang;
    private int traDungHan;
    private double tienThu;
    private int tongPhieuMuon;
    private int soSachMat;

    public StatisticDTO() {
        super();
    }

    public StatisticDTO(int nam, int thang, int traDungHan, double tienThu, int tongPhieuMuon, int soSachMat) {
        this.nam = nam;
        this.thang = thang;
        this.traDungHan = traDungHan;
        this.tienThu = tienThu;
        this.tongPhieuMuon = tongPhieuMuon;
        this.soSachMat = soSachMat;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getTraDungHan() {
        return traDungHan;
    }

    public void setTraDungHan(int traDungHan) {
        this.traDungHan = traDungHan;
    }

    public double getTienThu() {
        return tienThu;
    }

    public void setTienThu(double tienThu) {
        this.tienThu = tienThu;
    }

    public int getTongPhieuMuon() {
        return tongPhieuMuon;
    }

    public void setTongPhieuMuon(int tongPhieuMuon) {
        this.tongPhieuMuon = tongPhieuMuon;
    }

    public int getSoSachMat() {
        return soSachMat;
    }

    public void setSoSachMat(int soSachMat) {
        this.soSachMat = soSachMat;
    }

}
