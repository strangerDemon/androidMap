package com.example.administrator.androidmap.utils.ExpandableListView;

import com.example.administrator.androidmap.vo.TopicMapVO;

/**
 * Created by Jay on 2015/9/25 0025.
 */
public class Item {

    private int iId;
    private String iName;
    private com.example.administrator.androidmap.vo.TopicMapVO topicMapVO;
    public Item() {
    }

    public Item(TopicMapVO topicMapVO) {
        this.topicMapVO=topicMapVO;
        this.iId = Integer.parseInt(topicMapVO.getId());
        this.iName = topicMapVO.getName();
    }

    public int getiId() {
        return iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public TopicMapVO getTopicMapVO() {
        return topicMapVO;
    }

    public void setTopicMapVO(TopicMapVO topicMapVO) {
        this.topicMapVO = topicMapVO;
    }
}
