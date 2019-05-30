package hungle.example.project_nhom8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TimMaSVActivity extends AppCompatActivity {

    TextInputLayout inputLayoutTKMa;
    ImageButton imageButtonTK;
    ListView listView3;
    ArrayList<SinhVien> data = new ArrayList<>();
    SinhVienAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_ma_sv);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setControl();
        setAdapter();
        setEvent();
    }

    public void setControl(){
        inputLayoutTKMa = (TextInputLayout) findViewById(R.id.textTimKiemMa);
        imageButtonTK = (ImageButton) findViewById(R.id.ibTKMa);
        listView3 = (ListView) findViewById(R.id.listViewTKMa);
    }

    public void setAdapter(){
        adapter = new SinhVienAdapter(this, R.layout.listview_item_layout_sinhvien, data);
        listView3.setAdapter(adapter);
    }

    public void setEvent() {
        imageButtonTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = inputLayoutTKMa.getEditText().getText().toString();
                if(ma.length() !=0){
                    timKiemMa(ma);
                    if(data.isEmpty()){
                        Toast.makeText(TimMaSVActivity.this, "Không tìm được sinh viên", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(TimMaSVActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn chưa nhập mã sinh viên cần tìm.");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TimMaSVActivity.this, ChiTietSVActivity.class);
                intent.putExtra("masv", data.get(position).getMaSV());
                startActivity(intent);
            }
        });

        listView3.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TimMaSVActivity.this);
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

    public void timKiemMa(String ma)
    {
        Database db = new Database(this);
        data.clear();
        db.findMaSV(ma, data);
        adapter.notifyDataSetChanged();
    }

    public void DeleteSV(String sMa)
    {
        Database db = new Database(this);
        data.clear();
        db.deleteSV(sMa);
        db.deleteNote(sMa);
        db.findMaSV(sMa, data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
