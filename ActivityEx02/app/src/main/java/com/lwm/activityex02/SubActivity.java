package com.lwm.activityex02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    private static final String TAG = "SubActivity";

    private Button btnFinishSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btnFinishSub = findViewById(R.id.btn_finish_sub);
        btnFinishSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // this가 생략되어있다.
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        User user = (User) intent.getSerializableExtra("user");

        Log.d(TAG, "onCreate: name" + name);
    }
}