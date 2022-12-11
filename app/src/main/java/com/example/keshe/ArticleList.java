package com.example.keshe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keshe.adapter.ArticleAdapter;
import com.example.keshe.bean.ArticleBean;

import java.util.List;

public class ArticleList extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.article_list);
        List<ArticleBean> articleBeans = ArticleInfo.getArticleList();
        ImageView iv_to_home = this.findViewById(R.id.tv_jumpto_home);
        ImageButton btn_favourite = findViewById(R.id.btn_favourite);
        iv_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleList.this.finish();
            }
        });
        btn_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticleList.this, CollectionListActivity.class);
                startActivity(intent);
            }
        });

        ArticleAdapter articleAdapter = new ArticleAdapter(this, articleBeans);
        ListView listView = (ListView) findViewById(R.id.article_list);
        listView.setAdapter(articleAdapter);
    }


    @Override
    public void onClick(View v) {

    }
}
