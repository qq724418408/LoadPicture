package com.forms.wjl.loadpicture.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.forms.wjl.loadpicture.R;
import com.forms.wjl.loadpicture.adapter.FrescoListViewAdapter;
import com.forms.wjl.loadpicture.base.BaseActivity;
import com.forms.wjl.loadpicture.constant.URLConstant;

public class FrescoDemoActivity extends BaseActivity implements View.OnClickListener {

    private SimpleDraweeView simpleDraweeView;
    private ListView lvPicture;
    private ImageView ivBack; // 返回

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_fresco_demo);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.sdv);
        lvPicture = (ListView) findViewById(R.id.lv_picture);
    }

    @Override
    protected void initData() {
        lvPicture.setAdapter(new FrescoListViewAdapter(URLConstant.getImgUrlList(), this));
        simpleDraweeView.setImageURI(Uri.parse(URLConstant.getImgUrlList().get(0)));
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back: // 返回
                finish();
                break;
        }
    }
}
