package com.jaybon.musicplayerapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {

    // IPC 통신
    // AIDL 없이 바인더 사용(서비스의 레퍼런스를 넘기는 방식)
    // AIDL은 기본자료형만 넘길 수 있고 레퍼런스를 넘길수 없는데, 이 방식을 쓰면 레퍼런스 리턴가능
    // AIDL은 다른앱이랑 통신할 때 사용하고, 이 방식은 앱 내부에서 사용하기 위한 것
    // 바인더 타입으로 내부클래스 생성
    class LocalBinder extends Binder{
        MusicService getService(){
            return MusicService.this;
        }
    }

    private static final String TAG = "MusicService";

    // AIDL 없이 바인더 사용(서비스의 레퍼런스를 넘기는 방식)
    // IBinder는 Binder의 자식(?)
    private final IBinder binder = new LocalBinder();

    private MediaPlayer mp;

    // mp의 레퍼런스를 가져오기 위한 메서드
    public MediaPlayer getMp(){
        return mp;
    }

//        mp.start(); // 음악 시작
//        mp.getDuration(); // 음악의 총 길이
//        mp.getCurrentPosition(); // 현재 음악이 어디까지 흘렀는지 알 수있음
//        mp.seekTo(1); // 음악 재생 위치를 선택 할 수 있음
//        mp.getTimestamp()

    public MusicService() {

    }

    // startService, bindService 호출시 실행됨
    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this,R.raw.sample1);


    }

    // 메인에서 bindService호출하면 바인더를 리턴
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
