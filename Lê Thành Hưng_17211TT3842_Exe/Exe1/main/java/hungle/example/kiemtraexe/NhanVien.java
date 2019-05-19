package hungle.example.kiemtraexe;

public class NhanVien {
    int icon;
    String sMaNV, sHoTen, sPhong, sSdt;
    boolean bGT;

    public NhanVien() {
    }

    public NhanVien(int icon, String sMaNV, String sHoTen, String sPhong, boolean bGT) {
        this.icon = icon;
        this.sMaNV = sMaNV;
        this.sHoTen = sHoTen;
        this.sPhong = sPhong;
        this.bGT = bGT;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getsMaNV() {
        return sMaNV;
    }

    public void setsMaNV(String sMaNV) {
        this.sMaNV = sMaNV;
    }

    public String getsHoTen() {
        return sHoTen;
    }

    public void setsHoTen(String sHoTen) {
        this.sHoTen = sHoTen;
    }

    public String getsPhong() {
        return sPhong;
    }

    public void setsPhong(String sPhong) {
        this.sPhong = sPhong;
    }

    public boolean isbGT() {
        return bGT;
    }

    public void setbGT(boolean bGT) {
        this.bGT = bGT;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "icon=" + icon +
                ", sMaNV='" + sMaNV + '\'' +
                ", sHoTen='" + sHoTen + '\'' +
                ", sPhong='" + sPhong + '\'' +
                ", bGT=" + bGT +
                '}';
    }
}
