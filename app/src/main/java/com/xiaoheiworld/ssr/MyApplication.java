package com.xiaoheiworld.ssr;

import android.app.Application;

import com.github.shadowsocks.utils.SS_SDK;

/**
 * Created by victor on 2017/4/12.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SS_SDK.init(this);
    }
}
