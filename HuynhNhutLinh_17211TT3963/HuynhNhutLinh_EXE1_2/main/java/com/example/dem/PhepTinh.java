package com.example.dem;

public class PhepTinh {
    int icon;
    String soA, soB, pt;

    public PhepTinh() {
    }

    public PhepTinh(int icon, String soA, String soB, String pt, String kq) {
        this.icon = icon;
        this.soA = soA;
        this.soB = soB;
        this.pt = pt;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getSoA() {
        return soA;
    }

    public void setSoA(String soA) {
        this.soA = soA;
    }

    public String getSoB() {
        return soB;
    }

    public void setSoB(String soB) {
        this.soB = soB;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }
}
