package com.xiaoheiworld.ssr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.github.shadowsocks.constant.State;
import com.github.shadowsocks.utils.SS_SDK;
import com.github.shadowsocks.utils.VpnCallback;


public class MainActivity extends Activity implements View.OnClickListener, VpnCallback {
    private final static int UN_KNOW = 0;
    private Button mButton;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.switchButton);
        mButton.setOnClickListener(this);
        mWebView = findViewById(R.id.web_xiaohei);
        mWebView.loadUrl("http://www.xiaoheijia.tk");
        SS_SDK.getInstance().setStateCallback(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int state = SS_SDK.getInstance().getVPNstate();
        switch (state) {
            case State.CONNECTING:
                mButton.setText("连接中");
                break;
            case State.CONNECTED:
                mButton.setText("已连接");
                break;
            case State.STOPPED:
            case UN_KNOW:
                mButton.setText("未连接");
                break;
            case State.STOPPING:
                mButton.setText("断开中");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int state = SS_SDK.getInstance().getVPNstate();
        switch (state) {
            case State.CONNECTING:
                return;
            case State.CONNECTED:
            case UN_KNOW:
                mButton.setText("断开中");
                break;
            case State.STOPPING:
                return;
            case State.STOPPED:
                mButton.setText("连接中");
                break;
        }
        SS_SDK.getInstance().setProfile("67.21.81.39", 80, "www.xiaoheijia.tk",
                "aes-256-cfb", "auth_sha1_v4", "", "http_simple", "bing.com");
        SS_SDK.getInstance().switchVpn(this);
    }

    @Override
    public void callback(int state) {
        switch (state) {
            case State.CONNECTED:
                mButton.setText("已连接");
                break;
            case State.STOPPED:
                mButton.setText("未连接");
                break;
        }
    }

}
