package hungle.example.project_nhom8;

public class SinhVien {
    private int img;
    private String maSV, tenSV, gioiTinh, ngaySinh, diaChi, khoa, namVao, namRa, bacDT, sdt, email;

    public SinhVien() {
    }

    public SinhVien(int img, String maSV, String tenSV, String gioiTinh, String ngaySinh, String diaChi, String khoa, String namVao, String namRa, String bacDT, String sdt, String email) {
        this.img = img;
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.khoa = khoa;
        this.namVao = namVao;
        this.namRa = namRa;
        this.bacDT = bacDT;
        this.sdt = sdt;
        this.email = email;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getNamVao() {
        return namVao;
    }

    public void setNamVao(String namVao) {
        this.namVao = namVao;
    }

    public String getNamRa() {
        return namRa;
    }

    public void setNamRa(String namRa) {
        this.namRa = namRa;
    }

    public String getBacDT() {
        return bacDT;
    }

    public void setBacDT(String bacDT) {
        this.bacDT = bacDT;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
