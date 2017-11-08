package com.example.administrator.androidmap.vo;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

/**
 * Created by Seven on 2016/11/21.
 */

public class PlotVO extends Response{

    private int id;//类型id
    private String plotId;//数据库id
    private String title;
    private String content;
    private String type;
    private String geometry;
    private String extent;
    private String shareUrl;
    private String shareTitle;
    private String shareLogoUrl;

//    /**
//     * 除了分享信息外其他是否全部一致
//     * @param vo
//     * @return
//     */
//    public boolean isSameWhitoutShareInfo(PlotVO vo){
//        if(id != vo.getId() || !isSameString(title, vo.getTitle())
//                || !isSameString(content, vo.getContent())
//                || !isSameString(type, vo.getType())
//                || !isSameString(geometry, vo.getGeometry())
//                || !isSameString(extent, vo.getExtent())){
//            return false;
//        }
//        return true;
//    }
//    private boolean isSameString(String s1, String s2){
//        if(null == s1){
//            return null == s2;
//        }
//        return  s1.equals(s2);
//    }
    public PlotVO(){

    }
    public PlotVO(int id, String title, String content, String geometry, String extent){
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = id+"";//类型type，这里无用
        this.geometry = geometry;
        this.extent = extent;
    }

    public PlotVO(int id, String plotId, String title, String content, String type, String geometry, String extent, String shareUrl, String shareTitle, String shareLogoUrl) {
        this.id = id;
        this.plotId = plotId;
        this.title = title;
        this.content = content;
        this.type = type;
        this.geometry = geometry;
        this.extent = extent;
        this.shareUrl = shareUrl;
        this.shareTitle = shareTitle;
        this.shareLogoUrl = shareLogoUrl;
    }

    public PlotVO(JSONObject plot){
        this.id = Integer.parseInt(plot.get("id").toString());
        this.plotId = plot.get("plotId").toString();
        this.title = plot.get("title").toString();
        this.content = plot.get("content").toString();
        this.type = plot.get("type").toString();
        this.geometry = plot.get("geometry").toString();
        this.extent = plot.get("extent").toString();
        this.shareUrl = plot.get("shareUrl").toString();
        this.shareTitle = plot.get("shareTitle").toString();
        this.shareLogoUrl = plot.get("shareLogoUrl").toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareLogoUrl() {
        return shareLogoUrl;
    }

    public void setShareLogoUrl(String shareLogoUrl) {
        this.shareLogoUrl = shareLogoUrl;
    }

    /**
     * shaerContent 内不再保存标会分享信息
     * @param s
     * @param plotId
     * @param ShareLink
     */
    public void buildPlotVO(String s, String plotId, String ShareLink, String ShareTitle, String ShareLogoUrl){
        if("".equals(s))return;
        JSONObject jMapInfo = (JSONObject) JSONValue.parse(s);
        JSONObject jMT = (JSONObject) jMapInfo.get("MapInfo");
        JSONArray jArr;
        if(null==jMT){
            jArr = (JSONArray) jMapInfo.get("marker");
            this.extent = jMapInfo.get("extent").toString();
        }else{
            jArr = (JSONArray) jMT.get("marker");
            this.extent = getStringValue("extent", jMT);
        }
        JSONObject jGA = (JSONObject)jArr.get(0);
//        JSONObject jGeo = (JSONObject)jGA.get("geometry");
        JSONObject jAttr = (JSONObject) jGA.get("attributes");
        this.id =getIntValue("id", jAttr);
        this.plotId=plotId;
        this.title = getStringValue("title", jAttr);
        this.content = getStringValue("content", jAttr);
        this.type = getStringValue("type", jAttr);
        this.geometry = getStringValue("geometry", jGA);

        this.shareUrl = ShareLink;
        this.shareTitle = ShareTitle;
        this.shareLogoUrl =ShareLogoUrl;
    }

    public void parseShareInfo(JSONObject plotShare){
        if("".equals(plotShare.get("url").toString())){
           return;
        }
        this.shareUrl = plotShare.get("url").toString();
        this.shareTitle = plotShare.get("title").toString();
        this.shareLogoUrl =plotShare.get("logoUrl").toString();
    }

    public String buildJsonString(){
        JSONObject jAttr = new JSONObject();
        jAttr.put("id", id);
        jAttr.put("title", title);
        jAttr.put("content", content);
        jAttr.put("type", id);

        JSONObject jGeo = (JSONObject) JSONValue.parse(geometry);
        JSONObject jGA = new JSONObject();
        jGA.put("geometry", jGeo);
        jGA.put("attributes", jAttr);


        JSONArray jArr = new JSONArray();
        jArr.add(0, jGA);

        JSONObject jMT = new JSONObject();
        jMT.put("marker", jArr);

        JSONObject jExtent = (JSONObject) JSONValue.parse(extent);
        jMT.put("extent", jExtent);

        return jAttr.toJSONString();
    }

    public String bulidJsonStringNoParam(){
        JSONObject jAttr = new JSONObject();
        jAttr.put("id", id);
        jAttr.put("title", title);
        jAttr.put("content", content);
        jAttr.put("type", id);

        JSONObject jGeo = (JSONObject) JSONValue.parse(geometry);
        JSONObject jGA = new JSONObject();
        jGA.put("geometry", jGeo);
        jGA.put("attributes", jAttr);

        JSONArray jArr = new JSONArray();
        jArr.add(0, jGA);

        JSONObject jMT = new JSONObject();
        jMT.put("marker", jArr);

        JSONObject jExtent = (JSONObject) JSONValue.parse(extent);
        jMT.put("extent", jExtent);

        return jMT.toString();
    }

    public String getExtent() {
        return extent;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }
}
