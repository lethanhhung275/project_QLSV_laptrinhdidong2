package hungle.example.vehinh;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    DrawingView drawingView;
    Button btnTop, btnBottom, btnLeft, btnRight;
    SensorManager sensorManager;
    private Sensor accelerometor;
    private float vibrateThreshold = 0;

    float lastX, lastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setSensor();
        //setEvent();
    }

    public void setControl() {
        drawingView = (DrawingView) findViewById(R.id.drawing);
        btnTop = (Button) findViewById(R.id.btnTop);
        btnBottom = (Button) findViewById(R.id.btnBottom);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);
    }

    public void setEvent() {
        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(1);
            }
        });

        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(2);
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(3);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.move(4);
            }
        });


    }

    public void doiHinh(SensorEvent event) {
        float deltaX = Math.abs(lastX - event.values[0]);
        float deltaY = Math.abs(lastY - event.values[1]);

        if (deltaX > vibrateThreshold) {
            drawingView.move(3);

        }

        if (deltaX < vibrateThreshold) {
            drawingView.move(4);

        }
        if (deltaY > vibrateThreshold) {
            drawingView.move(1);
        }

        if (deltaY < vibrateThreshold) {
            drawingView.move(2);
        }

        lastX = event.values[0];
        lastY = event.values[1];
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            doiHinh(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void setSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometor, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometor.getMaximumRange() / 20;
        }
    }
}
