package com.huusoat.qlsv;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ThemeActivity extends AppCompatActivity {
    Button btnNext;
    private AdView av;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_theme);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemeActivity.this, MainActivity.class) ;
                startActivity(intent);
            }
        });

        av = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        av.loadAd(adRequest);
    }
    @Override
    public void onPause() {
        if (av != null) {
            av.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (av != null) {
            av.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (av != null) {
            av.destroy();
        }
        super.onDestroy();
    }
}

