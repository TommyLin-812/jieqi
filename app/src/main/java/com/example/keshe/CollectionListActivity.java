package com.example.keshe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keshe.adapter.ArticleAdapter;
import com.example.keshe.bean.ArticleBean;

import java.util.List;

public class CollectionListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_collection_list);
        ImageButton btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionListActivity.this.finish();
            }
        });

        List<ArticleBean> articleList = ArticleInfo.getFavouriteList(this);
        ArticleAdapter articleAdapter = new ArticleAdapter(this, articleList);
        ListView listView = findViewById(R.id.article_list);
        TextView tv_no_favourite = findViewById(R.id.tv_no_favourite);
        if (!articleAdapter.isEmpty()) {
            tv_no_favourite.setVisibility(View.GONE);
            listView.setAdapter(articleAdapter);
        }
    }
}