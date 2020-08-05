package com.jaybon.mvvmex01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getApplicationContext() 앱의 컨텍스트
        AppDatabase db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "db-cos")
                .fallbackToDestructiveMigration() // 2번째 배포부터 추가
                .allowMainThreadQueries() // 메인쓰레드에서 접근
                .build();

        userRepository = db.userRepository();

        User user = new User("JOOHO", "CHOI");

        userRepository.insert(user);

        Log.d(TAG, "onCreate: 데이터가 잘 저장되었습니다.");

        List<User> users = userRepository.findAll();

        for (User pUser:
             users) {
            Log.d(TAG, "onCreate: "+pUser);
        }

        User user1 = userRepository.findByUid(1);

        Log.d(TAG, "onCreate: "+user1);

    }
}