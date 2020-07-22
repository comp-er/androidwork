package com.jaybon.navigationintentex01;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

public class NavigationViewHelper {
    public static void enableNavigation(final Context context, NavigationView view){
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.nav_menu1){
                    Intent intent = new Intent(context, MainActivity.class);

                    // 싱글탑 플래그 (무조건 1개) 기존것 사용용
                   intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    context.startActivity(intent);
                } else if (item.getItemId() == R.id.nav_menu2){
                    Intent intent = new Intent(context, SubActivity.class);

                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    context.startActivity(intent);
                }

                return true;
            }
        });
    }
}
