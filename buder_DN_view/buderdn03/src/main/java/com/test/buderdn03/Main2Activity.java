package com.test.buderdn03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        Log.d(TAG, "onCreate start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            Log.d(TAG, "onPause 准备sleep9秒");
            Thread.sleep(9000);
            Log.d(TAG, "onPause sleep9秒完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop start");
        try {
            Log.d(TAG, "onStop 准备sleep9秒");
            Thread.sleep(9000);
            Log.d(TAG, "onStop sleep9秒完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        Button btnTest = findViewById(R.id.btn_test);
        testText = findViewById(R.id.tv_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testSleep();
            }
        });

    }

    public void testSleep() {
        //10s之后本应该进行更新ui操作，但是由于此时主线程处于休眠状态，因此待主线程结束休眠之后才会进行更新ui操作

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "准备更新text");
                testText.setText("update btn text");
                Log.d(TAG, "更新text完成");
            }
        }, 10000);


        try {
            Log.d(TAG, "准备sleep30秒");
            Thread.sleep(30000);
            Log.d(TAG, "sleep30秒完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "first update");
        testText.setText("This is the first update");

    }
}
