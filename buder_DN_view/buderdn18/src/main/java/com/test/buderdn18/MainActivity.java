package com.test.buderdn18;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    MenuBgView menuBgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        menuBgView = new MenuBgView(this);
//        setContentView(menuBgView);

    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        menuBgView.setTouchY(event.getY(),0.8f);
//
//        return super.onTouchEvent(event);
//
//    }
}
