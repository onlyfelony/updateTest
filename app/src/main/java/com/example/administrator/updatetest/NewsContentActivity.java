package com.example.administrator.updatetest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        String new_title = getIntent().getStringExtra("News title");
        String new_cont = getIntent().getStringExtra("News content");

        News news = new News();
        news.setTitle(new_title);
        news.setContent(new_cont);

        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.anotherPage);
        newsContentFragment.refresh(news);//刷新新闻内容界面


    }

    public  static  void actionStart(Context context,News news){

        Intent intent = new Intent(context,NewsContentActivity.class);
        intent.putExtra("News title",news.getTitle());
        intent.putExtra("News content",news.getContent());
        context.startActivity(intent);


    }

}
