package com.example.administrator.androidmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.administrator.androidmap.utils.ExpandableListView.Group;
import com.example.administrator.androidmap.utils.ExpandableListView.Item;
import com.example.administrator.androidmap.utils.ExpandableListView.MyBaseExpandableListAdapter;
import com.example.administrator.androidmap.vo.TopicMapVO;

import java.util.ArrayList;
import java.util.List;

import com.example.administrator.androidmap.R;
public class MainActivity extends AppCompatActivity {

    //ExpandableListView
    private List<TopicMapVO> topicMapList;
    private ExpandableListView lvTopicMap;
    private MyBaseExpandableListAdapter mTopicMapAdapter;
    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * ExpandableListView 的使用
     * topicMapList 数据源
     * "topicList": [
     {
     "id": "33",
     "mapType": "1",
     "groupName": "土地和房产",
     "name": "厦门市预售楼盘专题图-点",
     "mapUrl": "http://mapapi.xmtfj.gov.cn/RemoteRest/services/YSLPT/MapServer",
     "queryEnabled": "1",
     "identifyEnabled": "1",
     "isEdit": "0",
     "geometryType": "0",
     "layerDefinitions": "[{\"layerId\":\"0\",\"whereClause\":\"OBJECTID<1000\"}]",
     "logoUrl": "",
     "appMapIcon": "",
     "appIsMapCluster": 0,
     "isAppTokenNeeded": 0,
     "appToken": null,
     "appReferer": null
     },
     {
     "id": "34",
     "mapType": "1",
     "groupName": "文化体育",
     "name": "厦门市交通公路专题图-线",
     "mapUrl": "http://mapapi.xmtfj.gov.cn/RemoteRest/services/TRA/MapServer",
     "queryEnabled": "1",
     "identifyEnabled": "1",
     "isEdit": "0",
     "geometryType": "0",
     "layerDefinitions": "[{\"layerId\":\"1\",\"whereClause\":\"OBJECTID<1000\"}]",
     "logoUrl": "",
     "appMapIcon": "",
     "appIsMapCluster": 0,
     "isAppTokenNeeded": 0,
     "appToken": null,
     "appReferer": null
     },
     {
     "id": "35",
     "mapType": "1",
     "groupName": "土地和房产",
     "name": "厦门市在建招拍挂专题图-面",
     "mapUrl": "http://mapapi.xmtfj.gov.cn/RemoteRest/services/ZJZPGDK/MapServer",
     "queryEnabled": "1",
     "identifyEnabled": "1",
     "isEdit": "0",
     "geometryType": "0",
     "layerDefinitions": "[{\"layerId\":\"0\",\"whereClause\":\"objectid<10\"},{\"layerId\":\"1\",\"whereClause\":\"objectid<0\"}]",
     "logoUrl": "",
     "appMapIcon": "",
     "appIsMapCluster": 0,
     "isAppTokenNeeded": 0,
     "appToken": null,
     "appReferer": null
     }]
     */
    private void ExpandableListView() {
        //数据准备
        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<Item>>();
        for (int i = 0, size = topicMapList.size(); i < size; i++) {
            boolean exist=false;
            for (int j = 0, length = gData.size(); j < length; j++) {
                if (gData.get(j).getgName().equals(topicMapList.get(i).getGroupName())) {
                    exist=true;
                    break;
                }
            }
            if(!exist){
                gData.add(new Group(topicMapList.get(i).getGroupName()));
            }
        }

        for (int i = 0, size = gData.size(); i < size; i++) {
            lData = new ArrayList<Item>();
            String groupName=gData.get(i).getgName();
            for (int j = 0, length = topicMapList.size(); j < length; j++) {
                if (groupName.equals(topicMapList.get(j).getGroupName())) {
                    lData.add(new Item(topicMapList.get(j)));
                }
            }
            iData.add(lData);
        }

        mTopicMapAdapter = new MyBaseExpandableListAdapter(gData,iData,this,this);
        lvTopicMap.setAdapter(mTopicMapAdapter);

    }


}
