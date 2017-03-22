package com.forms.wjl.loadpicture.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.forms.wjl.loadpicture.R;
import com.forms.wjl.loadpicture.adapter.PictureListViewAdapter;
import com.forms.wjl.loadpicture.base.BaseActivity;
import com.forms.wjl.loadpicture.constant.URLConstant;
import com.forms.wjl.loadpicture.view.GlideCircleTransform;

import java.util.concurrent.ExecutionException;

import static com.forms.wjl.loadpicture.constant.URLConstant.getImgUrlList;
import static com.forms.wjl.loadpicture.constant.URLConstant.gifUrl;

public class GlideDemoActivity extends BaseActivity implements View.OnClickListener {

    private static String TAG = GlideDemoActivity.class.getSimpleName();

    private Button btn; // 按钮 // http://storage.slide.news.sina.com.cn/slidenews/77_ori/2017_12/74766_765125_520053.gif
    private ImageView ivGif;
    private TextView tvProgress;
    private ListView lvPicture;

    private final Handler glideHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                GlideDrawable glideDrawable = (GlideDrawable) msg.obj;
                ivGif.setImageDrawable(glideDrawable);
            }
        }
    };

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
        tvProgress = (TextView) findViewById(R.id.tv_progress);
        ivGif = (ImageView) findViewById(R.id.iv_glide_gif);
        lvPicture = (ListView) findViewById(R.id.lv_picture);
    }

    @Override
    protected void initData() {
        //loadPicture(gifUrl);
        new MyTask().execute(gifUrl);
        lvPicture.setAdapter(new PictureListViewAdapter(URLConstant.getImgUrlList(), this));
        //Glide.with(this).load(URLConstant.getImgUrlList().get(1)).into(new SimpleTarget<GlideDrawable>() {
        Glide.with(this)
                .load(getImgUrlList().get(1))
                .transform(new GlideCircleTransform(this))
                .placeholder(R.mipmap.ic_launcher)
                .animate(R.anim.alpha_item_in)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        ivGif.setImageDrawable(resource);
                    }
                });
    }

    @Override
    protected void initListener() {

        lvPicture.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:

                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        // 当ListView处于滑动状态时，停止加载图片，保证操作界面流畅
                        Glide.with(GlideDemoActivity.this).pauseRequests();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 当ListView处于静止状态时，继续加载图片
                        Glide.with(GlideDemoActivity.this).resumeRequests();
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

    }

    /**
     * 加载图片
     *
     * @param url
     */
    public void loadPicture(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GlideDrawable glideDrawable = Glide.with(GlideDemoActivity.this).load(url).into(100, 100).get();
                    Message msg = Message.obtain();
                    msg.what = 1;
                    msg.obj = glideDrawable;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    class MyTask extends AsyncTask<String, Integer, GifDrawable> {

        @Override
        protected GifDrawable doInBackground(String... params) {
            GifDrawable gifDrawable = null;
            int i = 0;
            try {
                gifDrawable = Glide.with(GlideDemoActivity.this).load(params[0]).asGif()
                        .into(AbsListView.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            while (null == gifDrawable) {
                i++;
                publishProgress(i);
            }
            return gifDrawable;
        }

        @Override
        protected void onPostExecute(GifDrawable bitmap) {
            ivGif.setImageDrawable(bitmap);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tvProgress.setText("进度：" + values[0]);
            super.onProgressUpdate(values);
        }
    }

}
