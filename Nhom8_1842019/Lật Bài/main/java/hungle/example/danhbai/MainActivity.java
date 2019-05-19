package hungle.example.danhbai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3;
    Button btnChoi, btnCL;
    TextView tvDiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    public void setControl(){
        imageView1 = (ImageView) findViewById(R.id.image1);
        imageView2 = (ImageView) findViewById(R.id.image2);
        imageView3 = (ImageView) findViewById(R.id.image3);
        btnChoi = (Button) findViewById(R.id.btnChoi);
        btnCL = (Button) findViewById(R.id.btnCL);
        tvDiem = (TextView) findViewById(R.id.tvDiem);
    }

    public void setEvent(){
        btnChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lay la bai thu nhat
                int index1 = random();
                // lay la bai thu 2
                int index2 = 0;
                do {
                    index2 = random();
                }while (index2 == index1);
                // lay la bai thu 3
                int index3 = 0;
                do {
                    index3 = random();
                }while (index3 == index1 && index3 == index2);
                // set hinh
                randomHinh(imageView1, index1);
                randomHinh(imageView2, index2);
                randomHinh(imageView3, index3);
                // tinh diem
                int diem = 0;
                if (index1 < 10){
                    diem += index1;
                }

                if (index2 < 10){
                    diem += index2;
                }

                if (index3 < 10){
                    diem += index3;
                }
                // lay phan le
                diem = diem % 10;
                tvDiem.setText("Điểm của bạn: " + diem);
            }
        });

        btnCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lay so ngau nhien
                int index1 = random();
                int index2 = random();
                int index3 = random();
                // set hinh
                randomHinh(imageView1, index1);
                randomHinh(imageView2, index2);
                randomHinh(imageView3, index3);
                // tinh diem
                int diem = 0;
                if (index1 < 10){
                    diem += index1;
                }

                if (index2 < 10){
                    diem += index2;
                }

                if (index3 < 10){
                    diem += index3;
                }
                // lay phan le
                diem = diem % 10;
                tvDiem.setText("Điểm của bạn: " + diem);
            }
        });

    }

    public int random(){
        Random r = new Random();
        int i = r.nextInt(13 - 1) + 1;
        return i;
    }

    public void randomHinh(ImageView imageView, int i){
        switch (i){
            case 1:{
                imageView.setImageResource(R.drawable.mot);
            }
            break;
            case 2:{
                imageView.setImageResource(R.drawable.hai);
            }
            break;
            case 3:{
                imageView.setImageResource(R.drawable.ba);
            }
            break;
            case 4:{
                imageView.setImageResource(R.drawable.bon);
            }
            break;
            case 5:{
                imageView.setImageResource(R.drawable.nam);
            }
            break;
            case 6:{
                imageView.setImageResource(R.drawable.sau);
            }
            break;
            case 7:{
                imageView.setImageResource(R.drawable.bay);
            }
            break;
            case 8:{
                imageView.setImageResource(R.drawable.tam);
            }
            break;
            case 9:{
                imageView.setImageResource(R.drawable.chin);
            }
            break;
            case 10:{
                imageView.setImageResource(R.drawable.muoi);
            }
            break;
            case 11:{
                imageView.setImageResource(R.drawable.boi);
            }
            break;
            case 12:{
                imageView.setImageResource(R.drawable.dam);
            }
            break;
            case 13:{
                imageView.setImageResource(R.drawable.gia);
            }
            break;
        }
    }
}
