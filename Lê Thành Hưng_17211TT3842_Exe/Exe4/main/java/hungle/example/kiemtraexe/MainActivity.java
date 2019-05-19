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
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtMaNV, txtHoTen, txtSdt;
    Button btnAdd, btnDelete, btnUpdate, btnReset;
    RadioButton radioButtonNam, radioButtonNu, radioButtonKT, radioButtonKD, radioButtonNS;
    ListView listView;
    Adapter adapter;
    ArrayList<NhanVien> dataKT = new ArrayList<>();
    ArrayList<NhanVien> dataKD = new ArrayList<>();
    ArrayList<NhanVien> dataNS = new ArrayList<>();
    ArrayList<NhanVien> dataAll = new ArrayList<>();

    private TelephonyManager mTelephonyManager;
    public static boolean isListening = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KhoiTao();

        setControl();
        setEvent();
        setAdapter(dataAll);
    }
    PhoneStateListener phoneStateListener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            super.onCallStateChanged(state, phoneNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    Toast.makeText(MainActivity.this, "CALL_STATE_IDLE", Toast.LENGTH_SHORT).show();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Toast.makeText(MainActivity.this, "CALL_STATE_RINGING", Toast.LENGTH_SHORT).show();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(MainActivity.this, "CALL_STATE_OFFHOOK", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    void  setAdapter(ArrayList<NhanVien> data){
        Adapter adapter = new Adapter(this, R.layout.listview_item_layout_nu, data);
        listView.setAdapter(adapter);
    }

    public void setControl(){
        txtMaNV = (EditText) findViewById(R.id.editMaNV);
        txtSdt = (EditText) findViewById(R.id.editSdt);
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
        listView = findViewById(R.id.listView);
        mTelephonyManager = (TelephonyManager) getSystemService(getApplicationContext().TELEPHONY_SERVICE);
    }

    public void setEvent(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = new NhanVien();

                nhanVien.setsMaNV(txtMaNV.getText().toString());
                nhanVien.setsSdt(txtSdt.getText().toString());
                nhanVien.setsHoTen(txtHoTen.getText().toString());
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
                }
                if(radioButtonKD.isChecked()){
                    nhanVien.setsPhong("Kinh doanh");
                }
                if(radioButtonNS.isChecked()){
                    nhanVien.setsPhong("Nhân sự");
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

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               showDialogConfirm(position);
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

    /*@Override
    protected void onResume() {
        super.onResume();
        mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }*/

    @Override
    protected void onStop() {
        super.onStop();
        mTelephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
    }

    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }


    public void showDialogConfirm(final int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
        Button btnSendMessage = (Button) dialog.findViewById(R.id.btn_send_message);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCall(position);
            }
        });
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSendMesseage(position);
            }
        });
        dialog.show();

    }

    private void intentSendMesseage(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + dataAll.get(position).getsSdt()));
        startActivity(intent);
    }

    private void intentCall(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + dataAll.get(position).getsSdt()));
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action != null && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
    }

    void KhoiTao() {
        dataAll.add(new NhanVien(R.drawable.nam, "123", "Hung", "Ke Toan", "123345", true));
        dataAll.add(new NhanVien(R.drawable.nam, "456", "Vu", "Ke Toan", "1233457", true));
    }
}
