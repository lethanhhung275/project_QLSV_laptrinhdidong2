package hungle.example.exe2_sensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setControl();
        setEvent();
    }

    public void setControl(){
        buttonBack = (Button) findViewById(R.id.btnBack);
    }

    public void setEvent(){
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
