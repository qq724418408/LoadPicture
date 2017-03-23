package com.forms.wjl.loadpicture.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.forms.wjl.loadpicture.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * Created by bubbly on 2017/3/23.
 */
public class FileUtil {

    /**
     * 获取sd卡路径
     *
     * @return
     */
    public static String getImgPath(Context context, String fileName) {
        return context.getFilesDir().getPath() + "/" + fileName;
    }

    /**
     * 下载网络图片到sd卡
     */
    public static void downLoadPictureToSD(final Context context, final Handler handler, final String imgUrl, final String imgName) {

        new Thread(new Runnable() {
            Bitmap bitmap;

            @Override
            public void run() {
                try {
                    bitmap = Glide.with(context)
                            .load(imgUrl)
                            .asBitmap()
                            .error(R.mipmap.image_default)
                            .placeholder(R.mipmap.image_loading)
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                    saveFileToSDCard(context, bitmap, imgName);
                    Message msg = handler.obtainMessage();
                    msg.what = 1;
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 获取本地图片
     *
     * @param fileName
     * @return
     */
    public static Bitmap getDiskBitmap(Context context, String fileName) {
        String path = getImgPath(context, fileName);
        Bitmap bitmap = null;
        try {
            File file = new File(path);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(path);
            }
        } catch (Exception e) {
        }
        if (null == bitmap) { //
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.image_default);
        }
        return bitmap;
    }

    /**
     * 保存到内存
     *
     * @param context
     * @param bitmap
     * @param toPath
     */
    public static void saveFileToSDCard(Context context, Bitmap bitmap, String toPath) {
        try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bs);
            InputStream isBm = new ByteArrayInputStream(bs.toByteArray());
            FileOutputStream fosTo = context.openFileOutput(toPath, Context.MODE_PRIVATE);
            byte[] b = new byte[1024];
            int hasRead = 0;
            try {
                while ((hasRead = isBm.read(b)) != -1) {
                    fosTo.write(b, 0, hasRead);
                    fosTo.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
