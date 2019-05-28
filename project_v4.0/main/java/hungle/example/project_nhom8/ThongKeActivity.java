package hungle.example.project_nhom8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ThongKeActivity extends AppCompatActivity {

    TextView textViewKhoa, textViewSV;
    ImageButton imageButtonKhoa, imageButtonSV;
    ArrayList<Khoa> dataKhoa = new ArrayList<>();
    ArrayList<SinhVien> dataSV = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
       if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // lay danh sach khoa va sinh vien
        LoadDataKhoa();
        LoadDataSV();

        setControl();
        setEvent();
    }

    public void setControl(){
        textViewKhoa = (TextView) findViewById(R.id.tvTongKhoa);
        textViewSV = (TextView) findViewById(R.id.tvTongSV);
        imageButtonKhoa = (ImageButton) findViewById(R.id.ibTongKhoa);
        imageButtonSV = (ImageButton) findViewById(R.id.ibTongSV);
    }

    public void setEvent(){
        textViewKhoa.setText(dataKhoa.size() + "");
        textViewSV.setText(dataSV.size() + "");

        imageButtonKhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongKeActivity.this, KhoaActivity.class);
                startActivity(intent);
            }
        });
        imageButtonSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongKeActivity.this, SinhVienActivity.class);
                startActivity(intent);
            }
        });
    }

    public void LoadDataKhoa()
    {
        Database db = new Database(this);
        dataKhoa.clear();
        db.getAllKhoa(dataKhoa);
    }

    public void LoadDataSV()
    {
        Database db = new Database(this);
        dataSV.clear();
        db.getAllSinhVien(dataSV);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
