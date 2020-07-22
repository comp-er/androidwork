package com.jaybon.navigationintentex01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.navigation.NavigationView;

public class SubActivity extends AppCompatActivity {

    // 아래 두가지는 무조건 만들자
    private static final String TAG = "Main_Activity";
    private Context mContext = SubActivity.this;

    private NavigationView nav;


    @Override
    protected void onNewIntent(Intent intent) { // 싱글탑 플래그일 경우 사용하던 것을 또 사용
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: 호출됨");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 컴포넌트들 가져오기
        init();

        // 네비게이션 메뉴들에 리스너 추가
        NavigationViewHelper.enableNavigation(mContext, nav);
    }

    private void init(){
        nav = findViewById(R.id.nav2);
    }
}