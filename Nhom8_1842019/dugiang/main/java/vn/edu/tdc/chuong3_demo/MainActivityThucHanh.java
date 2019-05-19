package vn.edu.tdc.chuong3_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivityThucHanh extends AppCompatActivity {
    ImageView imAirPlane, imTruck, imMatTroi, imQuat, imNhayDen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thuchanh);
        setControl();
        setEvent();

    }

    private void setAnimationNhayDen() {
        Toast.makeText(this, "Animation:setAnimationNhayDen", Toast.LENGTH_SHORT).show();

    }

    private void setAnimationMatTroi() {
        Toast.makeText(this, "Animation:setAnimationMatTroi", Toast.LENGTH_SHORT).show();
    }

    private void setAnimationRose() {
        Toast.makeText(this, "Animation:setAnimationRose", Toast.LENGTH_SHORT).show();
    }

    private void setAnimationTruck() {
        Toast.makeText(this, "Animation:setAnimationTruck", Toast.LENGTH_SHORT).show();
    }

    private void setAnimationAirPlane() {
        Toast.makeText(this, "Animation:setAnimationAirPlane", Toast.LENGTH_SHORT).show();
    }

    public void setEvent() {
        imAirPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationAirPlane();
            }
        });

        imTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationTruck();
            }
        });


        imNhayDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationNhayDen();
            }
        });

        imQuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationRose();
            }
        });

        imMatTroi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationMatTroi();
            }
        });



    }

    void setControl() {
        imAirPlane = findViewById(R.id.imAirPlane);
        imTruck = findViewById(R.id.imTruck);
        imMatTroi = findViewById(R.id.imMatTroi);
        imQuat = findViewById(R.id.imQuat);
        imNhayDen = findViewById(R.id.imNhayDen);

    }
}
