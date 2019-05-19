package vn.edu.tdc.chuong3_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivityDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo);
    }


    public void FadeIn(View view) {
        Toast.makeText(this, "Animation:FadeIn", Toast.LENGTH_SHORT).show();
    }

    public void FadeOut(View view) {
        Toast.makeText(this, "Animation:FadeOut", Toast.LENGTH_SHORT).show();
    }

    public void Blink(View view) {
        Toast.makeText(this, "Animation:FadeOut", Toast.LENGTH_SHORT).show();
    }

    public void ZoomIn(View view) {
        Toast.makeText(this, "Animation:ZoomIn", Toast.LENGTH_SHORT).show();
    }

    public void ZoomOut(View view) {
        Toast.makeText(this, "Animation:ZoomOut", Toast.LENGTH_SHORT).show();
    }

    public void Move(View view) {
        Toast.makeText(this, "Animation:Move", Toast.LENGTH_SHORT).show();
    }

    public void SlideDown(View view) {
        Toast.makeText(this, "Animation:SlideDown", Toast.LENGTH_SHORT).show();
    }
    public void SlideUp(View view) {
        Toast.makeText(this, "Animation:SlideUp", Toast.LENGTH_SHORT).show();
    }
    public void Rotate(View view) {
        Toast.makeText(this, "Animation:Rotate", Toast.LENGTH_SHORT).show();
    }


}
