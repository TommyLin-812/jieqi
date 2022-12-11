package com.example.keshe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keshe.bean.ArticleBean;
import com.example.keshe.database.CollectionDBHelper;

public class ArticleDetail extends AppCompatActivity implements View.OnClickListener {
    private ArticleBean bean;
    private ImageView btn_add_favourite;
    private ImageView btn_delete_favourite;
    private CollectionDBHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.article_datail);
        bean = (ArticleBean) getIntent().getSerializableExtra("article");
        TextView articleContent = findViewById(R.id.article_content);
        TextView articleTitle = findViewById(R.id.article_title);
        articleContent.setText(bean.getContent());
        articleTitle.setText(bean.getTitle());
        ImageView iv_back = this.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArticleDetail.this.finish();
            }
        });
        if (bean == null) {
            return;
        }

        btn_add_favourite = findViewById(R.id.btn_add_favourite);
        btn_add_favourite.setOnClickListener(this);
        btn_delete_favourite = findViewById(R.id.btn_delete_favourite);
        btn_delete_favourite.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        helper = CollectionDBHelper.getInstance(this);
        helper.OpenWriteLink();
        helper.OpenReadLink();
        if (helper.Query(bean)) isFavourite();
    }

    @Override
    protected void onStop() {
        super.onStop();
        helper.CloseLink();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_favourite:
                helper.Insert(bean);
                isFavourite();
                break;
            case R.id.btn_delete_favourite:
                helper.Delete(bean);
                isNotFavourite();
        }
    }

    public void isFavourite() {
        btn_add_favourite.setVisibility(View.INVISIBLE);
        btn_add_favourite.setClickable(false);
        btn_delete_favourite.setVisibility(View.VISIBLE);
        btn_delete_favourite.setClickable(true);
    }

    public void isNotFavourite() {
        btn_add_favourite.setVisibility(View.VISIBLE);
        btn_add_favourite.setClickable(true);
        btn_delete_favourite.setVisibility(View.INVISIBLE);
        btn_delete_favourite.setClickable(false);
    }

}
