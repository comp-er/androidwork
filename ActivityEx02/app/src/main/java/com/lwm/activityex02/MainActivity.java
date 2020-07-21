package com.lwm.activityex02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMoveSub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveSub = findViewById(R.id.btn_move_sub);
        btnMoveSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("name", "코스");

                Bundle bundle = new Bundle();
                bundle.putSerializable("user", new User(1,"ssar","1234"));

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

    }
}