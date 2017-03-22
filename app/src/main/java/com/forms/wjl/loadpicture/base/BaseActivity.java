package com.forms.wjl.loadpicture.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Activity基类
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化View
     */
    protected abstract void initView();
    /**
     * 初始化数据
     */
    protected abstract void initData();
    /**
     * 初始化事件
     */
    protected abstract void initListener();

}
