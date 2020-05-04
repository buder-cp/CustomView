package com.test.buderdn1920;

import android.view.View;

import java.util.Stack;

public class Recycler {


    private Stack<View>[] views;

    //打造一个回收池
    public Recycler(int typeNumber) {
//        实例化一个       栈  数组
        views = new Stack[typeNumber];
        for (int i = 0; i < typeNumber; i++) {
            views[i] = new Stack<View>();
        }
    }

    public void addRecycledView(View view, int type) {
//一直在调用
        views[type].push(view);
    }

    public View getRecyclerView(int type) {
//        只关心  取到的View是对应的类型
        try {
            return views[type].pop();
        } catch (Exception e) {
            return null;
        }

    }

}
