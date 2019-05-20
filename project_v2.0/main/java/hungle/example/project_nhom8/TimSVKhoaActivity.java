package hungle.example.project_nhom8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class TimSVKhoaActivity extends AppCompatActivity {

    Spinner spinnerKhoa;
    ListView listView4;
    ArrayList<String> tenKhoas = new ArrayList<>();
    ArrayList<SinhVien> data = new ArrayList<>();
    SinhVienAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_svkhoa);

        setControl();
        // lay danh sach ten khoa
        getTenKhoa();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tenKhoas);
        // attaching data adapter to spinner
        spinnerKhoa.setAdapter(dataAdapter);
        setAdapter();
        setEvent();
    }

    public void setControl(){
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
                String khoa = spinnerKhoa.getSelectedItem().toString();
                timKiemSVKhoa(khoa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(SinhVienActivity.this, UpdateSVActivity.class);
                intent.putExtra("masv", data.get(position).getMaSV());
                startActivity(intent);*/
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

    public void timKiemSVKhoa(String khoa)
    {
        Database db = new Database(this);
        data.clear();
        db.findSVKhoa(khoa, data);
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
}
