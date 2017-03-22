package com.forms.wjl.loadpicture.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.forms.wjl.loadpicture.R;
import com.forms.wjl.loadpicture.base.BaseActivity;

public class PicassoDemoActivity extends BaseActivity implements View.OnClickListener {

    private Button btn; // 按钮
    private ImageView ivGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_demo);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void initView() {
        ivGif = (ImageView) findViewById(R.id.iv_picasso_gif);
    }

    @Override
    protected void initData() {
        //Picasso.with(this).load(gifUrl).into(ivGif);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
