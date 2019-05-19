package hungle.example.exe2_sensor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class LacXuActivity extends AppCompatActivity implements SensorEventListener {

    ImageView imageViewTienRoi;

    // Sensor
    SensorManager sensorManager;
    private Sensor accelerometor;
    private float vibrateThreshold = 0;
    private  float lastY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lac_xu);

        setControl();
        setSensor();
        setEvent();

    }

    public void setControl(){
        imageViewTienRoi = (ImageView) findViewById(R.id.imageTienRoi);
    }

    public void setEvent(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(LacXuActivity.this);
        builder.setTitle("Hướng dẫn:");
        builder.setMessage("Bạn lắc điện thoại theo hướng lên trên hoặc xuống dưới để nhận thêm xu nhé.");
        builder.setNegativeButton("Đã hiểu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    private void setSensor(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            accelerometor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometor, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometor.getMaximumRange() / 5;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            lacXu(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void lacXu(SensorEvent event){
        float deltaY = Math.abs(lastY - event.values[1]);

        if(deltaY > vibrateThreshold ){
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.nhapnhay);
            imageViewTienRoi.startAnimation(animation);
            final AlertDialog.Builder builder = new AlertDialog.Builder(LacXuActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Chúc mừng bạn nhận được 2000xu. Chúc bạn chơi game vui vẻ.");
            MainActivity.tienDangCo += 500;
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(LacXuActivity.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            });
            builder.show();
        }

        lastY = event.values[1];
    }
}
