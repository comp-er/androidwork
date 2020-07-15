package com.jaybon.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity"; // 다른 것은 그대로 메인만 언더바 주자(검색 용이)
    // 제일 위에 전역으로 적는 것은~
    // 컴포넌트들을 모두 전역으로 올려서 선언한다

    private ProgressBar pgbDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent(); // 컴포넌트 초기화 (개발자 임의 작명)

        imageDownload();

        Log.d(TAG, "onCreate: 테스트1");
    }

    private void  initComponent(){ // 여기서 컴포넌트들을 찾아주고 프로그래밍 한다

        pgbDownload = findViewById(R.id.pgb_download);
        // 화면에 그리지 않으면 연산을 하지 않는다
    }

    private void imageDownload(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: 다운로드 쓰레드 시작");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: 다운로드 쓰레드 종료");
                pgbDownload.setVisibility(View.INVISIBLE);
            }
        }).start();
    }



}