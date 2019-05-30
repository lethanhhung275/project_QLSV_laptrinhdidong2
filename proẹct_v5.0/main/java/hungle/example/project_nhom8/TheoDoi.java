package hungle.example.project_nhom8;

public class TheoDoi {
    private int img;
    private String maSV;
    private String ngay;
    private String note;
    private String tinhHinh;

    public TheoDoi() {
    }

    public TheoDoi(int img, String maSV, String ngay, String note, String tinhHinh) {
        this.img = img;
        this.maSV = maSV;
        this.ngay = ngay;
        this.note = note;
        this.tinhHinh = tinhHinh;
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

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTinhHinh() {
        return tinhHinh;
    }

    public void setTinhHinh(String tinhHinh) {
        this.tinhHinh = tinhHinh;
    }

    @Override
    public String   toString() {
        return "TheoDoi{" +
                "img=" + img +
                ", maSV='" + maSV + '\'' +
                ", ngay='" + ngay + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
