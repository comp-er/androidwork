package com.jaybon.cosapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignInButton btnGoogleLogin = findViewById(R.id.btn_google_login);
        Button btnGoogleLogout = findViewById(R.id.btn_google_logout);


        btnGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Choose authentication providers
                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.GoogleBuilder().build());

                // 새로운 화면을 띄우는 것 (통신 아님)
                // 띄운후 로그인 하면 통신 (알아서 처리됨)
                startActivityForResult( // 화면이 꺼지면 리턴값을 받는것
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        3648);
            }
        });

        btnGoogleLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Log.d(TAG, "onClick: 로그아웃" + FirebaseAuth.getInstance().getCurrentUser());
            }
        });
    }

    // 구글 로그인 화면 꺼지면 실행
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3648) {

            // 응답받은 데이터를 받는다
            // 동시에 오브젝트객체를 메모리에 띄워준다
            // firebaseAuth 힙영역으로 만들어서 사용자의 모든 정보를 담아준다
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {

                // FirebaseAuth 힙영역으로 만들어서 사용자의 모든 정보를 담아준다
                // 세션
                // 스태틱으로 만들어져있음- 모든 액티비티에서 접근가능 (앱은 나혼자 쓰니까)
                // 쉐어드프리퍼런스 안만들려면 이런식으로 만들어야함
                // Successfully signed in 로그인성공
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                // 로그인되었으니 인텐트해서 다른 액티비티로 이동하면 된다.
                Log.d(TAG, "onActivityResult: 로그인완료 : " + user.getEmail());
                Log.d(TAG, "onActivityResult: 로그인완료 : " + FirebaseAuth.getInstance().getCurrentUser());
                Log.d(TAG, "onActivityResult: 로그인완료 : " + response);


            } else {

                Log.d(TAG, "onActivityResult: 실패 : " + response.getError());
            }
        }
    }
}

