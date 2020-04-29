package com.test.buderdn04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class DstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dst);

        findViewById(R.id.rounddstinbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllViews();
                findViewById(R.id.roundimage).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.irregularwavebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllViews();
                findViewById(R.id.irregularwaveview).setVisibility(View.VISIBLE);
            }
        });

    }

    private void hideAllViews() {
//        dts
        findViewById(R.id.roundimage).setVisibility(View.GONE);
        findViewById(R.id.irregularwaveview).setVisibility(View.GONE);
    }
}
