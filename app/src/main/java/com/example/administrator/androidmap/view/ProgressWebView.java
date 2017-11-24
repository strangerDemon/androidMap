package com.example.administrator.androidmap.view;

/**
 * Created by tengdag on 2016/11/23 11:41.
 * 带进度条的WebView
 */

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

@SuppressWarnings("deprecation")
public class ProgressWebView extends WebView {

    private ProgressBar progressbar;
    //网页缓存目录
    private static final String cacheDirPath = Environment
            .getExternalStorageDirectory() + "/tdt/webCache/";

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContext(context);
        addProgressBar();
        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);   //在当前的webview中跳转到新的url
                return true;
            }
        });
        setWebChromeClient(new WebChromeClient());
    }


    private void initContext(Context context) {
        requestFocus();
        setInitialScale(39);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//支持通过Javascript打开新窗口
        getSettings().setJavaScriptEnabled(true);//设置WebView属性，能够执行Javascript脚本
        getSettings().setUseWideViewPort(true);//将图片调整到适合webview的大小
        getSettings().setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        getSettings().setDomStorageEnabled(true);//设置是否启用了DOM Storage API
        getSettings().setDatabaseEnabled(true);//开启database storage API功能
        getSettings().setDatabasePath(cacheDirPath); //设置数据库缓存路径
        getSettings().setAppCachePath(cacheDirPath);//设置Application Caches缓存目录
        getSettings().setAppCacheEnabled(true);//开启Application Caches功能
    }

    /**
     * 添加进度条
     */
    public void addProgressBar() {
        progressbar = new ProgressBar(getContext(), null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, 20, 0, 0));
        addView(progressbar);//添加进度条至LoadingWebView中

        setWebChromeClient(new WebChromeClient());//设置setWebChromeClient对象
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

}
