package com.example.administrator.updatetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        RecyclerView recyclerView  = view.findViewById(R.id.recyclerview_titie);
        NewsAdapter nadapter = new NewsAdapter(getNews());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(nadapter);
        recyclerView.setLayoutManager(manager);
        return view;
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            News news = new News();
            news.setTitle("This is news title " + i);
            news.setContent(getRandomLengthContent("This is news content " + i + ". "));
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity().findViewById(R.id.testPage)!=null){

            isTwoPage = true;
        }else {

            isTwoPage = false;
        }


    }

    class  NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{


        List<News> newsList;

        public NewsAdapter(List<News> newsList) {
            this.newsList = newsList;
        }

        @NonNull
        @Override
        public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View  mView=  LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            final NewsViewHolder viewHolder = new NewsViewHolder(mView);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News my_news = newsList.get(viewHolder.getAdapterPosition());

                 if(isTwoPage){
                     //双页模式
                     NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.newscontent_fragment);
                     newsContentFragment.refresh(my_news);

                 }else {
                     //单页模式,直接跳到NewsContentActivity

                     NewsContentActivity.actionStart(getActivity(),my_news);
                 }

                }
            });




            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
            News news = newsList.get(position);
            holder.textView.setText(news.getTitle());

        }

        @Override
        public int getItemCount() {

            return newsList.size();
        }


        class NewsViewHolder extends RecyclerView.ViewHolder{
            TextView textView;

            public NewsViewHolder(View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.newstitle_item);

            }



        }


    }



}
