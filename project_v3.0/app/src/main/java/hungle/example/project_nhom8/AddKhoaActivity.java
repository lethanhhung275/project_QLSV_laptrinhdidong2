package hungle.example.project_nhom8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AddKhoaActivity extends AppCompatActivity {

    TextInputLayout inputLayoutMaKhoa, inputLayoutTenKhoa;
    Button btnHuy, btnThem;
    ArrayList<String> makhoas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_khoa);

        // lay danh sach ma khoa
        getMaKhoa();

        setControl();
        setEvent();
    }

    public void setControl(){
        inputLayoutMaKhoa = (TextInputLayout) findViewById(R.id.textMaKhoa);
        inputLayoutTenKhoa = (TextInputLayout) findViewById(R.id.textTenKhoa);
        btnThem = (Button) findViewById(R.id.btnThemKhoa);
        btnHuy = (Button) findViewById(R.id.btnHuyKhoa);
    }

    public void setEvent(){
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputLayoutMaKhoa.getEditText().getText().length() != 0 && inputLayoutTenKhoa.getEditText().getText().length() !=0)   {
                    for(int i = 0; i < makhoas.size(); i++){
                        if(inputLayoutMaKhoa.getEditText().getText().toString().equals(makhoas.get(i))){
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddKhoaActivity.this) ;
                            builder.setTitle("Thông báo!!!")  ;
                            builder.setMessage("Mã khoa đã tồn tại. Vui lòng kiểm tra lại?");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }) ;
                            builder.show();
                            return;
                        }
                    }
                    addKhoa();
                    Toast.makeText(AddKhoaActivity.this, "Thêm khoa thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddKhoaActivity.this, KhoaActivity.class);
                    startActivity(intent);

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddKhoaActivity.this) ;
                    builder.setTitle("Thông báo!!!")  ;
                    builder.setMessage("Bạn vui lòng nhập đầy đủ mã khoa và tên khoa.");
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
                if (inputLayoutMaKhoa.getEditText().getText().length() != 0 || inputLayoutTenKhoa.getEditText().getText().length() !=0)   {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddKhoaActivity.this) ;
                    builder.setTitle("Thông báo!!!")  ;
                    builder.setMessage("Tác vụ chưa hoàn thành. Bạn có muốn thoát?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(AddKhoaActivity.this, KhoaActivity.class);
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
                    Intent intent = new Intent(AddKhoaActivity.this, KhoaActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void addKhoa()
    {
        Database db = new Database(this);
        Khoa khoa = getKhoa();
        db.saveKhoa(khoa);
    }

    private Khoa getKhoa() {
        Khoa khoa = new Khoa();
        khoa.setMaKhoa(inputLayoutMaKhoa.getEditText().getText().toString());
        khoa.setTenKhoa(inputLayoutTenKhoa.getEditText().getText().toString());
        return khoa;
    }

    private void getMaKhoa(){
        Database db = new Database(this);
        db.getAllMaKhoa(makhoas);
    }
}
