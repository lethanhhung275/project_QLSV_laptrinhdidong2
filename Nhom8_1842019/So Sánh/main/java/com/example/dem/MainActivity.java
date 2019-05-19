package com.example.dem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtA, txtB, txtKQ;
    Button btnCong, btnTru, btnNhan, btnChia;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ArrayList<PhepTinh> data = new ArrayList<>();
    double dA, dB, dKQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
        setAdapter();
    }

    void  setAdapter(){
        recyclerView = findViewById(R.id.recycleView1);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, R.layout.listview_item, data);
        recyclerView.setAdapter(adapter);
    }
    public void setControl(){
        txtA = (EditText) findViewById(R.id.txtA);
        txtB = (EditText) findViewById(R.id.txtB);
        txtKQ = (EditText) findViewById(R.id.txtKQ);
        btnCong = (Button) findViewById(R.id.btnCong);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);
    }

    public void setEvent() {
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtA.getText().toString());
                phepTinh.setSoB(txtB.getText().toString());
                phepTinh.setPt("+");
                phepTinh.setKq(txtKQ.getText().toString());
                // goi ham getSo
                getSo();
                if (dA + dB == dKQ) {
                    phepTinh.setIcon(R.drawable.ic_check_black_24dp);
                }
                else {
                    phepTinh.setIcon(R.drawable.ic_clear_black_24dp);
                }
                data.add(phepTinh);
                adapter.notifyDataSetChanged();
            }
        });

        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtA.getText().toString());
                phepTinh.setSoB(txtB.getText().toString());
                phepTinh.setPt("-");
                phepTinh.setKq(txtKQ.getText().toString());
                // goi ham getSo
                getSo();
                if (dA - dB == dKQ) {
                    phepTinh.setIcon(R.drawable.ic_check_black_24dp);
                }
                else {
                    phepTinh.setIcon(R.drawable.ic_clear_black_24dp);
                }
                data.add(phepTinh);
                adapter.notifyDataSetChanged();
            }
        });

        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtA.getText().toString());
                phepTinh.setSoB(txtB.getText().toString());
                phepTinh.setPt("*");
                phepTinh.setKq(txtKQ.getText().toString());
                // goi ham getSo
                getSo();
                if (dA * dB == dKQ) {
                    phepTinh.setIcon(R.drawable.ic_check_black_24dp);
                }
                else {
                    phepTinh.setIcon(R.drawable.ic_clear_black_24dp);
                }
                data.add(phepTinh);
                adapter.notifyDataSetChanged();
            }
        });

        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtA.getText().toString());
                phepTinh.setSoB(txtB.getText().toString());
                phepTinh.setPt("/");
                phepTinh.setKq(txtKQ.getText().toString());
                // goi ham getSo
                getSo();
                if (dA / dB == dKQ) {
                    phepTinh.setIcon(R.drawable.ic_check_black_24dp);
                }
                else {
                    phepTinh.setIcon(R.drawable.ic_clear_black_24dp);
                }
                data.add(phepTinh);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void getSo(){
        dA = Double.parseDouble(txtA.getText().toString());
        dB = Double.parseDouble(txtB.getText().toString());
        dKQ = Double.parseDouble(txtKQ.getText().toString());
    }
}
