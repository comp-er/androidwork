package com.jaybon.getapitest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private Button btn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://kr.api.riotgames.com/lol/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RiotService service = retrofit.create(RiotService.class);

                Call<UserInfo> call = service.userInfo("포식 베이가");
                call.enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

                        if (!response.isSuccessful()) {
                            tv.setText("Code: " + response.code());
                            return;
                        }

                        UserInfo userInfo = response.body();
                        tv.setText(userInfo.getAccountId());
                        System.out.println("입력성공: " + userInfo.getAccountId());
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {

                        Log.d(TAG, "onFailure: " + t.getMessage());
                        tv.setText(t.getMessage());
                    }
                });
            }
        });
    }
}