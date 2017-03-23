package com.forms.wjl.loadpicture.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.forms.wjl.loadpicture.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/3/22.
 */
public class FrescoListViewAdapter extends BaseAdapter {

    private List<String> urlList;
    private Context context;

    public FrescoListViewAdapter(List<String> urlList, Context context) {
        Fresco.initialize(context);
        this.urlList = urlList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return urlList.size();
    }

    @Override
    public Object getItem(int position) {
        return urlList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fresco_demo_list_view, null);
            holder.imageView = (SimpleDraweeView) convertView.findViewById(R.id.iv_pic);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageURI(Uri.parse(urlList.get(position)));
        String today;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        today = df.format(new Date(System.currentTimeMillis()));
        holder.tvContent.setText("更新日期：" + today);
        return convertView;
    }

    class ViewHolder {
        SimpleDraweeView imageView;
        TextView tvContent;
    }

}
