package com.example.administrator.androidmap.pages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.androidmap.Session.SessionManager;
import com.example.administrator.androidmap.utils.pulltorefreshswipemenulistview.PullToRefreshSwipeMenuListView;
import com.example.administrator.androidmap.utils.pulltorefreshswipemenulistview.pulltorefresh.interfaces.IXListViewListener;
import com.example.administrator.androidmap.utils.pulltorefreshswipemenulistview.swipemenu.interfaces.OnSwipeListener;
import com.example.administrator.androidmap.utils.pulltorefreshswipemenulistview.util.RefreshTime;
import com.example.administrator.androidmap.utils.swipemenu.BaseSwipListAdapter;
import com.example.administrator.androidmap.utils.swipemenu.SwipeMenu;
import com.example.administrator.androidmap.utils.swipemenu.SwipeMenuCreator;
import com.example.administrator.androidmap.utils.swipemenu.SwipeMenuItem;
import com.example.administrator.androidmap.utils.swipemenu.SwipeMenuListView;
import com.example.administrator.androidmap.vo.PlotVO;

import net.minidev.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Seven on 2016/11/21.
 */

public class PlotListActivity extends AppCompatActivity implements IXListViewListener {
    private final List<PlotVO> mPlotRecordsList = new ArrayList<PlotVO>();
    private TextView tvEmpty;
    private MyAdapter mAdapter;
    private PullToRefreshSwipeMenuListView pullToRefreshSwipeMenuListView;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //page
        setContentView(R.layout.activity_plot_list);
        //data
        List<PlotVO> list = new ArrayList<PlotVO>();
        for (int i = 0, size = list.size(); i < size; i++) {
            mPlotRecordsList.add(list.get(i));
        }
        //mPlotRecordsList.(Arrays.asList(list)); //= DataUtil.loadPlotRecordsList(this);

        //PullToRefreshExpandableListView
        pullToRefreshSwipeMenuListView = (PullToRefreshSwipeMenuListView) findViewById(R.id.lvPlotRecords);
        tvEmpty = (TextView) findViewById(R.id.tvEmpty);
        pullToRefreshSwipeMenuListView.setEmptyView(tvEmpty);
        mAdapter = new MyAdapter();
        pullToRefreshSwipeMenuListView.setAdapter(mAdapter);
        pullToRefreshSwipeMenuListView.setPullRefreshEnable(true);
        pullToRefreshSwipeMenuListView.setPullLoadEnable(true);
        pullToRefreshSwipeMenuListView.setXListViewListener(this);
        mHandler = new Handler();

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                openItem.setWidth(dp2px(PlotListActivity.this, 90));
                openItem.setTitle("打开");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setWidth(dp2px(PlotListActivity.this, 90));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        pullToRefreshSwipeMenuListView.setMenuCreator(creator);
        // step 2. listener item click event
        pullToRefreshSwipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0: // open
                        open(position);
                        break;
                    case 1:// delete
                        delete(position);
                        break;
                }
            }
        });
        pullToRefreshSwipeMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }

    /**
     * swipe Adapter
     */
    class MyAdapter extends BaseSwipListAdapter {

        @Override
        public int getCount() {
            return mPlotRecordsList.size();
        }

        @Override
        public PlotVO getItem(int position) {
            return mPlotRecordsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = LayoutInflater.from(PlotListActivity.this).inflate(R.layout.adapter_plot_list, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            PlotVO item = getItem(position);
            int imgId = R.drawable.icon_plot_point;
            switch (item.getId()) {
                case 1:
                    break;
                case 2:
                    break;
            }
            holder.ivPlotLogo.setImageResource(imgId);
            holder.tvName.setText(item.getTitle());
            //if(StringUtils.isEmpty(item.getContent())){
            // holder.tvDescription.setVisibility(View.GONE);// 隐藏会造成显示问题
            // }else{
            holder.tvDescription.setVisibility(View.VISIBLE);
            holder.tvDescription.setText(item.getContent());
            //}

            holder.vDiv.setVisibility(position == getCount() - 1 ? View.GONE : View.VISIBLE);
            return convertView;
        }

        @Override
        public boolean getSwipEnableByPosition(int position) {
            return true;
        }

        class ViewHolder {
            ImageView ivPlotLogo;
            View vDiv;
            TextView tvName;
            TextView tvDescription;

            public ViewHolder(View view) {
                vDiv = view.findViewById(R.id.vDiv);
                ivPlotLogo = (ImageView) view.findViewById(R.id.ivPlotLogo);
                tvName = (TextView) view.findViewById(R.id.tvName);
                tvDescription = (TextView) view.findViewById(R.id.tvDescription);
                view.setTag(this);
            }
        }
    }

    public static Intent createIntent(Activity context) {
        Intent intent = new Intent(context, PlotListActivity.class);
        return intent;
    }

    private void onLoad() {
        SessionManager session = SessionManager.getInstance();

        if (1 == session.getPlotPage()) {
            mPlotRecordsList.clear();
        }
        request();
        pullToRefreshSwipeMenuListView.setRefreshTime(RefreshTime.getRefreshTime(getApplicationContext()));
        pullToRefreshSwipeMenuListView.stopRefresh();
        pullToRefreshSwipeMenuListView.stopLoadMore();
    }

    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
                RefreshTime.setRefreshTime(getApplicationContext(), df.format(new Date()));
                SessionManager session = SessionManager.getInstance();
                session.setPlotPage(1);
                onLoad();
            }
        }, 2000);
    }

    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SessionManager session = SessionManager.getInstance();
                //异步，可能数据没有加载，又请求下一页，但是应该允许请求，第一次请求有9条，在第二次请求之前数据库又多了2条
                session.setPlotPage(session.getPlotPage() + 1);
                onLoad();

            }
        }, 2000);
    }

    private void delete(int position) {
        //DataUtil.deletePlot(PlotListActivity.this,mAdapter.getItem(position));
        mPlotRecordsList.remove(position);
        //DataUtil.savePlotRecordsList(PlotListActivity.this, mPlotRecordsList);
        mAdapter.notifyDataSetChanged();
    }

    private void open(int position) {
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

    /**
     * plot List 数据结构如下
     * "plotList": {
     "TotalCount": 11,
     "Data": [
     {
     "Id": 2920,
     "ShareDate": "2017-10-27T10:09:34",
     "SharerIp": "",
     "SharerCity": "",
     "ShareContent": "{\"extent\":{\"ymin\":24.334879209810907,\"ymax\":24.99717748951622,\"xmin\":117.96574599379119,\"spatialReference\":{\"wkid\":4490},\"xmax\":118.35364303396567},\"marker\":[{\"attributes\":{\"type\":\"text\",\"content\":\"\",\"id\":3,\"title\":\"文字1\"},\"geometry\":{\"spatialReference\":{\"wkid\":4490},\"y\":24.779061428618252,\"x\":118.10627801023028}}]}",
     "ShareLink": "http://localhost:2323/#/?2eyqQj",
     "ShareTitle": "天地图·厦门-地图分享",
     "ShareLogoUrl": "http://www.ztgis.com:8868/img/logov150.png",
     "DataSource": "android",
     "UserAccount": "admin",
     "title": "文字1",
     "UserId": 0
     },
     {
     "Id": 2919,
     "ShareDate": "2017-10-27T10:09:21",
     "SharerIp": "",
     "SharerCity": "",
     "ShareContent": "{\"extent\":{\"ymin\":24.334879209810907,\"ymax\":24.99717748951622,\"xmin\":117.96216099678308,\"spatialReference\":{\"wkid\":4490},\"xmax\":118.35005803695756},\"marker\":[{\"attributes\":{\"type\":\"distance\",\"content\":\"\",\"id\":1,\"title\":\"线3\"},\"geometry\":{\"spatialReference\":{\"wkid\":4490},\"paths\":[[[118.10913497906648,24.767585720284526],[118.04783146884759,24.512127089610857],[118.31742350910774,24.669994761048482],[118.262225511612,24.428164673945677],[118.19411050269339,24.695104559124538],[118.02203048737321,24.629087164866295],[118.16937402991405,24.46045575567548],[118.26473503363145,24.571680738888976]]]}}]}",
     "ShareLink": "http://localhost:2323/#/?NbUbma",
     "ShareTitle": "天地图·厦门-地图分享",
     "ShareLogoUrl": "http://www.ztgis.com:8868/img/logov150.png",
     "DataSource": "android",
     "UserAccount": "admin",
     "title": "线3",
     "UserId": 0
     },
     {
     "Id": 2918,
     "ShareDate": "2017-10-27T10:09:07",
     "SharerIp": "",
     "SharerCity": "",
     "ShareContent": "{\"extent\":{\"ymin\":24.335602316577624,\"ymax\":24.997900596282935,\"xmin\":117.9535459510796,\"spatialReference\":{\"wkid\":4490},\"xmax\":118.34144299125408},\"marker\":[{\"attributes\":{\"type\":\"distance\",\"content\":\"\",\"id\":1,\"title\":\"线2\"},\"geometry\":{\"spatialReference\":{\"wkid\":4490},\"paths\":[[[118.10162142384175,24.79951807652077],[118.02956291163774,24.642726713093584],[118.25174940751808,24.680758476880044],[118.14426798043606,24.4798359640378]]]}}]}",
     "ShareLink": "http://localhost:2323/#/?mU3iqa",
     "ShareTitle": "天地图·厦门-地图分享",
     "ShareLogoUrl": "http://www.ztgis.com:8868/img/logov150.png",
     "DataSource": "android",
     "UserAccount": "admin",
     "title": "线2",
     "UserId": 0
     }]
     */
    private void request() {
        SessionManager session = SessionManager.getInstance();

        JSONObject plotJSONObject = new JSONObject();

        int totalCount = Integer.parseInt(plotJSONObject.get("TotalCount").toString());
        List<JSONObject> poltList = (List<JSONObject>) plotJSONObject.get("Data");
        boolean exist = false;
        for (int i = 0, size = poltList.size(); i < size; i++) {
            exist = false;
            String s = poltList.get(i).get("ShareContent").toString();
            PlotVO vo = new PlotVO();
            vo.buildPlotVO(s, poltList.get(i).get("Id").toString(), poltList.get(i).get("ShareLink").toString(),
                    poltList.get(i).get("ShareTitle").toString(), poltList.get(i).get("ShareLogoUrl").toString());
            for (int j = 0, length = mPlotRecordsList.size(); j < length; j++) {
                if (mPlotRecordsList.get(j).getPlotId() == vo.getPlotId()) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                mPlotRecordsList.add(vo);
            }
        }
        //刷新页面
        mAdapter.notifyDataSetChanged();
        //判断是否是异步请求后面的数据
        if (poltList.size() == 0) {
            session.setPlotPage(session.getPlotPage() - 1);
        }
        //塞回本地
        JSONObject sessionObject = new JSONObject();
        sessionObject.put("TotalCount", totalCount);
        sessionObject.put("Data", mPlotRecordsList);
        // SPUtils.put(PlotListActivity.this, AppConstant.AUTH_PLOT_LIST, SpParseUtil.encrypt(sessionObject.toJSONString()));

    }

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

}
