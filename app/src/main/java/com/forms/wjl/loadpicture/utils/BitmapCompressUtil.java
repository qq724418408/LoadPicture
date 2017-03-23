package com.forms.wjl.loadpicture.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by bubbly on 2017/3/23.
 */
public class BitmapCompressUtil {

    /**
     * 尺寸压缩
     *
     * @param filePath
     * @param w 图片宽度
     * @param h 图片高度
     * @return
     */
    public static Bitmap sizeCompressBitmap(String filePath, int w, int h) { // 图片路径
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, w, h); // 自定义一个宽和高
        options.inJustDecodeBounds = false; //
        return BitmapFactory.decodeFile(filePath, options); // 返回bitmap
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return inSampleSize
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight; // 获取图片的高
        final int width = options.outWidth; // 获取图片的框
        int inSampleSize = 4;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize; // 返回缩放值
    }

    /**
     * 尺寸压缩
     *
     * @param imgPath
     * @return Bitmap
     */
    public static Bitmap sizeCompressBitmap2(String imgPath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2; // 直接设置它的压缩率，表示1/2
        Bitmap b = null;
        try {
            b = BitmapFactory.decodeFile(imgPath, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 从本地读取图片,通过路径，获得bitmap
     *
     * @param pathName 图片路径
     * @return Bitmap
     */
    public static Bitmap getBitmapFromLocal(String pathName) {
        Bitmap bitmap = null;
        try {
            FileInputStream fis = new FileInputStream(pathName);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        return bitmap;
    }

    /**
     * 质量压缩
     *
     * @param bitmap
     * @param quality 质量0-100
     * @return
     */
    public static Bitmap qualityCompressBitmap(Bitmap bitmap, int quality) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        return BitmapFactory.decodeStream(bis);
    }

}
