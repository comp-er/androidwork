package com.jaybon.bottomnavex01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";


    // 자기가 직접 만든건 앞에 m이나 my를 붙이면 구분이 쉽다
    private BottomNavigationView myBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBottomNavigationView = findViewById(R.id.bottom_nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag1()).commit();

        myBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Log.d(TAG, "onNavigationItemSelected: "+item.getItemId());
                Log.d(TAG, "onNavigationItemSelected: R값: "+R.id.nav_search);
                
                switch (item.getItemId()){
                    case R.id.nav_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag1()).commit();
                        break;
                    case R.id.nav_setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag2()).commit();
                        break;
                    case R.id.nav_nav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Frag3()).commit();
                        break;
                }
                
                return true; // true를 써야 그림이 다시그려진다
            }
        });

    }
}