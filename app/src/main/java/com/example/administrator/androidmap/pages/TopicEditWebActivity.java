package com.example.administrator.androidmap.pages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.androidmap.view.ProgressWebView;


/**
 * 专题图详情
 */
public class TopicEditWebActivity extends ToolBarActivity {

    private ProgressWebView webview;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_topic_edit_web);
        setToolBarTitle(R.string.titleTopic);
        initView();
        initWeb();
    }

    /**
     * 点击跳转专题图web
     */
    private void initWeb() {
        try {
            webview.loadUrl("https://www.baidu.com");
        }catch (Exception ex){
            System.out.println("========"+ex.toString());
        }
    }

    private void initView() {
        webview = (ProgressWebView) findViewById(R.id.topicEditWebView);
    }


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, TopicEditWebActivity.class);
        return intent;
    }
}
