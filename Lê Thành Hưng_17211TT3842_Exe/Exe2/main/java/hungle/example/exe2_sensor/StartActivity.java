package hungle.example.exe2_sensor;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class StartActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Handler handler;
    AtomicBoolean isrunning = new AtomicBoolean(false);
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setControl();
        setEvent();
    }

    public void setControl(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        textView = (TextView) findViewById(R.id.tvPhanTram);
    }

    public void setEvent(){
        doStart();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //msg.arg1 là giá trị được trả về trong message
                //của tiến trình con
                progressBar.setProgress(msg.arg1);
                textView.setText(msg.arg1 + "%");

            }

        };
    }


    private void doStart() {
        progressBar.setProgress(0);
        isrunning.set(false);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                //vòng lặp chạy 100 lần
                for (int i = 0; i <= 100 && isrunning.get(); i++) {
                    //cho tiến trình tạm ngừng 100 mili second
                    SystemClock.sleep(20);
                    //lấy message từ Main thread
                    Message msg = handler.obtainMessage();
                    //gán giá trị vào cho arg1 để gửi về Main thread
                    msg.arg1 = i;
                    //gửi lại Message này về cho Main Thread
                    handler.sendMessage(msg);
                    if(i==100){
                        Intent intent = new Intent(StartActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        isrunning.set(true);
        //kích hoạt tiến trình
        th.start();
    }
}
