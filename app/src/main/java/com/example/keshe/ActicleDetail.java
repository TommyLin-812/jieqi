package com.example.keshe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keshe.bean.ActicleBean;

public class ActicleDetail extends AppCompatActivity implements View.OnClickListener{
    private TextView articleContent,articleTitile;
    private ActicleBean bean;
    private ImageView tvback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.article_datail);
        bean=(ActicleBean) getIntent().getSerializableExtra("article");
        articleContent=findViewById(R.id.article_content);
        articleTitile=findViewById(R.id.article_title);
        articleContent.setText(bean.getContent());
        articleTitile.setText(bean.getTitle());
        tvback=this.findViewById(R.id.iv_back);
        tvback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ActicleDetail.this.finish();
            }
        });
        if(bean==null){
            return;
        }





    }
    @Override
    public void onClick(View v) {

    }
}
