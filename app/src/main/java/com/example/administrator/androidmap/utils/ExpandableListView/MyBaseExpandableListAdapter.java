package com.example.administrator.androidmap.utils.ExpandableListView;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.androidmap.MainActivity;
import com.example.administrator.androidmap.vo.TopicMapVO;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.androidmap.R.id.vDiv;
/**
 * Created by Jay on 2015/9/25 0025.
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {

    private ArrayList<Group> gData;
    private ArrayList<ArrayList<Item>> iData;
    private Context mContext;
    private List<TopicMapVO> mTempSelectedTopicMapList = new ArrayList<TopicMapVO>();
    private MainActivity mhomeAcitvity;

    public MyBaseExpandableListAdapter(ArrayList<Group> gData, ArrayList<ArrayList<Item>> iData, Context mContext, MainActivity mhomeAcitvity) {
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
        this.mhomeAcitvity=mhomeAcitvity;
    }

    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public Group getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Item getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.adapter_topic_map_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getgName());
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView =  LayoutInflater.from(mContext).inflate(R.layout.adapter_topic_map_switch, null);
        }
        final TopicMapVO entry=iData.get(groupPosition).get(childPosition).getTopicMapVO();
        ImageView ivTopicMapLogo = (ImageView) convertView.findViewById(R.id.ivTopicMapLogo);
        TextView tvTopicMapName = (TextView) convertView.findViewById(R.id.tvTopicMapName);
        convertView.findViewById(vDiv).setVisibility(isLastChild ? View.GONE : View.VISIBLE);
        final LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.layout_topic);

        //onclick 点击跳转专题图详情
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setEnabled(true);
                    }
                }, 500);
                //todo 点击跳转专题图详情
            }
        });
        //switch
        final ImageView ivSwitchOn = (ImageView) convertView.findViewById(R.id.ivSwitchOn);
        final ImageView ivSwitchOff = (ImageView) convertView.findViewById(R.id.ivSwitchOff);
        if (mTempSelectedTopicMapList.contains(iData.get(groupPosition).get(childPosition).getTopicMapVO())) {
            ivSwitchOn.setVisibility(View.VISIBLE);
            ivSwitchOff.setVisibility(View.GONE);
        } else {
            ivSwitchOn.setVisibility(View.GONE);
            ivSwitchOff.setVisibility(View.VISIBLE);
        }
        convertView.findViewById(R.id.linSwitch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTempSelectedTopicMapList.contains(iData.get(groupPosition).get(childPosition).getTopicMapVO())) {
                    mTempSelectedTopicMapList.remove(iData.get(groupPosition).get(childPosition).getTopicMapVO());
                    ivSwitchOn.setVisibility(View.GONE);
                    ivSwitchOff.setVisibility(View.VISIBLE);
                } else {
                    mTempSelectedTopicMapList.add(iData.get(groupPosition).get(childPosition).getTopicMapVO());
                    ivSwitchOn.setVisibility(View.VISIBLE);
                    ivSwitchOff.setVisibility(View.GONE);
                }
            }
        });
        //load image
        //mhomeAcitvity.loadImage(entry.getLogoUrl(), ivTopicMapLogo);
        tvTopicMapName.setText(entry.getName());

        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private static class ViewHolderGroup{
        private TextView tv_group_name;
    }

    private static class ViewHolderItem{
        private ImageView img_icon;
        private TextView tv_name;
    }

    public List<TopicMapVO> getmTempSelectedTopicMapList() {
        return mTempSelectedTopicMapList;
    }

    public void setmTempSelectedTopicMapList(List<TopicMapVO> mTempSelectedTopicMapList) {
        this.mTempSelectedTopicMapList = mTempSelectedTopicMapList;
    }

    public void clearmTempSelectedTopicMapList(){
        this.mTempSelectedTopicMapList.clear();
    }
}
