package com.forms.wjl.loadpicture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.forms.wjl.loadpicture.R;
import com.forms.wjl.loadpicture.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnGoToGlideDemo; // Glide演示按钮
    private Button btnGoToPicassoDemo; // Picasso演示按钮
    private Button btnGoToFrescoDemo; // Fresco演示按钮
    private Button btnCompress; // 压缩演示按钮
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
        btnCompress = (Button) findViewById(R.id.btn_to_compress_demo);
        btnGoToGlideDemo = (Button) findViewById(R.id.btn_to_glide_demo);
        btnGoToPicassoDemo = (Button) findViewById(R.id.btn_to_picasso_demo);
        btnGoToFrescoDemo = (Button) findViewById(R.id.btn_to_fresco_demo);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnCompress.setOnClickListener(this);
        btnGoToGlideDemo.setOnClickListener(this);
        btnGoToPicassoDemo.setOnClickListener(this);
        btnGoToFrescoDemo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_glide_demo:
                intent = new Intent(this,GlideDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_to_compress_demo:
                intent = new Intent(this,CompressDemoActivity.class);
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
