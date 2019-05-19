package com.huusoat.qlsv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

import java.util.ArrayList;

public class LoadActivity extends AppCompatActivity {
   EditText edtMa,edtTen,edtGioiTinh,edtNgaySinh,edtDiaChi,edtKhoaHoc,edtKhoa,edtLop,edtNganh,edtBacDT,edtDiem;
    ImageView imgHinhDaiDien;
    Button btnTrơve;
    final String DATABASE_NAME = "QL.sqlite";
    SQLiteDatabase database;
    int id = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        addControl();
        intUI();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnSua:
                Intent intent = new Intent(LoadActivity.this,UpdateActivity.class);
                intent.putExtra("ID",id );
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void intUI() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID",-1)  ;
        database= Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM SinhVien WHERE ID = ?",new  String[]{id + ""});
        cursor.moveToFirst();

        String ma = cursor.getString(1);
        String ten = cursor.getString(2);
        String gioitinh = cursor.getString(4);
        String ngaysinh = cursor.getString(5);
        String diachi = cursor.getString(6);
        String khoahoc = cursor.getString(7);
        String khoa = cursor.getString(8);
        String lop = cursor.getString(9);
        String nganh = cursor.getString(10);
        String batDT = cursor.getString(11);
        String diem = cursor.getString(12);
        byte[] anh = cursor.getBlob(3);

        Bitmap bitmap = BitmapFactory.decodeByteArray(anh,0,anh.length) ;
        imgHinhDaiDien.setImageBitmap(bitmap);

        edtMa.setText(ma);
        edtTen.setText(ten);
       edtNgaySinh.setText(ngaysinh);
        edtDiaChi .setText(diachi);
        edtLop.setText(lop);
      edtGioiTinh.setText(gioitinh);
       edtKhoa .setText(khoa);
        edtDiem.setText(diem);
        edtLop.setText(lop);
        edtNganh.setText(nganh);
          edtDiaChi .setText(diachi);
       edtKhoaHoc.setText(khoahoc);
       edtBacDT.setText(batDT);
    }
    private void addControl() {

        edtTen = (EditText) findViewById(R.id.edtTen);
        edtMa = (EditText) findViewById(R.id.edtMA);
        edtKhoaHoc = (EditText) findViewById(R.id.edtKhoaHoc);
        edtKhoa = (EditText) findViewById(R.id.edtKhoa);
        edtNgaySinh = (EditText) findViewById(R.id.edtNgaySinh);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtLop = (EditText) findViewById(R.id.edtLop);
        edtBacDT = (EditText) findViewById(R.id.edtBacDT);
        edtDiem= (EditText) findViewById(R.id.edtDiem);
        edtNganh = (EditText) findViewById(R.id.edtNganh);
        edtGioiTinh = (EditText) findViewById(R.id.edtGioiTinh);

        imgHinhDaiDien = (ImageView) findViewById(R.id.imgHinhDaiDien);
       /* btnChon = (Button) findViewById(R.id.btnChon);
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadActivity.this,UpdateActivity.class);
                intent.putExtra("ID",id );
                startActivity(intent);
            }
        });  */
        btnTrơve = (Button) findViewById(R.id.btnSua);
        btnTrơve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadActivity.this, MainActivity.class) ;
                startActivity(intent);
            }
        });
    }
}
