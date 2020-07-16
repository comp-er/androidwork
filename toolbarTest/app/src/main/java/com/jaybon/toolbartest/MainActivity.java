package com.jaybon.toolbartest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main_Activity";

    private ImageView imageView;
    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter; // 내가만든 프래그먼트 어댑터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        drawerLayout = findViewById(R.id.drawer_layout);
        tabLayout = findViewById(R.id.tab_Layout);
        viewPager = findViewById(R.id.view_pager);

        // getSupportFragmentManager()는 그냥 넣자, 1은 페이지 이동방식
        adapter = new FragmentAdapter(getSupportFragmentManager(),1);

        // adapter에 데이터 담기
        adapter.addFragment(new Frag1());
        adapter.addFragment(new Frag2());

        // viewPager와 Fragment 연결(viewPager는 페이지슬라이딩)
        // 뷰페이저에 들어오는 데이터는 어댑터에 의해서 관리됨
        viewPager.setAdapter(adapter);

        // viewPager와 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("첫번째");
        tabLayout.getTabAt(1).setText("두번째");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }
}

