package com.forms.wjl.loadpicture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.forms.wjl.loadpicture.R;
import com.forms.wjl.loadpicture.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnGoToGlideDomo; // 按钮 //
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_glide_demo:
                intent = new Intent(this,GlideDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_to_picasso_demo:
                intent = new Intent(this,PicassoDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_to_fresco_demo:
                intent = new Intent(this,FrescoDemoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
