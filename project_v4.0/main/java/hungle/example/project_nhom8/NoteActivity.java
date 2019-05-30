package hungle.example.project_nhom8;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView5;
    ArrayList<TheoDoi> data = new ArrayList<>();
    ArrayList<SinhVien> dataSinhVien = new ArrayList<>();
    NoteAdapter adapter = null;
    TextView textViewNoteMa, textViewNoteTen;
    SinhVien sinhVien = new SinhVien();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setControl();
        LoadSV();
        setAdapter();
        setEvent();
    }

    public void setAdapter(){
        adapter = new NoteAdapter(this, R.layout.listview_item_layout_note, data);
        listView5.setAdapter(adapter);
    }

    public void setControl() {
        listView5 = (ListView) findViewById(R.id.listViewNote);
        textViewNoteMa = (TextView) findViewById(R.id.tvNoteMa);
        textViewNoteTen = (TextView) findViewById(R.id.tvNoteTen);
    }

    public void setEvent() {
        Intent intent = getIntent();
        sinhVien = laySV(intent.getStringExtra("masv"));
        textViewNoteMa.setText(sinhVien.getMaSV());
        textViewNoteTen.setText(sinhVien.getTenSV());

        // lay danh sach note
        LoadData(sinhVien.getMaSV());
    }

    public void LoadData(String ma)
    {
        Database db = new Database(this);
        data.clear();
        db.getAllTheoDoiSV(data,ma);
        adapter.notifyDataSetChanged();
    }

    public void LoadSV()
    {
        Database db = new Database(this);
        dataSinhVien.clear();
        db.getAllSinhVien(dataSinhVien);
    }

    public SinhVien laySV(String ma){
        SinhVien sinhVien = new SinhVien();
        for(int i = 0; i < dataSinhVien.size(); i++){
            if(dataSinhVien.get(i).getMaSV().equals(ma)){
                sinhVien = dataSinhVien.get(i);
                break;
            }
        }
        return sinhVien;
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
        getMenuInflater().inflate(R.menu.note, menu);
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

        if (id == R.id.nav_trangchu) {
            Intent intent = new Intent(NoteActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_addnote) {
            Intent intent = new Intent(NoteActivity.this, AddNoteActivity.class);
            intent.putExtra("masv", sinhVien.getMaSV());
            startActivity(intent);
        } else if (id == R.id.nav_sinhvien) {
            Intent intent = new Intent(NoteActivity.this, SinhVienActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_khoa) {
            Intent intent = new Intent(NoteActivity.this, KhoaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_timkiem) {
            Intent intent = new Intent(NoteActivity.this, TimKiemActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
