package com.jaybon.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private EditText et;
    private Button btnStart, btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
        initListener();

    }

    private void initObject(){
        et = findViewById(R.id.et);
        btnStart = findViewById(R.id.btn_start);
        btnEnd = findViewById(R.id.btn_end);
    }

    private void initListener(){

        // 서비스를 실행하는 방법
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String musicName = et.getText().toString();
                Intent intent = new Intent(MainActivity.this, MyService.class);
                intent.putExtra("musicName", musicName);
                startService(intent);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MyService.class);
                //서비스를 호출할 땐 무조건 인텐트가 필요하다
                stopService(intent);
            }
        });

    }
}