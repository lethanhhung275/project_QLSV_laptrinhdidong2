package hungle.example.exe2_sensor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Spinner spinnerNai, spinnerBau, spinnerGa, spinnerCa, spinnerCua, spinnerTom;
    ImageView imageView1,imageView2, imageView3;
    Button buttonChoi;
    TextView textViewTien;
    int tienNai = 0, tienBau = 0, tienGa = 0, tienCa = 0, tienCua = 0, tienTom = 0, tienCong, tienTru;
    static int tienDangCo = 500;

    // Sensor
    SensorManager sensorManager;
    private Sensor accelerometor;
    private float vibrateThreshold = 0;
    private  float lastX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setSensor();
        setEvent();
    }

    public void setControl(){
        spinnerNai = (Spinner) findViewById(R.id.spNai);
        spinnerBau = (Spinner) findViewById(R.id.spBau);
        spinnerGa = (Spinner) findViewById(R.id.spGa);
        spinnerCa = (Spinner) findViewById(R.id.spCa);
        spinnerCua = (Spinner) findViewById(R.id.spCua);
        spinnerTom = (Spinner) findViewById(R.id.spTom);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        buttonChoi = (Button) findViewById(R.id.btnChoi);
        textViewTien = (TextView) findViewById(R.id.tvTien);
    }

    public void setEvent(){
        // huong dan tro choi
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Hướng dẫn trò chơi:");
        builder.setMessage("Bạn có thể lắc điện thoại qua trái hoặc qua phải để thay thế việc nhấn nút chơi. Chúc bạn chơi game vui vẻ.");
        builder.setNegativeButton("Đã hiểu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
        buttonChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kiem tra con tien hay khong
                if (tienDangCo <= 0){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn đã hết xu. Bạn có muốn lắc thêm xu hay không???");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, LacXuActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
                // tinh so tien dat cuoc
                tienTru = tienNai + tienBau + tienGa + tienCa + tienCua + tienTom;
                // kiem tra da dat cuoc hay chua
                if (tienTru == 0){
                    Toast.makeText(MainActivity.this, "Bạn chưa đặt cược", Toast.LENGTH_SHORT).show();
                    return;
                }
                // kiem tra so tien dat cuoc
                if(tienTru > tienDangCo){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Số xu đang có của bạn không đủ để đặt cược. Vui lòng chỉnh sửa số xu đặt cược");
                    builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                    return;
                }

                int index1 = random();
                int index2 = random();
                int index3 = random();
                randomHinh(imageView1, index1);
                randomHinh(imageView2, index2);
                randomHinh(imageView3, index3);
                // tinh tien cong
                if(index1 == index2 && index1 == index3){
                    tienCong = tinhTienCongTrung3(index1);
                }else if(index1 == index2){
                    tienCong = tinhTienCongTrung2(index1) + tinhTienCongKhongTrung(index3);
                }else if(index1 == index3){
                    tienCong = tinhTienCongTrung2(index1) + tinhTienCongKhongTrung(index2);
                }else if(index2 == index3){
                    tienCong = tinhTienCongTrung2(index2) + tinhTienCongKhongTrung(index1);
                }else {
                    tienCong = tinhTienCongKhongTrung(index1) + tinhTienCongKhongTrung(index2) + tinhTienCongKhongTrung(index3);
                }
                tienDangCo = tienDangCo - tienTru + tienCong;
                textViewTien.setText(tienDangCo + "");
                tienNai = 0;
                tienBau = 0;
                tienGa = 0;
                tienCa = 0;
                tienCua = 0;
                tienTom = 0;
                spinnerNai.setSelection(0);
                spinnerBau.setSelection(0);
                spinnerGa.setSelection(0);
                spinnerCa.setSelection(0);
                spinnerCua.setSelection(0);
                spinnerTom.setSelection(0);
                // kiem tra con tien hay khong
                if (tienDangCo <= 0){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn đã hết xu. Bạn có muốn lắc thêm xu hay không???");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, LacXuActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            }
        });

        spinnerNai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tienNai = Integer.parseInt(spinnerNai.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerBau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tienBau = Integer.parseInt(spinnerBau.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerGa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tienGa = Integer.parseInt(spinnerGa.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tienCa = Integer.parseInt(spinnerCa.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCua.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tienCua = Integer.parseInt(spinnerCua.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tienTom = Integer.parseInt(spinnerTom.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setSensor(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
        accelerometor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometor, SensorManager.SENSOR_DELAY_NORMAL);
        vibrateThreshold = accelerometor.getMaximumRange() / 5;
    }
}

    public int random(){
        Random r = new Random();
        int i = r.nextInt(7 - 1) + 1;
        return i;
    }

    public void randomHinh(ImageView imageView, int i){
        switch (i){
            case 1:{
                imageView.setImageResource(R.drawable.nai);
            }
            break;
            case 2:{
                imageView.setImageResource(R.drawable.bau);
            }
            break;
            case 3:{
                imageView.setImageResource(R.drawable.conga);
            }
            break;
            case 4:{
                imageView.setImageResource(R.drawable.conca);
            }
            break;
            case 5:{
                imageView.setImageResource(R.drawable.concua);
            }
            break;
            case 6:{
                imageView.setImageResource(R.drawable.contom);
            }
            break;
        }
    }

    public int tinhTienCongKhongTrung(int index){
        int tien = 0;
        switch (index){
            case 1:{
                tien = tienNai * 2;
            }
            break;
            case 2:{
                tien = tienBau * 2;
            }
            break;
            case 3:{
                tien = tienGa * 2;
            }
            break;
            case 4:{
                tien = tienCa * 2;
            }
            break;
            case 5:{
                tien = tienCua * 2;
            }
            break;
            case 6:{
                tien = tienTom * 2;
            }
            break;
        }
        return tien;
    }

    public int tinhTienCongTrung2(int index){
        int tien = 0;
        switch (index){
            case 1:{
                tien = tienNai * 3;
            }
            break;
            case 2:{
                tien = tienBau * 3;
            }
            break;
            case 3:{
                tien = tienGa * 3;
            }
            break;
            case 4:{
                tien = tienCa * 3;
            }
            break;
            case 5:{
                tien = tienCua * 3;
            }
            break;
            case 6:{
                tien = tienTom * 3;
            }
            break;
        }
        return tien;
    }

    public int tinhTienCongTrung3(int index){
        int tien = 0;
        switch (index){
            case 1:{
                tien = tienNai * 4;
            }
            break;
            case 2:{
                tien = tienBau * 4;
            }
            break;
            case 3:{
                tien = tienGa * 4;
            }
            break;
            case 4:{
                tien = tienCa * 4;
            }
            break;
            case 5:{
                tien = tienCua * 4;
            }
            break;
            case 6:{
                tien = tienTom * 4;
            }
            break;
        }
        return tien;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            choi(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void choi(SensorEvent event){
        float deltaX = Math.abs(lastX - event.values[0]);

        if(deltaX > vibrateThreshold ){
            // kiem tra con tien hay khong
            if (tienDangCo <= 0) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn đã hết xu. Bạn có muốn lắc thêm xu hay không???");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, LacXuActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
            // tinh so tien dat cuoc
            tienTru = tienNai + tienBau + tienGa + tienCa + tienCua + tienTom;
            // kiem tra da dat cuoc hay chua
            if (tienTru == 0){
                Toast.makeText(MainActivity.this, "Bạn chưa đặt cược", Toast.LENGTH_SHORT).show();
                return;
            }
            // kiem tra so tien dat cuoc
            if(tienTru > tienDangCo){
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Số tiền đang có của bạn không đủ để đặt cược. Vui lòng chỉnh sửa số tiền đặt cược");
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                return;
            }

            int index1 = random();
            int index2 = random();
            int index3 = random();
            randomHinh(imageView1, index1);
            randomHinh(imageView2, index2);
            randomHinh(imageView3, index3);
            // tinh tien cong
            if(index1 == index2 && index1 == index3){
                tienCong = tinhTienCongTrung3(index1);
            }else if(index1 == index2){
                tienCong = tinhTienCongTrung2(index1) + tinhTienCongKhongTrung(index3);
            }else if(index1 == index3){
                tienCong = tinhTienCongTrung2(index1) + tinhTienCongKhongTrung(index2);
            }else if(index2 == index3){
                tienCong = tinhTienCongTrung2(index2) + tinhTienCongKhongTrung(index1);
            }else {
                tienCong = tinhTienCongKhongTrung(index1) + tinhTienCongKhongTrung(index2) + tinhTienCongKhongTrung(index3);
            }
            tienDangCo = tienDangCo - tienTru + tienCong;
            textViewTien.setText(tienDangCo + "");
            tienNai = 0;
            tienBau = 0;
            tienGa = 0;
            tienCa = 0;
            tienCua = 0;
            tienTom = 0;
            spinnerNai.setSelection(0);
            spinnerBau.setSelection(0);
            spinnerGa.setSelection(0);
            spinnerCa.setSelection(0);
            spinnerCua.setSelection(0);
            spinnerTom.setSelection(0);
            // kiem tra con tien hay khong
            if (tienDangCo <= 0){
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn đã hết xu. Bạn có muốn lắc thêm xu hay không???");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, LacXuActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        }

        lastX = event.values[0];
    }
}
