package com.huusoat.qlsv;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String DATABASE_NAME = "QL.sqlite";
    SQLiteDatabase database;
    ListView listView;
    ArrayList<SinhVien> list;
    AdapterSinhVien adapter;
    private AdView av;
    int id = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        addContentView();
        readData();
        av = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        av.loadAd(adRequest);
    }
    @Override
    public void onPause() {
        if (av != null) {
            av.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (av != null) {
            av.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (av != null) {
            av.destroy();
        }
        super.onDestroy();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnThem:
                Intent intent = new Intent(MainActivity.this, AddActivity.class) ;
                startActivity(intent);
                break;
            case R.id.mnTK:
                Intent intent1 = new Intent(MainActivity.this, ChooseActivity.class) ;
                startActivity(intent1);
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
        Cursor cursor = database.rawQuery("SELECT ID,MaSV,TenSV,Anh FROM SinhVien ",null);

        list.clear();
        for (int i= 0; i< cursor.getCount();i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(2);
            String ma = cursor.getString(1);
            byte[] anh = cursor.getBlob(3);
            list.add(new SinhVien( id,ten, ma, anh));
        }
        adapter.notifyDataSetChanged();
    }
}

