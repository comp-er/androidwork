package com.jaybon.myfirstapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // onCreate 함수가 최초로 실행됨.(콜백)
    // activity는 프레임
    // savedInstanceState 앱을 껐따켰을 때 이전 상태값을 가져온다
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 데이터를 가져온다
        setContentView(R.layout.activity_main); // 그림을 그린다
    }

}
