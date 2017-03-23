package com.forms.wjl.loadpicture.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.forms.wjl.loadpicture.R;
import com.forms.wjl.loadpicture.base.BaseActivity;
import com.forms.wjl.loadpicture.constant.URLConstant;
import com.forms.wjl.loadpicture.utils.BitmapCompressUtil;
import com.forms.wjl.loadpicture.utils.FileUtil;

public class CompressDemoActivity extends BaseActivity implements View.OnClickListener {

    private static String fileName = "abc"; // 图片名称
    private ImageView ivResources; // 原图片
    private ImageView ivTarget; // 目标图片
    private ImageView ivBack; // 返回
    private TextView tvResult;
    private SeekBar sbSize; // 尺寸压缩
    private SeekBar sbQuality; // 质量压缩按钮
    private Bitmap bitmap;
    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compress_demo);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void initView() {
        sbSize = (SeekBar) findViewById(R.id.sb_size);
        sbQuality = (SeekBar) findViewById(R.id.sb_quality);
        tvResult = (TextView) findViewById(R.id.tv_result);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivResources = (ImageView) findViewById(R.id.iv_resource);
        ivTarget = (ImageView) findViewById(R.id.iv_target);
    }

    @Override
    protected void initData() {
        FileUtil.downLoadPictureToSD(this, URLConstant.imgUrl, fileName);
        bitmap = FileUtil.getDiskBitmap(this, fileName);
        ivResources.setImageBitmap(bitmap);
        imgPath = FileUtil.getImgPath(this, fileName);
    }

    @Override
    protected void initListener() {
        ivBack.setOnClickListener(this);
        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int w = 10 * progress;
                int h = 10 * progress;
                bitmap = BitmapCompressUtil.sizeCompressBitmap(CompressDemoActivity.this, imgPath, w, h);
                ivTarget.setImageBitmap(bitmap);
                tvResult.setText("尺寸压缩\n\n" + "宽为：" + bitmap.getWidth() + "\n\n高为：" + bitmap.getHeight());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbQuality.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bitmap = BitmapCompressUtil.getBitmapFromLocal(CompressDemoActivity.this, imgPath);
                bitmap = BitmapCompressUtil.qualityCompressBitmap(CompressDemoActivity.this, bitmap, progress);
                ivTarget.setImageBitmap(bitmap);
                tvResult.setText("质量压缩\n\n" + "质量为：" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
