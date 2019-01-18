package com.liang.victor.myssrlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.shadowsocks.constant.State;
import com.github.shadowsocks.utils.SS_SDK;
import com.github.shadowsocks.utils.VpnCallback;


public class MainActivity extends Activity implements View.OnClickListener, VpnCallback {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.switchButton);
        button.setOnClickListener(this);
        button.setText("未连接");
        SS_SDK.getInstance().setStateCallback(this);
    }

    @Override
    public void onClick(View v) {
        SS_SDK.getInstance().setProfile("67.21.81.39", 80, "www.xiaoheijia.tk",
                "aes-256-cfb", "auth_sha1_v4", "", "http_simple", "bing.com");
//        SS_SDK.getInstance().setProfile("192.168.1.1", 443, "ingress",
//           "auth_sha1");
        SS_SDK.getInstance().switchVpn(this);
    }

    @Override
    public void callback(int state) {
        switch (state) {
            case State.CONNECTING:
                button.setText("连接中");
                break;
            case State.CONNECTED:
                button.setText("已连接");
                break;
            case State.STOPPED:
                button.setText("已停止");
                break;
            case State.STOPPING:
                button.setText("正在停止");
                break;
        }
    }

}
