package com.huusoat.qlsv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtTK,edtMK;
    Button btnD,btnDN,btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        ControlButTon();
    }


    private void ControlButTon() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog) ;
                builder.setTitle("Bạn Có Chắc Muốn Thoát !")  ;
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận!")  ;
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       onBackPressed();
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
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTK.getText().length() != 0 && edtMK.getText().length() !=0)   {
                    if (edtTK.getText().toString().equals("admin") && edtMK.getText().toString().equals("admin"))    {
                        Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập thành công !", Toast.LENGTH_SHORT).show(); ;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class)  ;
                        startActivity(intent);
                    }   else {
                        Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập thất bại !", Toast.LENGTH_SHORT).show(); ;

                    }

                }
                else{
                    Toast.makeText(LoginActivity.this, "Bạn hãy điền đầy đủ thông tin !", Toast.LENGTH_SHORT).show(); ;
                }
            }
        });
    }


    private void AnhXa() {
        edtTK = (EditText) findViewById(R.id.edtTK);
        edtMK = (EditText) findViewById(R.id.edtMK);

        btnDN = (Button) findViewById(R.id.btnDN);
        btnHuy = (Button) findViewById(R.id.btnHuy);

    }

}
