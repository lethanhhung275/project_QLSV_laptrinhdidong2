package com.huusoat.qlsv;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSinhVien extends BaseAdapter {
    Activity context;
    ArrayList<SinhVien> list;
    ImageButton btnXoa ;
    public AdapterSinhVien(Activity context, ArrayList<SinhVien> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_row,null);
        ImageView imgHinhDaiDien = (ImageView) row.findViewById(R.id.imgHinhDaiDien);
        TextView txtID = (TextView) row.findViewById(R.id.txtID);
        TextView txtTen = (TextView) row.findViewById(R.id.txtTen);
        TextView txtMa = (TextView) row.findViewById(R.id.txtMa);
        btnXoa = (ImageButton)row.findViewById(R.id.btnXoa);
        TextView txt1 = (TextView) row.findViewById(R.id.txt1);
        TextView txt2 = (TextView) row.findViewById(R.id.txt2);
        final SinhVien sinhVien = list.get(position);
        txtID.setText(sinhVien.id + "");
        txtMa.setText(sinhVien.ma);
        txtTen.setText(sinhVien.ten );


        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(sinhVien.anh , 0 , sinhVien.anh.length);
        imgHinhDaiDien.setImageBitmap(bmHinhDaiDien);

        imgHinhDaiDien .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,LoadActivity.class);
                intent.putExtra("ID", sinhVien.id );
                context.startActivity(intent);
            }
        });
        txtTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,LoadActivity.class);
                intent.putExtra("ID", sinhVien.id );
                context.startActivity(intent);
            }
        });
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,LoadActivity.class);
                intent.putExtra("ID", sinhVien.id );
                context.startActivity(intent);
            }
        });
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,LoadActivity.class);
                intent.putExtra("ID", sinhVien.id );
                context.startActivity(intent);

            }
        });

        txtMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete)  ;
                builder.setTitle("Xac nhan xoa ");
                builder.setMessage("Ban co chac muon xoa ?")   ;
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete(sinhVien.id);
                        Intent intent = new Intent(context,MainActivity.class);
                        context.startActivity(intent);
                    }
                }) ;
                builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }) ;
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.listview);
        row.startAnimation(animation);
        return row;

    }

    private void delete(int idSinhVien) {
        SQLiteDatabase database = Database.initDatabase(context,"QL.sqlite") ;
        database.delete("SinhVien ","ID = ?",new String[]{idSinhVien + ""}) ;
        Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien ",null) ;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ma = cursor.getString(1);
            String ten = cursor.getString(2);
            byte[] anh = cursor.getBlob(3);

            list.add(new SinhVien(id,ma,ten,anh));
        }
        notifyDataSetChanged();
    }



}
