package com.test.buderdn23_layoutmanager;

import android.graphics.Rect;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class AutoLayoutManager extends RecyclerView.LayoutManager {
    //保存所有item偏移量信息
    //所有数据高度和
    private int totalHeight = 0;
    private SparseArray<Rect> allItemframs = new SparseArray<>();

    /**
     * 滑动偏移量
     * 如果是正的就是在向上滑，展现上面的view
     * 如果是负的向下
     */
    private int verticalScrollOffset = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //摆放
        if (getItemCount() <= 0) {
            return;
        }
        //preLayout主要支持动画，直接跳过
        if (state.isPreLayout()) {
            return;
        }
        //将视图分离放入scrap缓存中，以准备重新对view进行排版
        detachAndScrapAttachedViews(recycler);

        int offsetY = 0;
        int offsetX = 0;
        int viewH = 0;
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            //需要将当前view添加后再进行侧量宽高
            addView(view);
            measureChildWithMargins(view, 0, 0);

            //系统测量完成宽高后，获取宽高
            int w = getDecoratedMeasuredWidth(view);
            int h = getDecoratedMeasuredHeight(view);
            viewH = h;
            Rect fram = allItemframs.get(i);
            if (fram == null) {
                fram = new Rect();
            }
            //之前累积的宽度加上当前item的宽度>recyclerView的宽度，则需要换行
            if (offsetX + w > getWidth()) {
                //换行的View的值
                offsetY += h;
                offsetX = w;
                fram.set(0, offsetY, w, offsetY + h);
            } else {
                //不需要换行
                fram.set(offsetX, offsetY, offsetX + w, offsetY + h);
                offsetX += w;
            }
            //要针对于当前View   生成对应的Rect  然后放到allItemframs数组
            allItemframs.put(i, fram);
        }
        totalHeight = offsetY + viewH;
        //detach 轻量级的移除操作；remove 重量级的移除操作
        //回收不可见的
        recyclerViewFillView(recycler, state);
    }

    private void recyclerViewFillView(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //清空RecyclerView的子View
        detachAndScrapAttachedViews(recycler);
        Rect phoneFrame = new Rect(0, verticalScrollOffset, getWidth(), verticalScrollOffset + getHeight());
        //将滑出屏幕的view进行回收
        Rect childRect = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            Rect child = allItemframs.get(i);
            if (!Rect.intersects(phoneFrame, child)) {
                removeAndRecycleView(childView, recycler);
            }
        }

        //可见区域出现在屏幕上的子view
        for (int j = 0; j < getItemCount(); j++) {
            if (Rect.intersects(phoneFrame, allItemframs.get(j))) {
                //scrap回收池里面拿的
                View scrap = recycler.getViewForPosition(j);
                measureChildWithMargins(scrap, 0, 0);
                addView(scrap);
                Rect frame = allItemframs.get(j);
                layoutDecorated(scrap, frame.left, frame.top - verticalScrollOffset,
                        frame.right, frame.bottom - verticalScrollOffset);
            }
        }
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        //实际滑动距离  dx
        int trval = dy;
        //如果滑动到最顶部  往下滑   verticalScrollOffset   -
        //第一个坐标值 减 最后一个坐标值  //记死
        if (verticalScrollOffset + dy < 0) {
            trval = -verticalScrollOffset;
        } else if (verticalScrollOffset + dy > totalHeight - getHeight()) {
            //如果滑动到最底部  往上滑   verticalScrollOffset   +
            trval = totalHeight - getHeight() - verticalScrollOffset;
        }

        //边界值判断
        verticalScrollOffset += trval;

        //平移容器内的item
        offsetChildrenVertical(trval);
        recyclerViewFillView(recycler, state);
        return trval;
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

}
