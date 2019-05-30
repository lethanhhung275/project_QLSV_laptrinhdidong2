package hungle.example.project_nhom8;

public class Khoa {
    private int img;
    private String maKhoa, tenKhoa;

    public Khoa() {
    }

    public Khoa(int img, String maKhoa, String tenKhoa) {
        this.img = img;
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }
}
