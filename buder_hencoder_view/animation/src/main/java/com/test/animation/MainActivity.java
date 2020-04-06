package com.test.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

import com.test.animation.view.CircleView;
import com.test.animation.view.FancyFlipView;
import com.test.animation.view.PointView;
import com.test.animation.view.ProvinceUtil;
import com.test.animation.view.ProvinceView;

public class MainActivity extends AppCompatActivity {

    ProvinceView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.view);

        /**
         * view 自带的动画，不能够进行定制，是一些动画的简单组合，使用方便
         */
        /*view.animate()
                .translationX(Utils.dp2px(200))
                .translationY(100)
                .rotation(180)
                .alpha(0.5f)
                .setStartDelay(1000)
                .start();*/

        /**
         * 自定义属性
         */
        /*ObjectAnimator animator = ObjectAnimator.ofFloat(view, "radius",
                Utils.dpToPixel(100), Utils.dpToPixel(10), Utils.dpToPixel(150));
        animator.setStartDelay(1000);
        animator.setDuration(3000);
        animator.start();*/

        /**
         * 自定义属性动画集合
         */
//        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 45);
//        bottomFlipAnimator.setDuration(1500);
//
//        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 270);
//        flipRotationAnimator.setDuration(1500);
//
//        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", - 45);
//        topFlipAnimator.setDuration(1500);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator);
//        animatorSet.setStartDelay(1000);
//        animatorSet.start();

        /*PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 45);
        PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", 270);
        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", - 45);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, bottomFlipHolder, flipRotationHolder, topFlipHolder);
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(2000);
        objectAnimator.start();*/

        /*float length = Utils.dpToPixel(300);
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 1.5f * length);
        Keyframe keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * length);
        Keyframe keyframe4 = Keyframe.ofFloat(1, 1 * length);
        PropertyValuesHolder viewHolder = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3, keyframe4);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, viewHolder);
        animator.setStartDelay(1000);
        animator.setDuration(2000);
        animator.start();*/

        /**
         * 自定义插值器
         */
        /*Point targetPoint = new Point((int) Utils.dpToPixel(300), (int) Utils.dpToPixel(200));
        ObjectAnimator animator = ObjectAnimator.ofObject(view, "point", new PointEvaluator(), targetPoint);
        animator.setStartDelay(1000);
        animator.setDuration(2000);
        animator.start();*/

        /**
         * 自定义文字变换插值器
         */
        ObjectAnimator animator = ObjectAnimator.ofObject(view, "province", new ProvinceEvaluator(), "澳门特别行政区");
        animator.setDuration(10000);
        animator.start();
    }

    class PointEvaluator implements TypeEvaluator<Point> {
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            // (1, 1)   (5, 5)     fraction: 0.2   x: 1 + (5 - 1) * 0.2 y: 1 + (5 - 1) * 0.2
            float x = startValue.x + (endValue.x - startValue.x) * fraction;
            float y = startValue.y + (endValue.y - startValue.y) * fraction;
            return new Point((int) x, (int) y);
        }
    }

    class ProvinceEvaluator implements TypeEvaluator<String> {
        @Override
        public String evaluate(float fraction, String startValue, String endValue) {
            // 北京市      上海市       fraction 0.5f
            int startIndex = ProvinceUtil.provinces.indexOf(startValue);
            int endIndex = ProvinceUtil.provinces.indexOf(endValue);
            int index = (int) (startIndex + (endIndex - startIndex) * fraction);
            return ProvinceUtil.provinces.get(index);
        }
    }

}
