package com.jaybon.lifecycleex02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sub2Activity extends AppCompatActivity {

    private Button btnSub2Num;
    private EditText et2Num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        btnSub2Num = findViewById(R.id.btn_sub2_num);
        et2Num = findViewById(R.id.et_3);

        btnSub2Num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra("email", et2Num.getText().toString());
                setResult(20, intent);

                finish();
            }
        });
    }
}