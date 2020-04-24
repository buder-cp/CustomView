package com.test.cp3_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EvaluatorLearnActivity extends AppCompatActivity {

    TextView mCharId;
    ValueAnimator valueAnimator;

    Button ValueAnimatorBtn;
    Button ObjectAnimatorBtn;
    FallingBallImageView ball_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluator_learn);

        ValueAnimatorBtn = findViewById(R.id.ValueAnimatorBtn);
        ObjectAnimatorBtn = findViewById(R.id.ObjectAnimatorBtn);
        ball_img = findViewById(R.id.ball_img);
        mCharId = findViewById(R.id.charID);
        initOnClickListener();
    }

    private void initOnClickListener() {
        ValueAnimatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doValueAnimatorBtn();
            }
        });

        ObjectAnimatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doObjectAnimatorBtn();
            }
        });
    }


    private void doObjectAnimatorBtn() {
        ObjectAnimator animator = ObjectAnimator.ofObject(ball_img, "fallingPos", new FallingBallEvaluator(), new Point(0, 0), new Point(500, 500));
//        ObjectAnimator animator = ObjectAnimator.ofObject(ball_img, "fallingPos", new FallingBallEvaluator(), new Point(500, 500));
        animator.setDuration(2000);
        animator.start();
    }

    private void doValueAnimatorBtn() {
        valueAnimator = ValueAnimator.ofObject(new CharEvaluator(), 'A', 'Z');
        valueAnimator.setDuration(6000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char text = (char) animation.getAnimatedValue();
                mCharId.setText(String.valueOf(text));
            }
        });
        valueAnimator.start();
    }
}
