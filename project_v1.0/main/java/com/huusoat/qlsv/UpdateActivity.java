package com.huusoat.qlsv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateActivity extends AppCompatActivity {
    EditText edtMa,edtTen,edtGioiTinh,edtNgaySinh,edtDiaChi,edtKhoaHoc,edtKhoa,edtLop,edtNganh,edtBacDT,edtDiem;
    ImageView imgHinhDaiDien;
    Button btnTrơve,btnLuu,btnHuy;
    ImageButton btnLop,btnKhoa,btnBacDT,btnGT;
    String lop;
    final int REQUEST_CHOOSE_PHOTO = 123;
    final int REQUEST_TAKE_PHOTO = 321;
    int id = -1;
    final String DATABASE_NAME = "QL.sqlite";
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        addControl();
        intUI();
        addEvent();
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
        btnGT = (ImageButton) findViewById(R.id.btnGT);
        btnLop = (ImageButton) findViewById(R.id.btnLop);
        btnKhoa = (ImageButton) findViewById(R.id.btnKhoa);
        btnBacDT = (ImageButton) findViewById(R.id.btnBacDT);
        edtTen = (EditText) findViewById(R.id.edtTen);
        edtMa = (EditText) findViewById(R.id.edtMA);
        edtKhoaHoc = (EditText) findViewById(R.id.edtKhoaHoc);
        edtKhoa = (EditText) findViewById(R.id.edtKhoa);
        edtNgaySinh = (EditText) findViewById(R.id.edtNgaySinh);
        edtDiaChi = (EditText) findViewById(R.id.edtDiaChi);
        edtLop = (EditText) findViewById(R.id.edtLop);
        btnLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog= new Dialog(UpdateActivity.this) ;
                dialog.setTitle("abc");
                //dialog.setCancelable(false);
                dialog.setContentView(R.layout.activity_lop);
                final EditText txt1 = (EditText)dialog.findViewById(R.id.editText);
                final EditText txt2 = (EditText)dialog.findViewById(R.id.editText2);
                final EditText txt3 = (EditText)dialog.findViewById(R.id.editText3);
                final EditText txt4 = (EditText)dialog.findViewById(R.id.editText4);
                final EditText txt5 = (EditText)dialog.findViewById(R.id.editText5);
                final EditText txt6 = (EditText)dialog.findViewById(R.id.editText6);
                final EditText txt7 = (EditText)dialog.findViewById(R.id.editText7);
                final EditText txt8 = (EditText)dialog.findViewById(R.id.editText8);
                Button btn1 = (Button)dialog.findViewById(R.id.btn1);
                Button btn2 = (Button)dialog.findViewById(R.id.btn2);
                Button btn3 = (Button)dialog.findViewById(R.id.btn3);
                Button btn4 = (Button)dialog.findViewById(R.id.btn4);
                Button btn5 = (Button)dialog.findViewById(R.id.btn5);
                Button btn6 = (Button)dialog.findViewById(R.id.btn6);
                Button btn7 = (Button)dialog.findViewById(R.id.btn7);
                Button btn8 = (Button)dialog.findViewById(R.id.btn8);


                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt1.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt2.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt3.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                    }
                });

                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt4.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                    }
                });

                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt5.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                    }
                });

                btn6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt6.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                    }
                });

                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt7.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                    }
                });

                btn8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt8.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
        btnKhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog= new Dialog(UpdateActivity.this) ;
                dialog.setTitle("abc");
                //dialog.setCancelable(false);
                dialog.setContentView(R.layout.khoa);
                final EditText txt1 = (EditText)dialog.findViewById(R.id.editText);
                final EditText txt2 = (EditText)dialog.findViewById(R.id.editText2);
                final EditText txt3 = (EditText)dialog.findViewById(R.id.editText3);
                final EditText txt4 = (EditText)dialog.findViewById(R.id.editText4);
                final EditText txt5 = (EditText)dialog.findViewById(R.id.editText5);
                final EditText txt6 = (EditText)dialog.findViewById(R.id.editText6);
                Button btn1 = (Button)dialog.findViewById(R.id.btn1);
                Button btn2 = (Button)dialog.findViewById(R.id.btn2);
                Button btn3 = (Button)dialog.findViewById(R.id.btn3);
                Button btn4 = (Button)dialog.findViewById(R.id.btn4);
                Button btn5 = (Button)dialog.findViewById(R.id.btn5);
                Button btn6 = (Button)dialog.findViewById(R.id.btn6);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt1.getText().toString().trim();
                        edtKhoa.setText(lop);
                        dialog.cancel();
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt2.getText().toString().trim();
                        edtKhoa.setText(lop);
                        dialog.cancel();
                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt3.getText().toString().trim();
                        edtKhoa.setText(lop);
                        dialog.cancel();
                    }
                });

                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt4.getText().toString().trim();
                        edtKhoa.setText(lop);
                        dialog.cancel();
                    }
                });

                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt5.getText().toString().trim();
                        edtKhoa.setText(lop);
                        dialog.cancel();
                    }
                });

                btn6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt6.getText().toString().trim();
                        edtKhoa.setText(lop);
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        btnBacDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog= new Dialog(UpdateActivity.this) ;
                dialog.setTitle("abc");
                //dialog.setCancelable(false);
                dialog.setContentView(R.layout.bachoc);
                final EditText txt1 = (EditText)dialog.findViewById(R.id.editText);
                final EditText txt2 = (EditText)dialog.findViewById(R.id.editText2);
                final EditText txt3 = (EditText)dialog.findViewById(R.id.editText3);
                Button btn1 = (Button)dialog.findViewById(R.id.btn1);
                Button btn2 = (Button)dialog.findViewById(R.id.btn2);
                Button btn3 = (Button)dialog.findViewById(R.id.btn3);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt1.getText().toString().trim();
                        edtBacDT.setText(lop);
                        dialog.cancel();
                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt2.getText().toString().trim();
                        edtBacDT.setText(lop);
                        dialog.cancel();
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt3.getText().toString().trim();
                        edtBacDT.setText(lop);
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
        btnGT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog= new Dialog(UpdateActivity.this) ;
                dialog.setTitle("abc");
                //dialog.setCancelable(false);
                dialog.setContentView(R.layout.gioitinh);
                final EditText txt1 = (EditText)dialog.findViewById(R.id.editText);
                final EditText txt2 = (EditText)dialog.findViewById(R.id.editText2);
                Button btn1 = (Button)dialog.findViewById(R.id.btnNam);
                Button btn2 = (Button)dialog.findViewById(R.id.btnNu);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt1.getText().toString().trim();
                        edtGioiTinh.setText(lop);
                        dialog.cancel();
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt2.getText().toString().trim();
                        edtGioiTinh.setText(lop);
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        edtBacDT = (EditText) findViewById(R.id.edtBacDT);
        edtDiem= (EditText) findViewById(R.id.edtDiem);
        edtNganh = (EditText) findViewById(R.id.edtNganh);
        edtGioiTinh = (EditText) findViewById(R.id.edtGioiTinh);

        btnLuu = (Button) findViewById(R.id.btnLuu);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        imgHinhDaiDien = (ImageView) findViewById(R.id.imgHinhDaiDien);
        btnTrơve = (Button) findViewById(R.id.btnTroVe);
        btnTrơve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class) ;
                startActivity(intent);
            }
        });

    }
    private  void addEvent(){
        imgHinhDaiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn chọn hình hay chụp hình?");
                builder.setPositiveButton("Chụp hình", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        takePhoto();
                    }
                });
                builder.setNegativeButton("Chọn hình", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choosePhoto();
                    }
                });
                builder.show();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }
    private  void takePhoto (){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }
    private  void choosePhoto (){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*") ;
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CHOOSE_PHOTO){

                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgHinhDaiDien.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }     else if (requestCode == REQUEST_TAKE_PHOTO){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgHinhDaiDien.setImageBitmap(bitmap);
            }
        }

    }

    private void update(){
        String ten = edtTen.getText().toString();
        String ma= edtMa.getText().toString();
        String gioitinh= edtGioiTinh.getText().toString();
        String ngaysinh= edtNgaySinh.getText().toString();
        String diachi= edtDiaChi.getText().toString();
        String khoahoc= edtKhoaHoc.getText().toString();
        String khoa= edtKhoa.getText().toString();
        String lop= edtLop.getText().toString();
        String nganh= edtNganh.getText().toString();
        String bacdt= edtBacDT.getText().toString();
        String diemtb= edtDiem.getText().toString();

        byte[] anh = getByteArrayFromImageView(imgHinhDaiDien);
        ContentValues contentValues = new ContentValues();
        contentValues.put("MaSV" ,ma);
        contentValues.put("TenSV" ,ten);
        contentValues.put("Anh" ,anh);
        contentValues.put("GioiTinh" ,gioitinh);
        contentValues.put("NgaySinh" ,ngaysinh);
        contentValues.put("DiaChi" ,diachi);
        contentValues.put("KhoaHoc" ,khoahoc);
        contentValues.put("Khoa" ,khoa);
        contentValues.put("Lop" ,lop);
        contentValues.put("Nganh" ,nganh);
        contentValues.put("BacDT" ,bacdt);
        contentValues.put("DiemTB" ,diemtb);


        database= Database.initDatabase(this,DATABASE_NAME);
        database.update("SinhVien", contentValues,"id = ?",new String[]{id + ""}) ;
        Intent intent = new Intent( this , MainActivity.class) ;
        startActivity(intent);
    }
    private void cancel(){
        Intent intent = new Intent( this , MainActivity.class) ;
        startActivity(intent);
    }
    private byte[] getByteArrayFromImageView(ImageView imgv){
        BitmapDrawable drawable= (BitmapDrawable) imgv.getDrawable();
        Bitmap bmg = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmg.compress(Bitmap.CompressFormat.PNG , 100,stream);
        byte[] byteArray = stream.toByteArray();
        return  byteArray;
    }
}
