package hungle.example.project_nhom8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TimSVKhoaActivity extends AppCompatActivity {

    Spinner spinnerKhoa;
    ListView listView4;
    ArrayList<String> tenKhoas = new ArrayList<>();
    ArrayList<SinhVien> data = new ArrayList<>();
    ArrayList<Khoa> dataKhoa = new ArrayList<>();
    SinhVienAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_svkhoa);

        setControl();
        // lay danh sach khoa
        getDataKhoa();
        // lay danh sach ten khoa
        getTenKhoa();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tenKhoas);
        // attaching data adapter to spinner
        spinnerKhoa.setAdapter(dataAdapter);

        setAdapter();
        setEvent();
    }

    public void setControl() {
        spinnerKhoa = (Spinner) findViewById(R.id.spinnerTKSVKhoa);
        listView4 = (ListView) findViewById(R.id.listViewTKSVKhoa);
    }

    public void setAdapter(){
        adapter = new SinhVienAdapter(this, R.layout.listview_item_layout_sinhvien, data);
        listView4.setAdapter(adapter);
    }

    public void setEvent() {
        spinnerKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tenKhoa = spinnerKhoa.getSelectedItem().toString();
                for (int i = 0; i < dataKhoa.size(); i++){
                    if(dataKhoa.get(i).getTenKhoa().equals(tenKhoa)){
                        timKiemSVKhoa(dataKhoa.get(i).getMaKhoa());
                        // thong bao khi khoa khong co sinh vien
                        if(data.isEmpty()){
                            Toast.makeText(TimSVKhoaActivity.this, "Khoa không chưa có sinh viên", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TimSVKhoaActivity.this, ChiTietSVActivity.class);
                intent.putExtra("masv", data.get(position).getMaSV());
                startActivity(intent);
            }
        });

        listView4.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TimSVKhoaActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Xác nhận xóa sinh viên???");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SinhVien sv = data.get(position);
                        DeleteSV(sv.getMaSV());
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return false;
            }
        });
    }

    private void getTenKhoa(){
        Database db = new Database(this);
        db.getAllTenKhoa(tenKhoas);
    }

    public void timKiemSVKhoa(String k)
    {
        Database db = new Database(this);
        data.clear();
        db.findKhoaSV(k, data);
        adapter.notifyDataSetChanged();
    }

    public void DeleteSV(String sMa)
    {
        Database db = new Database(this);
        data.clear();
        db.deleteSV(sMa);
        db.getAllSinhVien(data);
        adapter.notifyDataSetChanged();
    }

    private void getDataKhoa(){
        Database db = new Database(this);
        db.getAllKhoa(dataKhoa);
    }
}
