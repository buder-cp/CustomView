package com.test.cp3_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mEvaluatorLearn;

    private Button mMenuButton;
    private Button mItemButton1;
    private Button mItemButton2;
    private Button mItemButton3;
    private Button mItemButton4;
    private Button mItemButton5;

    private boolean mIsMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEvaluatorLearn = findViewById(R.id.evaluator_learn);
        mEvaluatorLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EvaluatorLearnActivity.class);
                startActivity(intent);

            }
        });

        initView();
    }

    private void initView() {
        mMenuButton = findViewById(R.id.menu);
        mMenuButton.setOnClickListener(this);

        mItemButton1 = findViewById(R.id.item1);
        mItemButton1.setOnClickListener(this);

        mItemButton2 = findViewById(R.id.item2);
        mItemButton2.setOnClickListener(this);

        mItemButton3 = findViewById(R.id.item3);
        mItemButton3.setOnClickListener(this);

        mItemButton4 = findViewById(R.id.item4);
        mItemButton4.setOnClickListener(this);

        mItemButton5 = findViewById(R.id.item5);
        mItemButton5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (!mIsMenuOpen) {
            mIsMenuOpen = true;
            openMenu();
        } else {
            Toast.makeText(this, "你点击了" + v, Toast.LENGTH_SHORT).show();
            mIsMenuOpen = false;
            closeMenu();
        }
    }

    private void openMenu() {
        doAnimateOpen(mItemButton1, 0, 5, 300);
        doAnimateOpen(mItemButton2, 1, 5, 300);
        doAnimateOpen(mItemButton3, 2, 5, 300);
        doAnimateOpen(mItemButton4, 3, 5, 300);
        doAnimateOpen(mItemButton5, 4, 5, 300);
    }

    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double hudu = Math.toRadians(90) / (total - 1) * index;
        float translationX = -(float) Math.sin(hudu) * radius;
        float translationY = -(float) Math.cos(hudu) * radius;

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1));

        set.setDuration(500).start();


    }

    private void closeMenu() {
        doAnimateClose(mItemButton1, 0, 5, 300);
        doAnimateClose(mItemButton2, 1, 5, 300);
        doAnimateClose(mItemButton3, 2, 5, 300);
        doAnimateClose(mItemButton4, 3, 5, 300);
        doAnimateClose(mItemButton5, 4, 5, 300);
    }

    private void doAnimateClose(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double hudu = Math.toRadians(90) / (total - 1) * index;
        float translationX = -(float) Math.sin(hudu) * radius;
        float translationY = -(float) Math.cos(hudu) * radius;

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));

        set.setDuration(500).start();
    }
}
