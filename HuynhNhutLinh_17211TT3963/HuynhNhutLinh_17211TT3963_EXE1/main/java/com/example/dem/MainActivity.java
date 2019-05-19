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

    EditText txtA, txtB;
    Button btnLon, btnNho, btnBang;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ArrayList<PhepTinh> data = new ArrayList<>();
    double dA, dB;
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
        btnLon = (Button) findViewById(R.id.btnLon);
        btnBang= (Button) findViewById(R.id.btnBang);
        btnNho = (Button) findViewById(R.id.btnNho);
    }

    public void setEvent() {
        btnLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtA.getText().toString());
                phepTinh.setSoB(txtB.getText().toString());
                phepTinh.setPt(">");
                // goi ham getSo
                getSo();
                if (dA > dB) {
                    phepTinh.setIcon(R.drawable.ic_check_black_24dp);
                }
                else {
                    phepTinh.setIcon(R.drawable.ic_clear_black_24dp);
                }
                data.add(phepTinh);
                adapter.notifyDataSetChanged();
            }
        });

        btnBang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtA.getText().toString());
                phepTinh.setSoB(txtB.getText().toString());
                phepTinh.setPt("=");
                // goi ham getSo
                getSo();
                if (dA == dB) {
                    phepTinh.setIcon(R.drawable.ic_check_black_24dp);
                }
                else {
                    phepTinh.setIcon(R.drawable.ic_clear_black_24dp);
                }
                data.add(phepTinh);
                adapter.notifyDataSetChanged();
            }
        });

        btnNho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhepTinh phepTinh = new PhepTinh();
                phepTinh.setSoA(txtA.getText().toString());
                phepTinh.setSoB(txtB.getText().toString());
                phepTinh.setPt("<");
                // goi ham getSo
                getSo();
                if (dA < dB) {
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
    }
}
