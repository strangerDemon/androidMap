package com.example.administrator.androidmap.utils.swipemenu;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Seven on 2016/7/12.
 */
public class MeasuredSwipeMenListView extends SwipeMenuListView {
    public MeasuredSwipeMenListView(Context context) {
        super(context);
    }

    public MeasuredSwipeMenListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MeasuredSwipeMenListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
