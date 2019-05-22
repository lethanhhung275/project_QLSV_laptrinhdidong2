package hungle.example.project_nhom8;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ChiTietSVActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textViewMa, textViewTen, textViewGT, textViewNgaySinh, textViewDiaChi, textViewSdt,
            textViewEmail, textViewBacDT, textViewKhoaHoc, textViewKhoa;
    SinhVien sinhVien = new SinhVien();

    ArrayList<Khoa> dataKhoa = new ArrayList<>();
    ArrayList<SinhVien> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // lay danh sach khoa
        getDataKhoa();
        LoadData();
        setControl();
        setEvent();
    }

    public void setControl(){
        textViewMa = (TextView) findViewById(R.id.tvTTMa);
        textViewTen = (TextView) findViewById(R.id.tvTTTen);
        textViewGT = (TextView) findViewById(R.id.tvTTGT);
        textViewNgaySinh = (TextView) findViewById(R.id.tvTTNgaySinh);
        textViewDiaChi = (TextView) findViewById(R.id.tvTTDiaChi);
        textViewSdt = (TextView) findViewById(R.id.tvTTSdt);
        textViewEmail = (TextView) findViewById(R.id.tvTTEmail);
        textViewBacDT = (TextView) findViewById(R.id.tvTTBacDT);
        textViewKhoaHoc = (TextView) findViewById(R.id.tvTTKhoaHoc);
        textViewKhoa = (TextView) findViewById(R.id.tvTTKhoa);
    }

    public void setEvent(){
        Intent intent = getIntent();
        sinhVien = laySV(intent.getStringExtra("masv"));
        textViewMa.setText(sinhVien.getMaSV());
        textViewTen.setText(sinhVien.getTenSV());
        textViewGT.setText(sinhVien.getGioiTinh());
        textViewNgaySinh.setText(sinhVien.getNgaySinh());
        textViewDiaChi.setText(sinhVien.getDiaChi());
        textViewSdt.setText(sinhVien.getSdt());
        textViewEmail.setText(sinhVien.getEmail());
        textViewBacDT.setText(sinhVien.getBacDT());
        textViewKhoaHoc.setText(sinhVien.getNamVao() + " - " + sinhVien.getNamRa());
        // khoa
        for (int i = 0; i < dataKhoa.size(); i++){
            if (dataKhoa.get(i).getMaKhoa().equals(sinhVien.getKhoa())){
                textViewKhoa.setText(dataKhoa.get(i).getTenKhoa());
                break;
            }
        }
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

    private void getDataKhoa(){
        Database db = new Database(this);
        db.getAllKhoa(dataKhoa);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chi_tiet_sv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_update) {
            Intent intent = new Intent(ChiTietSVActivity.this, UpdateSVActivity.class);
            intent.putExtra("masv", sinhVien.getMaSV());
            startActivity(intent);
        } else if (id == R.id.nav_note) {
            Intent intent = new Intent(ChiTietSVActivity.this, NoteActivity.class);
            intent.putExtra("masv", sinhVien.getMaSV());
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
