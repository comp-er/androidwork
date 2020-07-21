package com.jaybon.lifecycleex02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    //메인 액티비티의 전역변수, 걸어두고 사용하는 것이 편리하다
    //메인 액티비티의 모든 것이 담겨있다
    private Context mContext = MainActivity.this;

    private Button btnNum, btnEmail;
    private TextView tvNum, tvEmail;

    
    // 콜백함수
    // 어떤화면이 꺼지면 데이터값을 가져오는 것?
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: 콜백받음");

        if(requestCode == 1){
            if(resultCode == 10){ // 인증번호 응답을 받은 것
                tvNum.setText(data.getStringExtra("number"));
            } else if(resultCode == 20){
                tvEmail.setText(data.getStringExtra("email"));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNum = findViewById(R.id.tv_num);
        tvEmail = findViewById(R.id.tv_email);
        btnEmail = findViewById(R.id.btn_email);
        btnNum = findViewById(R.id.btn_num);

        // 리스너
        btnNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 트럭 생성 getBaseContext() 또는 MainActivity.this 또는 전역변수로 등록하여 사용
                // 출발지 MainActivity.this, 목적지 Sub2Activity.class
                Intent intent = new Intent(mContext, SubActivity.class);

                // startActivity(intent); 이동만 할때 사용

                // 이동 및 응답을 받을 수 있는 것
                // 나를 띄운 페이지 확인 requestCode (enum 등을 사용하자)
                startActivityForResult(intent, 1);

            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Sub2Activity.class);
                startActivityForResult(intent, 1);
            }
        });
        
        

    }
}