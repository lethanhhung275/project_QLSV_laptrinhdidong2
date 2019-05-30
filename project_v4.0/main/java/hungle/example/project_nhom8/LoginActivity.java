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

public class LoginActivity extends AppCompatActivity {

    TextInputLayout inputLayoutTK, inputLayoutMK;
    Button btnDangNhap, btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setControl();
        setEvent();
    }

    private void setControl(){
        inputLayoutTK = (TextInputLayout) findViewById(R.id.textTaiKhoan);
        inputLayoutMK = (TextInputLayout) findViewById(R.id.textMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnThoat = (Button) findViewById(R.id.btnThoat);
    }

    private void setEvent(){
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputLayoutTK.getEditText().getText().length() != 0 && inputLayoutMK.getEditText().getText().length() !=0)   {
                    if (inputLayoutTK.getEditText().getText().toString().equals("admin") && inputLayoutMK.getEditText().getText().toString().equals("admin"))    {
                        Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập thành công !", Toast.LENGTH_SHORT).show(); ;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class)  ;
                        startActivity(intent);
                    }   else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this) ;
                        builder.setTitle("Thông báo!!!")  ;
                        builder.setMessage("Đăng nhập thất bại. Bạn vui lòng kiểm tra lại tài khoản và mật khẩu.");
                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }) ;
                        builder.show();

                    }

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this) ;
                    builder.setTitle("Thông báo!!!")  ;
                    builder.setMessage("Bạn vui lòng nhập đầy đủ tài khoản và mật khẩu.");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }) ;
                    builder.show();
                }
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog) ;
                builder.setTitle("BẠN MUỐN THOÁT?")  ;
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận!")  ;
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }) ;
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }) ;
                builder.show();
            }
        });
    }
}
