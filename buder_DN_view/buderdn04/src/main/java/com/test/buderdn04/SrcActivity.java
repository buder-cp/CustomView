package com.test.buderdn04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SrcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_src);
        findViewById(R.id.rounddstinbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllViews();
                findViewById(R.id.roundimage).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.NormalGestureTrackViewbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllViews();
                findViewById(R.id.NormalGestureTrackView).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.BezierGestureTrackViewbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllViews();
                findViewById(R.id.BezierGestureTrackView).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.eraserbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllViews();
                findViewById(R.id.EraserView_SRCOUTView).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.GuaGuaCardViewbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllViews();
                findViewById(R.id.GuaGuaCardView_SRCOUT).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.StripMeiZibtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAllViews();
                findViewById(R.id.StripMeiZi).setVisibility(View.VISIBLE);
            }
        });

    }

    private void hideAllViews() {
//        dts
        findViewById(R.id.roundimage).setVisibility(View.GONE);
        findViewById(R.id.NormalGestureTrackView).setVisibility(View.GONE);
        findViewById(R.id.BezierGestureTrackView).setVisibility(View.GONE);
        findViewById(R.id.EraserView_SRCOUTView).setVisibility(View.GONE);
        findViewById(R.id.GuaGuaCardView_SRCOUT).setVisibility(View.GONE);
        findViewById(R.id.StripMeiZi).setVisibility(View.GONE);

    }
}