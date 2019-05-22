package hungle.example.project_nhom8;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class AddSVActivity extends AppCompatActivity {

    TextInputLayout inputLayoutMaSV, inputLayoutTenSV, inputLayoutDiaChi, inputLayoutSdt, inputLayoutEmail;
    RadioButton radioButtonNam, radioButtonNu;
    Spinner spinnerKhoa, spinnerBacDT;
    EditText editTextNgaySinh, editTextNamVao, editTextNamRa;
    ImageButton imageButtonDate;
    Button btnHuy, btnThem;
    DatePickerDialog datePickerDialog;
    ArrayList<String> maSVs = new ArrayList<>();
    ArrayList<String> tenKhoas = new ArrayList<>();
    ArrayList<Khoa> dataKhoa = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sv);

        setControl();
        // lay danh sach ma sinh vien
        getMaSV();
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
        inputLayoutMaSV = (TextInputLayout) findViewById(R.id.textMaSV);
        inputLayoutTenSV = (TextInputLayout) findViewById(R.id.textTenSV);
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
        btnHuy = (Button) findViewById(R.id.btnHuySV);
        btnThem = (Button) findViewById(R.id.btnThemSV);
    }

    public void setEvent(){
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputLayoutMaSV.getEditText().getText().length() != 0 &&
                        inputLayoutTenSV.getEditText().getText().length() !=0 &&
                        inputLayoutDiaChi.getEditText().getText().length() !=0 &&
                        inputLayoutSdt.getEditText().getText().length() !=0 &&
                        inputLayoutEmail.getEditText().getText().length() !=0 &&
                        editTextNgaySinh.getText().toString().length() !=0 &&
                        editTextNamVao.getText().toString().length() !=0 &&
                        editTextNamRa.getText().toString().length() !=0)   {
                    for(int i = 0; i < maSVs.size(); i++){
                        if(inputLayoutMaSV.getEditText().getText().toString().equals(maSVs.get(i))){
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddSVActivity.this) ;
                            builder.setTitle("Thông báo!!!")  ;
                            builder.setMessage("Mã sinh viên đã tồn tại. Vui lòng kiểm tra lại?");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }) ;
                            builder.show();
                            return;
                        }
                    }
                    // kiem nam vao nam ra
                    if(Integer.parseInt(editTextNamVao.getText().toString())>Integer.parseInt(editTextNamRa.getText().toString())){
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddSVActivity.this) ;
                        builder.setTitle("Thông báo!!!")  ;
                        builder.setMessage("Năm vào trường không thể lớn hơn năm ra trường. Vui lòng kiểm tra lại?");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }) ;
                        builder.show();
                        return;
                    }
                    addSinhVien();
                    Toast.makeText(AddSVActivity.this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddSVActivity.this, SinhVienActivity.class);
                    startActivity(intent);

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddSVActivity.this) ;
                    builder.setTitle("Thông báo!!!")  ;
                    builder.setMessage("Bạn vui lòng nhập đầy đủ các thông tin.");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }) ;
                    builder.show();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputLayoutMaSV.getEditText().getText().length() != 0 ||
                        inputLayoutTenSV.getEditText().getText().length() !=0 ||
                        inputLayoutDiaChi.getEditText().getText().length() !=0 ||
                        inputLayoutSdt.getEditText().getText().length() !=0 ||
                        inputLayoutEmail.getEditText().getText().length() !=0 ||
                        editTextNgaySinh.getText().toString().length() !=0 ||
                        editTextNamVao.getText().toString().length() !=0 ||
                        editTextNamRa.getText().toString().length() !=0)   {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddSVActivity.this) ;
                    builder.setTitle("Thông báo!!!")  ;
                    builder.setMessage("Tác vụ chưa hoàn thành. Bạn có muốn thoát?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(AddSVActivity.this, SinhVienActivity.class);
                            startActivity(intent);
                        }
                    }) ;
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }else {
                    Intent intent = new Intent(AddSVActivity.this, SinhVienActivity.class);
                    startActivity(intent);
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
                datePickerDialog  = new DatePickerDialog(AddSVActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        editTextNgaySinh.setText(dayOfMonth+"/"+(monthOfYear + 1)+"/"+year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void getMaSV(){
        Database db = new Database(this);
        db.getAllMaSV(maSVs);
    }

    private void getTenKhoa(){
        Database db = new Database(this);
        db.getAllTenKhoa(tenKhoas);
    }

    private void getDataKhoa(){
        Database db = new Database(this);
        db.getAllKhoa(dataKhoa);
    }

    public void addSinhVien()
    {
        Database db = new Database(this);
        SinhVien sinhVien = getSinhVien();
        db.saveSV(sinhVien);
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
