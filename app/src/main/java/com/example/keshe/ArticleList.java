package com.example.keshe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keshe.adapter.ArticleAdpter;
import com.example.keshe.bean.ArticleBean;

import java.util.List;

public class ArticleList extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private ImageView jumptohome, jumptoarticle;
    private ImageButton btn_favourite;
    private List<ArticleBean> articleBeans;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.article_list);
        articleBeans = ArticleInfo.getArticleList();
        jumptohome = this.findViewById(R.id.tv_jumpto_home);
        jumptoarticle = this.findViewById(R.id.tv_jumpto_acticle);
        btn_favourite = findViewById(R.id.btn_favourite);
        jumptohome.setOnClickListener(new View.OnClickListener() {
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

        ArticleAdpter articleAdpter = new ArticleAdpter(this, articleBeans);
        ListView listView = (ListView) findViewById(R.id.article_list);
        listView.setAdapter(articleAdpter);
    }


    @Override
    public void onClick(View v) {

    }
}
