package com.test.buderdn03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //自定义雷达view
        setContentView(new RadarGradientView(this));


        /**
         * 自定义的文字闪光view
         */
//        setContentView(R.layout.activity_main);
//
//        txt1 = findViewById(R.id.text1);
//        txt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Main2Activity.class));
//            }
//        });
    }
}
