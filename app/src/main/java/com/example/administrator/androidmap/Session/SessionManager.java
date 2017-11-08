package com.example.administrator.androidmap.Session;

import com.example.administrator.androidmap.utils.StringUtils;

import net.minidev.json.JSONObject;

import java.util.List;

/**
 * @author sloanwu 2011-7-7 下午03:01:11
 */
public class SessionManager {

    private String loginUUid;//	用户ID
    private String name;
    private String phone;//电话
    private String logo;//头像地址
    private String gender;//性别
    private String account;//账号

    //专题图权限列表
    private List<JSONObject> autuTopicMapList;
    //标会分页 当前页 标会每页的大小
    private int plotPage=1;
    private int plotPageSize=10;
    //当前标会的分享信息
    private JSONObject PlotShare;
    //当前时候需要重新加载地图底图
    //针对离线地图的on off
    private boolean isReloadOffline;

    private static SessionManager instance;


    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (null == instance) {
            instance = new SessionManager();
        }
        if (StringUtils.isEmpty(instance.getLoginUUid())) {

        }
        return instance;
    }

    public String getLoginUUid() {
        return loginUUid;
    }

    public void setLoginUUid(String loginUUid) {
        this.loginUUid = loginUUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isLogin() {
        return !StringUtils.isEmpty(loginUUid);
    }

    public List<JSONObject> getAutuTopicMapList() {
        return autuTopicMapList;
    }

    public void setAutuTopicMapList(List<JSONObject> autuTopicMapList) {
        this.autuTopicMapList = autuTopicMapList;
    }

    public JSONObject getPlotShare() {
        return PlotShare;
    }

    public void setPlotShare(JSONObject plotShare) {
        PlotShare = plotShare;
    }

    public int getPlotPage() {
        return plotPage;
    }

    public void setPlotPage(int plotPage) {
        this.plotPage = plotPage;
    }

    public int getPlotPageSize() {
        return plotPageSize;
    }

    public void setPlotPageSize(int plotPageSize) {
        this.plotPageSize = plotPageSize;
    }

    public boolean isReloadOffline() {
        return isReloadOffline;
    }

    public void setReloadOffline(boolean reloadOffline) {
        isReloadOffline = reloadOffline;
    }
}
