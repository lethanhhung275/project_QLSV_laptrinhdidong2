package com.example.dem;

public class QLSV {
    int icon;
    String ten, mmsv;

    public QLSV() {
    }

    public QLSV(int icon, String ten, String mmsv) {
        this.icon = icon;
        this.ten = ten;
        this.mmsv = mmsv;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMmsv() {
        return mmsv;
    }

    public void setMmsv(String mmsv) {
        this.mmsv = mmsv;
    }

    @Override
    public String toString() {
        return "QLSV{" +
                "icon=" + icon +
                ", ten='" + ten + '\'' +
                ", mmsv='" + mmsv + '\'' +
                '}';
    }
}
