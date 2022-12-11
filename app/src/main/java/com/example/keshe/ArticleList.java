package com.example.keshe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        ImageView iv_to_home = this.findViewById(R.id.iv_to_home);
        ImageButton btn_favourite = findViewById(R.id.btn_favourite);
        Button btn_history = findViewById(R.id.btn_history);
        iv_to_home.setOnClickListener(this);
        btn_favourite.setOnClickListener(this);
        btn_history.setOnClickListener(this);

        ArticleAdapter articleAdapter = new ArticleAdapter(this, articleBeans);
        ListView listView = (ListView) findViewById(R.id.article_list);
        listView.setAdapter(articleAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_to_home:
                ArticleList.this.finish();
                break;
            case R.id.btn_favourite:
                Intent intentToCollection = new Intent(ArticleList.this, CollectionListActivity.class);
                startActivity(intentToCollection);
                break;
            case R.id.btn_history:
                Intent intentToHistory = new Intent(ArticleList.this,ReadHistoryActivity.class);
                startActivity(intentToHistory);
                break;
        }
    }
}
