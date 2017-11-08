package com.example.administrator.androidmap.utils.pulltorefreshswipemenulistview.swipemenu.interfaces;

import com.besprout.android.base.widget.pulltorefreshswipemenulistview.swipemenu.bean.SwipeMenu;
import com.besprout.android.base.widget.pulltorefreshswipemenulistview.swipemenu.view.SwipeMenuView;


public interface OnSwipeItemClickListener {
    void onItemClick(SwipeMenuView view, SwipeMenu menu, int index);
}