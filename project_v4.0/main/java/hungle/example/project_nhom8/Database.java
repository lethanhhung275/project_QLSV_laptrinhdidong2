package hungle.example.project_nhom8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static String DB_NAME = "dbQLSV.db";
    private static int DB_VERSION = 1;

    //Define table SinhVien
    private static final String TB_SINHVIENS = "tbSinhVien";
    private static final String COL_SINHVIEN_ID = "sinhvien_id";
    private static final String COL_SINHVIEN_MA = "sinhvien_ma";
    private static final String COL_SINHVIEN_TEN = "sinhvien_ten";
    private static final String COL_SINHVIEN_GIOITINH = "sinhvien_gioitinh";
    private static final String COL_SINHVIEN_NGAYSINH = "sinhvien_ngaysinh";
    private static final String COL_SINHVIEN_DIACHI = "sinhvien_diachi";
    private static final String COL_SINHVIEN_MAKHOA = "sinhvien_makhoa";
    private static final String COL_SINHVIEN_NAMVAO = "sinhvien_namvao";
    private static final String COL_SINHVIEN_NAMRA = "sinhvien_namra";
    private static final String COL_SINHVIEN_BACDT = "sinhvien_bacdt";
    private static final String COL_SINHVIEN_SDT = "sinhvien_sdt";
    private static final String COL_SINHVIEN_EMAIL = "sinhvien_email";

    //Define table Khoa
    private static final String TB_KHOAS = "tbKhoa";
    private static final String COL_KHOA_ID = "khoa_id";
    private static final String COL_KHOA_MA = "khoa_ma";
    private static final String COL_KHOA_TEN = "khoa_ten";

    //Define table TheoDoi
    private static final String TB_THEODOIS = "tbTheoDoi";
    private static final String COL_THEODOI_ID = "theodoi_id";
    private static final String COL_THEODOI_MASV = "theodoi_masv";
    private static final String COL_THEODOI_NGAY = "theodoi_ngay";
    private static final String COL_THEODOI_NOTE = "theodoi_note";

    public Database(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_SINHVIENS);
        db.execSQL("DROP TABLE IF EXISTS " + TB_KHOAS);
        db.execSQL("DROP TABLE IF EXISTS " + TB_THEODOIS);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String scriptTBSinhViens = "CREATE TABLE " + TB_SINHVIENS + "(" +
                COL_SINHVIEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_SINHVIEN_MA + " TEXT, " +
                COL_SINHVIEN_TEN + " TEXT, " +
                COL_SINHVIEN_GIOITINH + " TEXT, " +
                COL_SINHVIEN_NGAYSINH + " TEXT, " +
                COL_SINHVIEN_DIACHI + " TEXT, " +
                COL_SINHVIEN_MAKHOA + " TEXT, " +
                COL_SINHVIEN_NAMVAO + " TEXT, " +
                COL_SINHVIEN_NAMRA + " TEXT, " +
                COL_SINHVIEN_BACDT + " TEXT, " +
                COL_SINHVIEN_SDT + " TEXT, " +
                COL_SINHVIEN_EMAIL + " TEXT)";

        String scriptTBKhoas = "CREATE TABLE " + TB_KHOAS + "(" +
                COL_KHOA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_KHOA_MA + " TEXT, " +
                COL_KHOA_TEN + " TEXT)";

        String scriptTBTheoDois = "CREATE TABLE " + TB_THEODOIS + "(" +
                COL_THEODOI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COL_THEODOI_MASV + " TEXT, " +
                COL_THEODOI_NGAY+ " TEXT, " +
                COL_THEODOI_NOTE + " TEXT)";
        //Execute script
        db.execSQL(scriptTBSinhViens);
        db.execSQL(scriptTBKhoas);
        db.execSQL(scriptTBTheoDois);
    }

    public void getSinhVien(ArrayList<SinhVien> sinhViens)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_SINHVIENS, new String[]{COL_SINHVIEN_MA, COL_SINHVIEN_TEN, COL_SINHVIEN_GIOITINH, COL_SINHVIEN_NGAYSINH, COL_SINHVIEN_DIACHI, COL_SINHVIEN_MAKHOA, COL_SINHVIEN_NAMVAO, COL_SINHVIEN_NAMRA, COL_SINHVIEN_BACDT, COL_SINHVIEN_SDT, COL_SINHVIEN_EMAIL}, null,null,null, null, null);
        if(cursor.moveToFirst()) {
            do {
                SinhVien sv = new SinhVien();
                sv.setMaSV(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_MA)));
                sv.setTenSV(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_TEN)));
                sv.setGioiTinh(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_GIOITINH)));
                sv.setNgaySinh(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NGAYSINH)));
                sv.setDiaChi(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_DIACHI)));
                sv.setKhoa(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_MAKHOA)));
                sv.setNamVao(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NAMVAO)));
                sv.setNamRa(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NAMRA)));
                sv.setBacDT(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_BACDT)));
                sv.setSdt(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_SDT)));
                sv.setEmail(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_EMAIL)));

                sinhViens.add(sv);
            } while (cursor.moveToNext());
        }
    }

    public void getAllSinhVien(ArrayList<SinhVien> sinhViens) {
            SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from "+TB_SINHVIENS;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                SinhVien sv = new SinhVien();
                sv.setMaSV(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_MA)));
                sv.setTenSV(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_TEN)));
                sv.setGioiTinh(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_GIOITINH)));
                sv.setNgaySinh(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NGAYSINH)));
                sv.setDiaChi(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_DIACHI)));
                sv.setKhoa(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_MAKHOA)));
                sv.setNamVao(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NAMVAO)));
                sv.setNamRa(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NAMRA)));
                sv.setBacDT(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_BACDT)));
                sv.setSdt(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_SDT)));
                sv.setEmail(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_EMAIL)));

                sinhViens.add(sv);
            } while (cursor.moveToNext());
        }

    }

    public void getAllMaSV(ArrayList<String> maSVs) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select sinhvien_ma from "+TB_SINHVIENS;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                String masv;
                masv = cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_MA));

                maSVs.add(masv);
            } while (cursor.moveToNext());
        }

    }

    public void saveSV(SinhVien sv)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_SINHVIEN_MA, sv.getMaSV());
        values.put(COL_SINHVIEN_TEN, sv.getTenSV());
        values.put(COL_SINHVIEN_GIOITINH, sv.getGioiTinh());
        values.put(COL_SINHVIEN_NGAYSINH, sv.getNgaySinh());
        values.put(COL_SINHVIEN_DIACHI, sv.getDiaChi());
        values.put(COL_SINHVIEN_MAKHOA, sv.getKhoa());
        values.put(COL_SINHVIEN_NAMVAO, sv.getNamVao());
        values.put(COL_SINHVIEN_NAMRA, sv.getNamRa());
        values.put(COL_SINHVIEN_BACDT, sv.getBacDT());
        values.put(COL_SINHVIEN_SDT, sv.getSdt());
        values.put(COL_SINHVIEN_EMAIL, sv.getEmail());

        db.insert(TB_SINHVIENS, null, values);
        db.close();
    }

    public void deleteSV(String maSV)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM tbSinhVien WHERE sinhvien_ma=" + maSV;
        db.execSQL(query);
    }

    public void updateSV(SinhVien sv)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Update "+ TB_SINHVIENS +" set ";
        sql += COL_SINHVIEN_TEN +" = '"+ sv.getTenSV()+"' , ";
        sql += COL_SINHVIEN_GIOITINH +" = '"+ sv.getGioiTinh()+"' , ";
        sql += COL_SINHVIEN_NGAYSINH +" = '"+ sv.getNgaySinh()+"' , ";
        sql += COL_SINHVIEN_DIACHI +" = '"+ sv.getDiaChi()+"' , ";
        sql += COL_SINHVIEN_MAKHOA +" = '"+ sv.getKhoa()+"' , ";
        sql += COL_SINHVIEN_NAMVAO +" = '"+ sv.getNamVao()+"' , ";
        sql += COL_SINHVIEN_NAMRA +" = '"+ sv.getNamRa()+"' , ";
        sql += COL_SINHVIEN_BACDT +" = '"+ sv.getBacDT()+"' , ";
        sql += COL_SINHVIEN_SDT +" = '"+ sv.getSdt()+"' , ";
        sql += COL_SINHVIEN_EMAIL +" = '"+ sv.getEmail()+"'  ";
        sql += "  WHERE "+ COL_SINHVIEN_MA +" = "+ sv.getMaSV();

        db.execSQL(sql);
    }

    public void findMaSV(String maSV, ArrayList<SinhVien> sinhViens)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from "+TB_SINHVIENS + " where " + COL_SINHVIEN_MA + " like '%" + maSV + "%'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                SinhVien sv = new SinhVien();
                sv.setMaSV(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_MA)));
                sv.setTenSV(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_TEN)));
                sv.setGioiTinh(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_GIOITINH)));
                sv.setNgaySinh(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NGAYSINH)));
                sv.setDiaChi(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_DIACHI)));
                sv.setKhoa(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_MAKHOA)));
                sv.setNamVao(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NAMVAO)));
                sv.setNamRa(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NAMRA)));
                sv.setBacDT(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_BACDT)));
                sv.setSdt(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_SDT)));
                sv.setEmail(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_EMAIL)));

                sinhViens.add(sv);
            } while (cursor.moveToNext());
        }
    }

    public void findKhoaSV(String k, ArrayList<SinhVien> sinhViens) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from " + TB_SINHVIENS + " where " + COL_SINHVIEN_MAKHOA + " like '%" + k + "%'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            do {
                SinhVien sv = new SinhVien();
                sv.setMaSV(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_MA)));
                sv.setTenSV(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_TEN)));
                sv.setGioiTinh(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_GIOITINH)));
                sv.setNgaySinh(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NGAYSINH)));
                sv.setDiaChi(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_DIACHI)));
                sv.setKhoa(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_MAKHOA)));
                sv.setNamVao(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NAMVAO)));
                sv.setNamRa(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_NAMRA)));
                sv.setBacDT(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_BACDT)));
                sv.setSdt(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_SDT)));
                sv.setEmail(cursor.getString(cursor.getColumnIndex(COL_SINHVIEN_EMAIL)));

                sinhViens.add(sv);
            } while (cursor.moveToNext());
        }
    }


    public void getKhoa(ArrayList<Khoa> khoas)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_KHOAS, new String[]{COL_KHOA_MA, COL_KHOA_TEN}, null,null,null, null, null);
        if(cursor.moveToFirst()) {
            do {
                Khoa khoa = new Khoa();
                khoa.setMaKhoa(cursor.getString(cursor.getColumnIndex(COL_KHOA_MA)));
                khoa.setTenKhoa(cursor.getString(cursor.getColumnIndex(COL_KHOA_TEN)));

                khoas.add(khoa);
            } while (cursor.moveToNext());
        }
    }

    public void getAllKhoa(ArrayList<Khoa> khoas) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from "+TB_KHOAS;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                Khoa khoa = new Khoa();
                khoa.setMaKhoa(cursor.getString(cursor.getColumnIndex(COL_KHOA_MA)));
                khoa.setTenKhoa(cursor.getString(cursor.getColumnIndex(COL_KHOA_TEN)));

                khoas.add(khoa);
            } while (cursor.moveToNext());
        }

    }

    public void getAllMaKhoa(ArrayList<String> makhoas) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select khoa_ma from "+TB_KHOAS;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                String makhoa;
                makhoa = cursor.getString(cursor.getColumnIndex(COL_KHOA_MA));

                makhoas.add(makhoa);
            } while (cursor.moveToNext());
        }
    }

    public void getAllTenKhoa(ArrayList<String> tenKhoas) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select khoa_ten from "+TB_KHOAS;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                String tenkhoa;
                tenkhoa = cursor.getString(cursor.getColumnIndex(COL_KHOA_TEN));

                tenKhoas.add(tenkhoa);
            } while (cursor.moveToNext());
        }
    }

    public void saveKhoa(Khoa khoa)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_KHOA_MA, khoa.getMaKhoa());
        values.put(COL_KHOA_TEN, khoa.getTenKhoa());

        db.insert(TB_KHOAS, null, values);
        db.close();
    }

    public void deleteKhoa(String maKhoa)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM tbKhoa WHERE khoa_ma=" + maKhoa;
        db.execSQL(query);
    }

    public void updateKhoa(Khoa khoa)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Update "+ TB_KHOAS +" set ";
        sql += COL_KHOA_TEN +" = '"+ khoa.getTenKhoa()+"'  ";
        sql += "  WHERE "+ COL_KHOA_MA +" = "+ khoa.getMaKhoa();

        db.execSQL(sql);
    }



    public void getTheoDoi(ArrayList<TheoDoi> theoDois)
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(TB_THEODOIS, new String[]{COL_THEODOI_MASV, COL_THEODOI_NGAY, COL_THEODOI_NGAY}, null,null,null, null, null);
        if(cursor.moveToFirst()) {
            do {
                TheoDoi theoDoi = new TheoDoi();
                theoDoi.setMaSV(cursor.getString(cursor.getColumnIndex(COL_THEODOI_MASV)));
                theoDoi.setNgay(cursor.getString(cursor.getColumnIndex(COL_THEODOI_NGAY)));
                theoDoi.setNote(cursor.getString(cursor.getColumnIndex(COL_THEODOI_NOTE)));

                theoDois.add(theoDoi);
            } while (cursor.moveToNext());
        }
    }

    public void getAllTheoDoiSV(ArrayList<TheoDoi> theoDois, String masv) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from "+TB_THEODOIS + " where theodoi_masv=" + masv;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                TheoDoi theoDoi = new TheoDoi();
                theoDoi.setMaSV(cursor.getString(cursor.getColumnIndex(COL_THEODOI_MASV)));
                theoDoi.setNgay(cursor.getString(cursor.getColumnIndex(COL_THEODOI_NGAY)));
                theoDoi.setNote(cursor.getString(cursor.getColumnIndex(COL_THEODOI_NOTE)));

                theoDois.add(theoDoi);
            } while (cursor.moveToNext());
        }
    }

    public void saveTheoDoi(TheoDoi theoDoi)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_THEODOI_MASV, theoDoi.getMaSV());
        values.put(COL_THEODOI_NGAY, theoDoi.getNgay());
        values.put(COL_THEODOI_NOTE, theoDoi.getNote());

        db.insert(TB_THEODOIS, null, values);
        db.close();
    }

    public void deleteNote(String maSV)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM tbTheoDoi WHERE theodoi_masv=" + maSV;
        db.execSQL(query);
    }
}
