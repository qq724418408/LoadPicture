package com.forms.wjl.loadpicture.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.forms.wjl.loadpicture.R;
import com.forms.wjl.loadpicture.base.BaseActivity;

public class FrescoDemoActivity extends BaseActivity implements View.OnClickListener{

    private Button btn; // 按钮 // http://storage.slide.news.sina.com.cn/slidenews/77_ori/2017_12/74766_765125_520053.gif

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

    }
}
