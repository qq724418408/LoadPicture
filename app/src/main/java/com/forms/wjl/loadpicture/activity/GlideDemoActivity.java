package com.forms.wjl.loadpicture.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.forms.wjl.loadpicture.R;
import com.forms.wjl.loadpicture.adapter.GlideListViewAdapter;
import com.forms.wjl.loadpicture.base.BaseActivity;
import com.forms.wjl.loadpicture.constant.URLConstant;

import static com.forms.wjl.loadpicture.constant.URLConstant.gifUrl;

public class GlideDemoActivity extends BaseActivity implements View.OnClickListener {

    private static String TAG = GlideDemoActivity.class.getSimpleName();

    private ImageView ivBack; // 返回
    private ImageView ivGif;
    private ListView lvPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivGif = (ImageView) findViewById(R.id.iv_glide_gif);
        lvPicture = (ListView) findViewById(R.id.lv_picture);
    }

    @Override
    protected void initData() {
        lvPicture.setAdapter(new GlideListViewAdapter(URLConstant.getImgUrlList(), this));
        Glide.with(this)
                .load(gifUrl)
                .asGif()
                .error(R.mipmap.image_default)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.mipmap.image_loading)
                .into(ivGif);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(this);
        lvPicture.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:

                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        // 当ListView处于滑动状态时，停止加载图片，保证操作界面流畅
                        //Glide.with(GlideDemoActivity.this).pauseRequests();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 当ListView处于静止状态时，继续加载图片
                        //Glide.with(GlideDemoActivity.this).resumeRequests();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
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
