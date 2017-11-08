package com.example.administrator.androidmap.vo;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seven on 2016/11/9.
 */

public class TopicMapVO extends BaseVo {
    /**
     * id : 1
     * mapType : 2
     * name : 厦门市楼盘专题图（聚合）
     * mapUrl : http://mapapi.xmtfj.gov.cn/RemoteRest/services/YSLPT/FeatureServer/0
     * queryEnabled : 1
     * identifyEnabled : 1
     */

    public static final String MAP_TYPE_TILED = "0",//切片地图服务
                                 MAP_TYPE_VECTOR = "1",//矢量动态地图服务
                                 MAP_TYPE_FEATURE = "2",//要素服务
                                 MAP_TYPE_PICTURE = "3";//图片专题

    public static final String QUERY_ENABLED_YES = "1",
                                  QUERY_ENABLED_NO = "2";
    private String id;
    private String mapType;
    private String geometryType;//地图类型  1 点 2 线 3 面
    private String name;
    private String mapUrl;
    private String queryEnabled;
    private String identifyEnabled;
    private String isEdit;
    private String logoUrl;
    private String appMapIcon;
    private String appIsMapCluster;
    private String isAppTokenNeeded;//是否安全服务
    //way 1
    private String appToken;
    private String appReferer;
    private String layerDefinitions;
    private String groupName;

    public static TopicMapVO parseTopicMapVO(JSONObject jsonObject) {
        TopicMapVO result = new TopicMapVO();
        fillIt(result, jsonObject);
        return result;
    }

    public static List<TopicMapVO> parseTopicMapVOList(JSONArray jsonArray) {
        List<TopicMapVO> list = new ArrayList<>();
        if (jsonArray != null && jsonArray.size() > 0) {
            for (Object obj : jsonArray) {
                list.add(parseTopicMapVO((JSONObject) obj));
            }
        }
        return list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public String getGeometryType() {
        return geometryType;
    }

    public void setGeometryType(String geometryType) {
        this.geometryType = geometryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getQueryEnabled() {
        return queryEnabled;
    }

    public void setQueryEnabled(String queryEnabled) {
        this.queryEnabled = queryEnabled;
    }

    public String getIdentifyEnabled() {
        return identifyEnabled;
    }

    public void setIdentifyEnabled(String identifyEnabled) {
        this.identifyEnabled = identifyEnabled;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAppMapIcon() {
        return appMapIcon;
    }

    public void setAppMapIcon(String appMapIcon) {
        this.appMapIcon = appMapIcon;
    }

    public String getAppIsMapCluster() {
        return appIsMapCluster;
    }

    public void setAppIsMapCluster(String appIsMapCluster) {
        this.appIsMapCluster = appIsMapCluster;
    }

    public String getIsAppTokenNeeded() {
        return isAppTokenNeeded;
    }

    public void setIsAppTokenNeeded(String isAppTokenNeeded) {
        this.isAppTokenNeeded = isAppTokenNeeded;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getAppReferer() {
        return appReferer;
    }

    public void setAppReferer(String appReferer) {
        this.appReferer = appReferer;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getLayerDefinitions() {
        return layerDefinitions;
    }

    public void setLayerDefinitions(String layerDefinitions) {
        this.layerDefinitions = layerDefinitions;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
