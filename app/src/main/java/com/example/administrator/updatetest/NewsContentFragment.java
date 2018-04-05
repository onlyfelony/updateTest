package com.example.administrator.updatetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsContentFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }


    public void refresh(News news){
        //只有当调用refersh(),才将新闻内容的布局显示出来
        View visibilitylayout = view.findViewById(R.id.visibility_layout);
        visibilitylayout.setVisibility(View.VISIBLE);

        TextView newsTitle = view.findViewById(R.id.news_title);
        TextView newsContent = view.findViewById(R.id.news_content);

        //刷新新闻的标题和内容
        newsTitle.setText(news.getTitle());
        newsContent.setText(news.getContent());


    }

}
