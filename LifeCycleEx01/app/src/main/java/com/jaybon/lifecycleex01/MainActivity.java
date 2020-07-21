package com.jaybon.lifecycleex01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private String downloadData = ""; // 다운로드 데이터로 가정한다
    private String currentState = "";



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

        currentState = "에너지 50";
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        Log.d(TAG, "onResume: 현재 에너지는 "+currentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // 불러오면서 키값을 바로 만들 수 있다
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("currentState", currentState);
        editor.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE); // 불러오면서 키값을 바로 만들 수 있다
        SharedPreferences.Editor editor = pref.edit();
        currentState = pref.getString("currentState", "");

        Log.d(TAG, "onCreate: 현재 에너지는"+ currentState);


        // 다운로드 시작
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=1; i<4; i++){
                        Thread.sleep(1000);
                        Log.d(TAG, "run: "+i+"초");
                    }
                    downloadData = "다운받은 데이터";
                    Log.d(TAG, "run:  다운로드 종료");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}