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

public class UpdateKhoaActivity extends AppCompatActivity {

    TextInputLayout inputLayoutMaKhoa, inputLayoutTenKhoa;
    Button btnHuy, btnLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_khoa);

        setControl();
        setEvent();
    }

    public void setControl(){
        inputLayoutMaKhoa = (TextInputLayout) findViewById(R.id.textMaKhoaUpdate);
        inputLayoutTenKhoa = (TextInputLayout) findViewById(R.id.textTenKhoaUpdate);
        Intent intent = getIntent();
        inputLayoutMaKhoa.getEditText().setText(intent.getStringExtra("makhoa"));
        inputLayoutTenKhoa.getEditText().setText(intent.getStringExtra("tenkhoa"));
        btnLuu = (Button) findViewById(R.id.btnLuuUpdateKhoa);
        btnHuy = (Button) findViewById(R.id.btnHuyUpdateKhoa);
    }

    public void setEvent(){
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputLayoutTenKhoa.getEditText().getText().length() !=0)   {
                    updateKhoa(getKhoa());
                    Toast.makeText(UpdateKhoaActivity.this, "Update thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateKhoaActivity.this, KhoaActivity.class);
                    startActivity(intent);

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateKhoaActivity.this) ;
                    builder.setTitle("Thông báo!!!")  ;
                    builder.setMessage("Bạn vui lòng nhập tên khoa.");
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
                Intent intent = getIntent();
                if (inputLayoutTenKhoa.getEditText().getText().toString().equals(intent.getStringExtra("tenkhoa")))   {
                    Intent intent1 = new Intent(UpdateKhoaActivity.this, KhoaActivity.class);
                    startActivity(intent1);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateKhoaActivity.this);
                    builder.setTitle("Thông báo!!!")  ;
                    builder.setMessage("Tác vụ chưa hoàn thành. Bạn có muốn thoát?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(UpdateKhoaActivity.this, KhoaActivity.class);
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
    }

    private Khoa getKhoa() {
        Khoa khoa = new Khoa();
        khoa.setMaKhoa(inputLayoutMaKhoa.getEditText().getText().toString());
        khoa.setTenKhoa(inputLayoutTenKhoa.getEditText().getText().toString());
        return khoa;
    }

    public void updateKhoa(Khoa khoa)
    {
        Database db = new Database(this);
        db.updateKhoa(khoa);
    }
}
