package com.example.keshe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.keshe.adapter.ArticleAdapter;
import com.example.keshe.bean.ArticleBean;
import com.example.keshe.database.HistoryDBHelper;

import java.util.List;

public class ReadHistoryActivity extends AppCompatActivity {

    private HistoryDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_history);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageButton btn_back=findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadHistoryActivity.this.finish();
            }
        });

        List<ArticleBean> articleBeans = ArticleInfo.getHistoryList(this);
        ArticleAdapter articleAdapter = new ArticleAdapter(this, articleBeans);
        ListView listView = findViewById(R.id.article_list);
        TextView tv_no_history = findViewById(R.id.tv_no_history);
        if (!articleAdapter.isEmpty()) {
            tv_no_history.setVisibility(View.GONE);
            listView.setAdapter(articleAdapter);
        }

        Button btn_clean_history=findViewById(R.id.btn_clean_history);
        helper = HistoryDBHelper.getInstance(this);
        helper.OpenWriteLink();
        btn_clean_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setVisibility(View.GONE);
                tv_no_history.setVisibility(View.VISIBLE);
                helper.DeleteAll();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        helper.CloseLink();
    }
}