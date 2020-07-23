package com.jaybon.instagramrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    //    Context mContext = MainActivity.this;
    private RecyclerView rvContent;
    private RecyclerView rvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvProfile = findViewById(R.id.rv_profile);
        ProfileAdapter profileAdapter = new ProfileAdapter();

        profileAdapter.addProfile(new Profile(R.drawable.propic1));
        profileAdapter.addProfile(new Profile(R.drawable.propic2));
        profileAdapter.addProfile(new Profile(R.drawable.propic3));
        profileAdapter.addProfile(new Profile(R.drawable.propic3));
        profileAdapter.addProfile(new Profile(R.drawable.propic3));
        profileAdapter.addProfile(new Profile(R.drawable.propic3));
        profileAdapter.addProfile(new Profile(R.drawable.propic3));
        profileAdapter.addProfile(new Profile(R.drawable.propic3));

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvProfile.setLayoutManager(layoutManager1);
        rvProfile.setAdapter(profileAdapter);


        rvContent = findViewById(R.id.rv_content);
        ContentAdapter contentAdapter = new ContentAdapter();

        contentAdapter.addContent(new Content(R.drawable.postpic1));
        contentAdapter.addContent(new Content(R.drawable.postpic2));
        contentAdapter.addContent(new Content(R.drawable.postpic3));
        contentAdapter.addContent(new Content(R.drawable.postpic4));
        contentAdapter.addContent(new Content(R.drawable.postpic5));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvContent.setLayoutManager(layoutManager);
        rvContent.setAdapter(contentAdapter);

    }

}