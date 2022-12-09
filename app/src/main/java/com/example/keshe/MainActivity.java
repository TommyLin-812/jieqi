package com.example.keshe;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private ImageView jumptohome,jumptoarticle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.home);
        jumptohome=this.findViewById(R.id.tv_jumpto_home);
        jumptoarticle=this.findViewById(R.id.tv_jumpto_acticle);
        jumptoarticle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this, ActicleList.class);
                startActivity(intent);
            }
        });


    }

}
