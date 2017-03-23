package com.forms.wjl.loadpicture.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.forms.wjl.loadpicture.R;
import com.forms.wjl.loadpicture.adapter.PicassoListViewAdapter;
import com.forms.wjl.loadpicture.base.BaseActivity;
import com.forms.wjl.loadpicture.constant.URLConstant;
import com.forms.wjl.loadpicture.utils.PicassoCircleTransform;
import com.squareup.picasso.Picasso;

public class PicassoDemoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack; // 返回
    private ImageView ivGif;
    private ListView lvPicture;

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
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivGif = (ImageView) findViewById(R.id.iv_gif);
        lvPicture = (ListView) findViewById(R.id.lv_picture);
    }

    @Override
    protected void initData() {
        Picasso.with(this)
                .load(URLConstant.getImgUrlList().get(1))
                .transform(new PicassoCircleTransform())
                .error(R.mipmap.image_default)
                .placeholder(R.mipmap.image_loading)
                .into(ivGif);
        lvPicture.setAdapter(new PicassoListViewAdapter(URLConstant.getImgUrlList(), this)); // listview设置适配器
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
