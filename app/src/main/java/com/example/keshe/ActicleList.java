package com.example.keshe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.keshe.adapter.ActicleAdpter;
import com.example.keshe.bean.ActicleBean;

import java.util.ArrayList;
import java.util.List;

public class ActicleList extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;
    private ImageView jumptohome ,jumptoarticle;
    public List<ActicleBean> acticleBeans = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.article_list);
        ActicleBean a1 =new ActicleBean("1","测试标题1","红红火火恍恍惚惚或或哈哈哈哈",R.drawable.rain_cow,"zxx");
        ActicleBean a2 =new ActicleBean("2","测试标题2","6666666666666666666666",R.drawable.rabit,"ltb");
        acticleBeans.add(a1);
        acticleBeans.add(a2);
        jumptohome=this.findViewById(R.id.tv_jumpto_home);
        jumptoarticle = this.findViewById(R.id.tv_jumpto_acticle);
        jumptohome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ActicleList.this.finish();
            }
        });
        ActicleAdpter acticleAdpter=new ActicleAdpter(this,acticleBeans);
        ListView listView=(ListView) findViewById(R.id.article_list);
        listView.setAdapter(acticleAdpter);
    }


    @Override
    public void onClick(View v) {

    }
}
