package com.jaybon.mvvmex01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;

    private RecyclerView recyclerView;

    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리사이클러 뷰 설정
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        // 뷰모델 생성
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        // 빨대 꼽기 - 옵저버
        // observe() 컨텍스트 / 데이터 변할때마다 할것 설정
        noteViewModel.구독().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                // 받은 데이터로 화면을 갱신 시켜주면된다

                Log.d(TAG, "onChanged: 구독하고 있는 데이터가 변경되었습니다.");

                // 리사이클러뷰에 데이터 변경 + notifyDataSetChanged()
                adapter.setNotes(notes);
            }
        });

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int priority = new Random().nextInt(100)+1;
                noteViewModel.추가하기(new Note("제목1", "설명1", priority));
            }
        });

    }
}