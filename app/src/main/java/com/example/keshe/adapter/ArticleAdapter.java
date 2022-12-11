package com.example.keshe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keshe.ArticleDetail;
import com.example.keshe.R;
import com.example.keshe.bean.ArticleBean;

import java.util.List;

public class ArticleAdapter extends BaseAdapter {
    private final Context context;
    private final List<ArticleBean> data;

    public ArticleAdapter(Context context, List<ArticleBean> article_list) {

        this.context = context;
        data = article_list;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.acticle_item, null);
            viewHolder.articleAuthor = convertView.findViewById(R.id.tv_article_author);
            viewHolder.articlePic = convertView.findViewById(R.id.iv_article_pic);
            viewHolder.articleTitle = convertView.findViewById(R.id.tv_article_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ArticleBean articleBean = data.get(position);
        viewHolder.articleTitle.setText(articleBean.getTitle());
        viewHolder.articlePic.setImageResource(articleBean.getArticlePic());
        viewHolder.articleAuthor.setText(articleBean.getAuthor());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArticleDetail.class);
                intent.putExtra("article", articleBean);
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    static class ViewHolder {
        TextView articleTitle;
        TextView articleAuthor;
        ImageView articlePic;
    }


}
