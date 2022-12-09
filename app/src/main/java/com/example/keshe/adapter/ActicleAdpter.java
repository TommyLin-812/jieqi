package com.example.keshe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keshe.ActicleDetail;
import com.example.keshe.R;
import com.example.keshe.bean.ActicleBean;

import java.io.Serializable;
import java.util.List;

public class ActicleAdpter extends BaseAdapter {
    private Context context;
    private List<ActicleBean> data;
    public ActicleAdpter(Context context,List<ActicleBean>acticle_list) {

        this.context = context;
        data=acticle_list;
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
        ViewHolder viewHolder =null;
        if(convertView == null){
            viewHolder=new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.acticle_item,null);
            viewHolder.articleauthor=convertView.findViewById(R.id.tv_article_author);
            viewHolder.articlepic=convertView.findViewById(R.id.iv_article_pic);
            viewHolder.articletitle=convertView.findViewById(R.id.tv_article_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        final ActicleBean acticleBean = data.get(position);
        viewHolder.articletitle.setText(acticleBean.getTitle());
        viewHolder.articlepic.setImageResource(acticleBean.getActiclePic());
        viewHolder.articleauthor.setText(acticleBean.getAuthor());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ActicleDetail.class);
                intent.putExtra("article",acticleBean);
                context.startActivity(intent);
            }
        } );



        return convertView;
    }
    class ViewHolder{
        TextView articletitle,articleauthor,articlecontent;
        ImageView articlepic;
    }


}
