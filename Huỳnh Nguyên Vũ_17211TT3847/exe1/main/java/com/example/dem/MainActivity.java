package com.example.dem;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    private Sensor acclerometor;
    private float vibrateThreshold = 0;
    private float lastX;
    EditText txtten, txtmssv;
    Button btnthem,btnedit,btndelete;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ArrayList<QLSV> data = new ArrayList<>();
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
        txtten = (EditText) findViewById(R.id.txtten);
        txtmssv = (EditText) findViewById(R.id.txtmssv);
        btnthem = (Button) findViewById(R.id.btnthem);
        btnedit = (Button) findViewById(R.id.btnedit);
        btndelete = (Button) findViewById(R.id.btndelete);
    }

    private void setSensor(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            acclerometor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this,acclerometor,SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = acclerometor.getMaximumRange()/5;
        }
    }
    public void them(SensorEvent event){
        float deltaX = Math.abs(lastX - event.values[0]);
        if(deltaX > vibrateThreshold){
            QLSV phepTinh = new QLSV();
            phepTinh.setTen(txtten.getText().toString());
            phepTinh.setMmsv(txtmssv.getText().toString());
            phepTinh.setIcon(R.drawable.ic_account_circle_black_24dp);
            data.add(phepTinh);
            adapter.notifyDataSetChanged();
            txtmssv.setText("");
            txtten.setText("");
            txtmssv.requestFocus();
        }
        lastX = event.values[0];
    }
    public void setEvent() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QLSV phepTinh = new QLSV();
                phepTinh.setTen(txtten.getText().toString());
                phepTinh.setMmsv(txtmssv.getText().toString());
                phepTinh.setIcon(R.drawable.ic_account_circle_black_24dp);
                data.add(phepTinh);
                adapter.notifyDataSetChanged();
                txtmssv.setText("");
                txtten.setText("");
                txtmssv.requestFocus();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleteNV = "";
                deleteNV = txtmssv.getText().toString();
                for (int i = 0; i < data.size(); i++) {
                    if (deleteNV.equals(data.get(i).getMmsv())) {
                        data.remove(i);
                        adapter.notifyDataSetChanged();
                        //return;
                    }
                }
                txtmssv.setText("");
                txtten.setText("");
                txtmssv.requestFocus();
            }
        });
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleteNV = "";
                deleteNV = txtmssv.getText().toString();
                for(int i = 0; i < data.size(); i++){
                    if(deleteNV.equals(data.get(i).getMmsv())){
                        data.get(i).setTen(txtten.getText().toString());
                        // lay phong
                        adapter.notifyDataSetChanged();
                        return;
                    }
                }
                adapter.notifyDataSetChanged();
                txtmssv.setText("");
                txtten.setText("");
                txtmssv.requestFocus();
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
