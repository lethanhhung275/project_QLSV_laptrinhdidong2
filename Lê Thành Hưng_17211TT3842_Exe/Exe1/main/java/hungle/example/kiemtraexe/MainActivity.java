package hungle.example.kiemtraexe;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtMaNV, txtHoTen;
    Button btnAdd, btnDelete, btnUpdate, btnReset;
    RadioButton radioButtonNam, radioButtonNu, radioButtonKT, radioButtonKD, radioButtonNS;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    ArrayList<NhanVien> dataKT = new ArrayList<>();
    ArrayList<NhanVien> dataKD = new ArrayList<>();
    ArrayList<NhanVien> dataNS = new ArrayList<>();
    ArrayList<NhanVien> dataAll = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setAdapter(dataKT);
        setEvent();
    }

    void  setAdapter(ArrayList<NhanVien> data){
        recyclerView = findViewById(R.id.recycleView1);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(this, data);
        recyclerView.setAdapter(adapter);
    }

    public void setControl(){
        txtMaNV = (EditText) findViewById(R.id.editMaNV);
        txtHoTen = (EditText) findViewById(R.id.editHoTen);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnReset = (Button) findViewById(R.id.btnReset);
        radioButtonNam = (RadioButton) findViewById(R.id.rbNam);
        radioButtonNu = (RadioButton) findViewById(R.id.rbNu);
        radioButtonKT = (RadioButton) findViewById(R.id.rbKT);
        radioButtonKD = (RadioButton) findViewById(R.id.rbKD);
        radioButtonNS = (RadioButton) findViewById(R.id.rbNS);
    }

    public void setEvent(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = new NhanVien();

                nhanVien.setsMaNV(txtMaNV.getText().toString());
                // kiem tra da nhap ma
                if(nhanVien.getsMaNV().length() == 0){
                    Toast.makeText(MainActivity.this, "Bạn chưa nhận mã nhân viên", Toast.LENGTH_SHORT).show();
                    return;
                }

                // kiem tra ma da ton tai hay chua
                for (int i = 0; i < dataAll.size(); i++) {
                    if (nhanVien.getsMaNV().equals(dataAll.get(i).getsMaNV())) {
                        Toast.makeText(MainActivity.this, "Mã nhân viên đã tồn tại", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                nhanVien.setsHoTen(txtHoTen.getText().toString());
                // kiem tra da nhap ho ten
                if(nhanVien.getsHoTen().length() == 0){
                    Toast.makeText(MainActivity.this, "Bạn chưa nhận họ tên", Toast.LENGTH_SHORT).show();
                    return;
                }

                // lay gioi tinh
                if (radioButtonNam.isChecked()) {
                    nhanVien.setbGT(true);
                    nhanVien.setIcon(R.drawable.nam);
                }
                else {
                    nhanVien.setbGT(false);
                    nhanVien.setIcon(R.drawable.nu);
                }

                // lay phong
                if(radioButtonKT.isChecked()){
                    nhanVien.setsPhong("Kế toán");
                    dataKT.add(nhanVien);
                }
                if(radioButtonKD.isChecked()){
                    nhanVien.setsPhong("Kinh doanh");
                    dataKD.add(nhanVien);
                }
                if(radioButtonNS.isChecked()){
                    nhanVien.setsPhong("Nhân sự");
                    dataNS.add(nhanVien);
                }
                dataAll.add(nhanVien);
                Toast.makeText(MainActivity.this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sDeleteNV = "";
                sDeleteNV = txtMaNV.getText().toString();
                for (int i = 0; i < dataAll.size(); i++) {
                    if (sDeleteNV.equals(dataAll.get(i).getsMaNV())) {
                        if (dataAll.get(i).getsPhong().equals("Kế toán")){
                            deleteNV(dataKT, sDeleteNV);
                        }
                        if (dataAll.get(i).getsPhong().equals("Kinh doanh")){
                            deleteNV(dataKD, sDeleteNV);
                        }
                        if (dataAll.get(i).getsPhong().equals("Nhân sự")){
                            deleteNV(dataNS, sDeleteNV);
                        }
                        dataAll.remove(i);
                        adapter.notifyDataSetChanged();
                        return;
                    }
                }
            }
        });
        /*btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setsMaNV(txtMaNV.getText().toString());
                nhanVien.setsHoTen(txtHoTen.getText().toString());
                for(int i = 0; i < dataAll.size(); i++){
                    if(sUpdateNV.equals(dataAll.get(i).getsMaNV())){
                        dataAll.get(i).setsHoTen(txtHoTen.getText().toString());
                        // lay phong
                        String sPhong = "";
                        if(radioButtonKT.isChecked()){
                            sPhong = "Kế toán";
                        }
                        if(radioButtonKD.isChecked()){
                            sPhong = "Kinh doanh";
                        }
                        if(radioButtonNS.isChecked()){
                            sPhong = "Nhân sự";
                        }
                        data.get(i).setsPhong(sPhong);
                        // lay gioi tinh
                        if (radioButtonNam.isChecked()) {
                            data.get(i).setbGT(true);
                            data.get(i).setIcon(R.drawable.nam);
                        }
                        else {
                            data.get(i).setbGT(false);
                            data.get(i).setIcon(R.drawable.nu);
                        }
                        adapter.notifyDataSetChanged();
                        return;
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });*/
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaNV.setText("");
                txtHoTen.setText("");
                radioButtonNam.setChecked(true);
                radioButtonKT.setChecked(true);
                txtMaNV.requestFocus();
                setAdapter(dataKT);
            }
        });
        radioButtonKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAdapter(dataKT);
            }
        });
        radioButtonKD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAdapter(dataKD);
            }
        });
        radioButtonNS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAdapter(dataNS);
            }
        });
    }

    public void deleteNV(ArrayList<NhanVien> data, String key){
        for (int i = 0; i < data.size(); i++) {
            if (key.equals(data.get(i).getsMaNV())) {
                data.remove(i);
                return;
            }
        }
    }

}
