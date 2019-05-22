package hungle.example.project_nhom8;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class UpdateSVActivity extends AppCompatActivity {

    TextInputLayout inputLayoutMaSV, inputLayoutTenSV, inputLayoutDiaChi, inputLayoutSdt, inputLayoutEmail;
    RadioButton radioButtonNam, radioButtonNu;
    Spinner spinnerKhoa, spinnerBacDT;
    EditText editTextNgaySinh, editTextNamVao, editTextNamRa;
    ImageButton imageButtonDate;
    Button btnHuy, btnThem;
    DatePickerDialog datePickerDialog;
    ArrayList<String> tenKhoas = new ArrayList<>();
    ArrayList<SinhVien> data = new ArrayList<>();
    ArrayList<Khoa> dataKhoa = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sv);

        LoadData();
        setControl();

        // lay danh sach khoa
        getDataKhoa();
        // lay danh sach ten khoa
        getTenKhoa();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tenKhoas);
        // attaching data adapter to spinner
        spinnerKhoa.setAdapter(dataAdapter);

        setEvent();
    }

    public void setControl(){
        inputLayoutMaSV = (TextInputLayout) findViewById(R.id.textMaSVUpdate);
        inputLayoutTenSV = (TextInputLayout) findViewById(R.id.textTenSVUpdate);
        inputLayoutDiaChi = (TextInputLayout) findViewById(R.id.textDiaChi);
        inputLayoutSdt = (TextInputLayout) findViewById(R.id.textSdt);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.textEmail);
        radioButtonNam = (RadioButton) findViewById(R.id.radioNam);
        radioButtonNu = (RadioButton) findViewById(R.id.radioNu);
        spinnerBacDT = (Spinner) findViewById(R.id.spinnerBacDT);
        spinnerKhoa = (Spinner) findViewById(R.id.spinnerKhoa);
        editTextNgaySinh = (EditText) findViewById(R.id.editNgaySinh);
        editTextNamVao = (EditText) findViewById(R.id.editNamVao);
        editTextNamRa = (EditText) findViewById(R.id.editNamRa);
        imageButtonDate = (ImageButton) findViewById(R.id.ibDate);
        btnHuy = (Button) findViewById(R.id.btnHuyUpdateSV);
        btnThem = (Button) findViewById(R.id.btnLuuUpdateSV);
    }

    public void setEvent() {
        // hien thi sinh vien
        Intent intent = getIntent();
        SinhVien sinhVien = laySV(intent.getStringExtra("masv"));
        inputLayoutMaSV.getEditText().setText(sinhVien.getMaSV());
        inputLayoutTenSV.getEditText().setText(sinhVien.getTenSV());
        inputLayoutDiaChi.getEditText().setText(sinhVien.getDiaChi());
        inputLayoutSdt.getEditText().setText(sinhVien.getSdt());
        inputLayoutEmail.getEditText().setText(sinhVien.getEmail());
        editTextNgaySinh.setText(sinhVien.getNgaySinh());
        editTextNamVao.setText(sinhVien.getNamVao());
        editTextNamRa.setText(sinhVien.getNamRa());
        // gioi tinh
        if(sinhVien.getGioiTinh().equals("Nam")){
            radioButtonNam.setChecked(true);
        }else {
            radioButtonNu.setChecked(true);
        }
        // bac dao tao
        switch (sinhVien.getBacDT()){
            case "Trung Cấp":{
                spinnerBacDT.setSelection(0);
            }break;
            case "Cao Đẳng":{
                spinnerBacDT.setSelection(1);
            }break;
            case "Đại Học":{
                spinnerBacDT.setSelection(2);
            }break;
        }
        // khoa
        String tenKhoa = "";
        for (int i = 0; i < dataKhoa.size(); i++){
            if (dataKhoa.get(i).getMaKhoa().equals(sinhVien.getKhoa())){
                tenKhoa = dataKhoa.get(i).getTenKhoa();
            }
        }

        for (int i = 0; i < tenKhoas.size(); i++){
            if(tenKhoas.get(i).equals(tenKhoa)){
                spinnerKhoa.setSelection(i);
                break;
            }
        }

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputLayoutTenSV.getEditText().getText().length() != 0 &&
                        inputLayoutDiaChi.getEditText().getText().length() != 0 &&
                        inputLayoutSdt.getEditText().getText().length() != 0 &&
                        inputLayoutEmail.getEditText().getText().length() != 0 &&
                        editTextNgaySinh.getText().toString().length() != 0 &&
                        editTextNamVao.getText().toString().length() != 0 &&
                        editTextNamRa.getText().toString().length() != 0) {
                    // kiem nam vao nam ra
                    if (Integer.parseInt(editTextNamVao.getText().toString()) > Integer.parseInt(editTextNamRa.getText().toString())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateSVActivity.this);
                        builder.setTitle("Thông báo!!!");
                        builder.setMessage("Năm vào trường không thể lớn hơn năm ra trường. Vui lòng kiểm tra lại?");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                        return;
                    }
                    updateSinhVien(getSinhVien());
                    Toast.makeText(UpdateSVActivity.this, "Update sinh viên thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateSVActivity.this, SinhVienActivity.class);
                    startActivity(intent);

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateSVActivity.this);
                    builder.setTitle("Thông báo!!!");
                    builder.setMessage("Bạn vui lòng nhập đầy đủ các thông tin.");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                SinhVien sinhVien = laySV(intent1.getStringExtra("masv"));
                // lay gioi tinh
                String gt = "";
                if(radioButtonNam.isChecked()){
                    gt = "Nam";
                }else {
                    gt = "Nữ";
                }
                // lay khoa
                String tenKhoa = "";
                for (int i = 0; i <dataKhoa.size(); i++){
                    if (dataKhoa.get(i).getMaKhoa().equals(sinhVien.getKhoa())){
                        tenKhoa = dataKhoa.get(i).getTenKhoa();
                        break;
                    }
                }
                if (inputLayoutTenSV.getEditText().getText().toString().equals(sinhVien.getTenSV()) &&
                        inputLayoutDiaChi.getEditText().getText().toString().equals(sinhVien.getDiaChi()) &&
                        inputLayoutSdt.getEditText().getText().toString().equals(sinhVien.getSdt()) &&
                        inputLayoutEmail.getEditText().getText().toString().equals(sinhVien.getEmail()) &&
                        editTextNgaySinh.getText().toString().equals(sinhVien.getNgaySinh()) &&
                        editTextNamVao.getText().toString().equals(sinhVien.getNamVao()) &&
                        editTextNamRa.getText().toString().equals(sinhVien.getNamRa()) &&
                        spinnerBacDT.getSelectedItem().toString().equals(sinhVien.getBacDT()) &&
                        spinnerKhoa.getSelectedItem().toString().equals(tenKhoa) &&
                        gt.equals(sinhVien.getGioiTinh()))   {
                    Intent intent = new Intent(UpdateSVActivity.this, SinhVienActivity.class);
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateSVActivity.this) ;
                    builder.setTitle("Thông báo!!!")  ;
                    builder.setMessage("Tác vụ chưa hoàn thành. Bạn có muốn thoát?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(UpdateSVActivity.this, SinhVienActivity.class);
                            startActivity(intent);
                        }
                    }) ;
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            }
        });

        imageButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(UpdateSVActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        editTextNgaySinh.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void getTenKhoa(){
        Database db = new Database(this);
        db.getAllTenKhoa(tenKhoas);
    }

    private void getDataKhoa(){
        Database db = new Database(this);
        db.getAllKhoa(dataKhoa);
    }

    public void updateSinhVien(SinhVien sinhVien){
        Database db = new Database(this);
        db.updateSV(sinhVien);
    }

    public void LoadData()
    {
        Database db = new Database(this);
        data.clear();
        db.getAllSinhVien(data);
    }

    public SinhVien laySV(String ma){
        SinhVien sinhVien = new SinhVien();
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).getMaSV().equals(ma)){
                sinhVien = data.get(i);
                break;
            }
        }
        return sinhVien;
    }

    private SinhVien getSinhVien() {
        SinhVien sinhVien = new SinhVien();
        sinhVien.setMaSV(inputLayoutMaSV.getEditText().getText().toString());
        sinhVien.setTenSV(inputLayoutTenSV.getEditText().getText().toString());
        sinhVien.setDiaChi(inputLayoutDiaChi.getEditText().getText().toString());
        sinhVien.setSdt(inputLayoutSdt.getEditText().getText().toString());
        sinhVien.setEmail(inputLayoutEmail.getEditText().getText().toString());
        // lay gioi tinh
        if (radioButtonNam.isChecked()){
            sinhVien.setGioiTinh("Nam");
        }else {
            sinhVien.setGioiTinh("Nữ");
        }
        sinhVien.setNgaySinh(editTextNgaySinh.getText().toString());
        sinhVien.setNamVao(editTextNamVao.getText().toString());
        sinhVien.setNamRa(editTextNamRa.getText().toString());
        // lay khoa
        String tenKhoa = spinnerKhoa.getSelectedItem().toString();
        for (int i = 0; i < dataKhoa.size(); i++){
            if(dataKhoa.get(i).getTenKhoa().equals(tenKhoa)){
                sinhVien.setKhoa(dataKhoa.get(i).getMaKhoa());
            }
        }
        sinhVien.setBacDT(spinnerBacDT.getSelectedItem().toString());
        return sinhVien;
    }
}
