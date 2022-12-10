package com.example.keshe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keshe.adapter.ArticleAdpter;
import com.example.keshe.bean.ArticleBean;

import java.util.List;

public class CollectionListActivity extends AppCompatActivity {
    private List<ArticleBean> articleList;
    private ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_collection_list);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionListActivity.this.finish();
            }
        });

        articleList = ArticleInfo.getFavouriteList(this);
        ArticleAdpter articleAdpter = new ArticleAdpter(this, articleList);
        ListView listView = findViewById(R.id.article_list);
        TextView tv_no_favourite = findViewById(R.id.tv_no_favourite);
        if (!articleAdpter.isEmpty()) {
            tv_no_favourite.setVisibility(View.GONE);
            listView.setAdapter(articleAdpter);
        }
    }
}