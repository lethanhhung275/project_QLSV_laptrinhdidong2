package hungle.example.demo3;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    SensorManager sensorManager;
    private Sensor accelerometor;
    private float vibrateThreshold = 0;
    LinearLayout view;
    ImageView imgHinh;

    private  float lastX, lastY, lastZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setSensor();
    }
    public void setControl(){
        view = (LinearLayout) findViewById(R.id.linearLayout);
        imgHinh = (ImageView) findViewById(R.id.imageView);
    }

    public void setEvent(){
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
            doiHinh(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void doiHinh(SensorEvent event){
        float deltaX = Math.abs(lastX - event.values[0]);
        float deltaY = Math.abs(lastY - event.values[1]);
        float deltaZ = Math.abs(lastZ - event.values[2]);

        if(deltaX > vibrateThreshold ){
            imgHinh.setImageResource(R.drawable.dt1);
            view.setBackgroundColor(Color.RED);

        }

        if(deltaY > vibrateThreshold ){
            imgHinh.setImageResource(R.drawable.dt2);
            view.setBackgroundColor(Color.BLUE);

        }

        if(deltaZ > vibrateThreshold ){
            imgHinh.setImageResource(R.drawable.dt3);
            view.setBackgroundColor(Color.YELLOW);

        }

        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];
    }
}
