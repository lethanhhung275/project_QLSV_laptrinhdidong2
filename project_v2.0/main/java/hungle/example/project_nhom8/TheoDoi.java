package hungle.example.project_nhom8;

public class TheoDoi {
    private String maSV;
    private String ngay;
    private String note;

    public TheoDoi() {
    }

    public TheoDoi(String maSV, String ngay, String note) {
        this.maSV = maSV;
        this.ngay = ngay;
        this.note = note;
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
}
