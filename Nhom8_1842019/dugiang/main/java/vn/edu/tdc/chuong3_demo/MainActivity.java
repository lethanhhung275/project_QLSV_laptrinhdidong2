package vn.edu.tdc.chuong3_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void doStart(View view)
    {
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();

    }
}
