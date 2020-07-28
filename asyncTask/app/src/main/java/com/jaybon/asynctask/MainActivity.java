package com.jaybon.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private Button btnExecute, btnStop; //실행 중지버튼
    private ProgressBar progressBar; // 진행바
    private int value = 0; // 진행바의 퍼센테이지
    private  BackgroundTask task;

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {  // 이것이 쓰레드가 됨

        @Override // 타겟 호출 직전
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress(value);
            Log.d(TAG, "onPreExecute: ");
        }

        @Override // 타겟 (run()과 같음)
        protected Integer doInBackground(Integer... integers) { // 스레드 실행시 인수 받기
            Log.d(TAG, "doInBackground: ");

            while (isCancelled() == false){

                value = value + 5;

                if(value >= 100){
                    break;
                }

                // 이 값은 onProgressUpdate로 넘어간다
                // 넘어가서 바로 그려짐
                publishProgress(value);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            // 이 값이 onPostExecute의 매개변수로 넘어간다
            return 1; // 정상적으로 수행됐으면 1, 안됐으면 -1
        }

        @Override // UI쓰레드에 그림을 그려주는 메서드
        protected void onProgressUpdate(Integer... values) { // publishProgress 리턴 값 받기
            super.onProgressUpdate(values);
            Log.d(TAG, "onProgressUpdate: ");
            progressBar.setProgress(values[0]); // Integer...는 배열이라서 0번 주소 = Integer[]
        }

        @Override // 타겟 호출 이후
        protected void onPostExecute(Integer integer) { // doInBackground 리턴 값 받기
            // 리턴값 받아서 값에 따라 분기
            super.onPostExecute(integer);
            Log.d(TAG, "onPostExecute: ");
            Toast.makeText(MainActivity.this, "다운로드 완료", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInit(); // 함수 사용할 때 내가 만든 것은 m을 붙이고 오버라이딩은 그대로

        task = new BackgroundTask();

        mListenser();

    }

    private void mListenser() {

        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(task == null || task.isCancelled() == true){
                    task = new BackgroundTask();
                    task.execute(); // execute(여기의 값이 doInBackground 의 매개변수로 감)
                }


            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                task.cancel(true);


            }
        });
    }

    private void mInit() {
        btnExecute = findViewById(R.id.btn_execute);
        btnStop = findViewById(R.id.btn_stop);
        progressBar = findViewById(R.id.progressBar);
    }
}