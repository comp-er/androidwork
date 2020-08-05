package com.jaybon.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private Context mContext = MainActivity.this; // 메인액티비티는 액티비티+xml정보

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager; // 리사이클러뷰는 무조건 이것이 필요하다
    private YtsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 오브젝트 초기화
        init();

        // 다운로드
        initDownload();

        // 리스너
        listener();
        
    }

    private void init(){
        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(); // 리사이클러뷰 높이

        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new YtsAdapter();
    }

    // Yts 데이터 받기
    private void initDownload(){
        YtsService ytsService = YtsService.retrofit.create(YtsService.class);

        // 미래의 데이터를 call에 담기 (퓨처)
        Call<YtsData> call = ytsService.영화목록가져오기("rating",10,1);

        // enqueue가 하는 역할은 응답 받아주는 것 (프로미스의 then 과 같음)
        call.enqueue(new Callback<YtsData>() {
            @Override
            public void onResponse(Call<YtsData> call, Response<YtsData> response) {
                if(response.isSuccessful() == true){
                    YtsData ytsData = response.body();
                    // 리사이클러뷰 어댑터에 연결
                    recyclerView.setAdapter(adapter);
                    adapter.addItems(ytsData.getData().getMovies());

                }
            }

            @Override
            public void onFailure(Call<YtsData> call, Throwable t) { // 에러가 궁금하면 t.를 통해서 확인
                Toast.makeText(MainActivity.this, "다운로드 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void listener(){
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                Toast.makeText(mContext, "안녕", Toast.LENGTH_SHORT).show();
//            }
//        }).attachToRecyclerView(recyclerView);


    }
}