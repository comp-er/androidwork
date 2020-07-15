package com.jaybon.frag2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2;
    private Fragment fragment1, fragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // commit이 호출될때 onCreateView가 실행된다
                // onCreateView에 R.id.frame_container가 들어간다
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment1).commit();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // commit이 호출될때 onCreateView가 실행된다
                // onCreateView에 R.id.frame_container가 들어간다
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment2).commit();
            }
        });

    }
}