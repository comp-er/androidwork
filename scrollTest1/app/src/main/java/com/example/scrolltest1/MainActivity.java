package com.example.scrolltest1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        Adapter adapter = new Adapter();

        adapter.addContent(new Data("헤더",0));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));
        adapter.addContent(new Data("리사이클러뷰",1));


        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(layoutManager1);
        rv.setAdapter(adapter);
    }
}