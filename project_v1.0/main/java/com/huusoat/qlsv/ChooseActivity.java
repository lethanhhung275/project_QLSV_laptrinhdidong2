package com.huusoat.qlsv;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class ChooseActivity extends AppCompatActivity {

    Button btnTK;
    EditText edtLop;
    String lop ;
    final String DATABASE_NAME = "QL.sqlite";
    SQLiteDatabase database;
    ListView listView;
    ArrayList<SinhVien> list;
    AdapterSinhVien adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        addContentView();

        edtLop = (EditText) findViewById(R.id.edtLop);
        btnTK = (Button) findViewById(R.id.btnTK);
       /* btnTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
            }
        }); */
        btnTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog= new Dialog(ChooseActivity.this) ;
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
                        readData();
                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt2.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                        readData();
                    }
                });

                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt3.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                        readData();
                    }
                });

                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt4.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                        readData();
                    }
                });

                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt5.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                        readData();
                    }
                });

                btn6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt6.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                        readData();
                    }
                });

                btn7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt7.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                        readData();
                    }
                });

                btn8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lop = txt8.getText().toString().trim();
                        edtLop.setText(lop);
                        dialog.cancel();
                        readData();
                    }
                });

                dialog.show();
            }

        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnTV:
                Intent intent = new Intent(ChooseActivity.this, MainActivity.class) ;
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    private void addContentView() {
        listView= (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new AdapterSinhVien(this, list);
        listView.setAdapter(adapter);

    }

    private void readData(){
        database= Database.initDatabase(this,DATABASE_NAME);
        if (edtLop.getText().length() != 0 )   {
            if (edtLop.getText().toString().equals("DCT14"))    {
                Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien Where Lop ='DCT14' ",null);
                list.clear();
                for (int i= 0; i< cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    int id = cursor.getInt(0);
                    String ten = cursor.getString(2);
                    String ma = cursor.getString(1);
                    byte[] anh = cursor.getBlob(3);
                    list.add(new SinhVien( id,ten, ma, anh));
                }
            }   else  if (edtLop.getText().toString().equals("DSA14"))  {
                Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien Where Lop ='DSA14' ",null);
                list.clear();
                for (int i= 0; i< cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    int id = cursor.getInt(0);
                    String ten = cursor.getString(2);
                    String ma = cursor.getString(1);
                    byte[] anh = cursor.getBlob(3);
                    list.add(new SinhVien( id,ten, ma, anh));
                }
            }  else  if (edtLop.getText().toString().equals("DCK14"))  {
                Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien Where Lop ='DCK14' ",null);
                list.clear();
                for (int i= 0; i< cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    int id = cursor.getInt(0);
                    String ten = cursor.getString(2);
                    String ma = cursor.getString(1);
                    byte[] anh = cursor.getBlob(3);
                    list.add(new SinhVien( id,ten, ma, anh));
                }
            }else  if (edtLop.getText().toString().equals("DSI14"))  {
                Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien Where Lop ='DSI14' ",null);
                list.clear();
                for (int i= 0; i< cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    int id = cursor.getInt(0);
                    String ten = cursor.getString(2);
                    String ma = cursor.getString(1);
                    byte[] anh = cursor.getBlob(3);
                    list.add(new SinhVien( id,ten, ma, anh));
                }
            }else  if (edtLop.getText().toString().equals("DSV14"))  {
                Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien Where Lop ='DSV14' ",null);
                list.clear();
                for (int i= 0; i< cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    int id = cursor.getInt(0);
                    String ten = cursor.getString(2);
                    String ma = cursor.getString(1);
                    byte[] anh = cursor.getBlob(3);
                    list.add(new SinhVien( id,ten, ma, anh));
                }
            }else  if (edtLop.getText().toString().equals("DSN14"))  {
                Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien Where Lop ='DSN14' ",null);
                list.clear();
                for (int i= 0; i< cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    int id = cursor.getInt(0);
                    String ten = cursor.getString(2);
                    String ma = cursor.getString(1);
                    byte[] anh = cursor.getBlob(3);
                    list.add(new SinhVien( id,ten, ma, anh));
                }
            }else  if (edtLop.getText().toString().equals("DKT14"))  {
                Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien Where Lop ='DKT14' ",null);
                list.clear();
                for (int i= 0; i< cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    int id = cursor.getInt(0);
                    String ten = cursor.getString(2);
                    String ma = cursor.getString(1);
                    byte[] anh = cursor.getBlob(3);
                    list.add(new SinhVien( id,ten, ma, anh));
                }
            }

        }
        else{
            Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien Where Lop ='' ",null);
            list.clear();
            for (int i= 0; i< cursor.getCount();i++){
                cursor.moveToPosition(i);
                int id = cursor.getInt(0);
                String ten = cursor.getString(2);
                String ma = cursor.getString(1);
                byte[] anh = cursor.getBlob(3);
                list.add(new SinhVien( id,ten, ma, anh));
            }

        }
        /*database= Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien Where Lop =? ",new String[]{lop});
        list.clear();
        for (int i= 0; i< cursor.getCount();i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(2);
            String ma = cursor.getString(1);
            byte[] anh = cursor.getBlob(3);
            list.add(new SinhVien( id,ten, ma, anh));
        }   */
        adapter.notifyDataSetChanged();
    }
}
