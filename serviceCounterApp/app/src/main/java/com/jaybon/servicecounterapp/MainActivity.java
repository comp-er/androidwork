package com.jaybon.servicecounterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private TextView tvCount;
    private Button btnStart;
    private Button btnStop;
    private boolean running = true;
    private Handler handler = new Handler();
    private boolean isServicerunning = false;

    private InterfaceCounterService binder;

    ServiceConnection connection = new ServiceConnection() {

        // IBinder는 bindService가 실행되는 순간 서비스가 들고있는 바인더가 될 것
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = InterfaceCounterService.Stub.asInterface(service);

//            try {
//                Thread.sleep(1000);
//                Log.d(TAG, "onServiceConnected: " + binder.getCount());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObject();
        initListener();

    }

    private void initObject() {
        tvCount = findViewById(R.id.tv_count);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
    }

    private void initListener() {

//        - BIND_AUTO_CREATE : Component와 연결되어있는 동안 비정상적으로 종료시 자동으로 다시시작
//        - BIND_DEBUG_UNBIND : 비정상적으로 연결이 끊어지면 로그를 남긴다(디버깅용)
//        - BIND_NOT_FOREGROUND : 백그라운드로만 동작한다. 만약 액티비티에서 생성한 경우 생명주기를 같이 한다
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
                Intent intent = new Intent(MainActivity.this, CounterService.class);

                // 카운트 값을 넘겨줘야한다
                if(binder != null){
                    try {
                        intent.putExtra("count", Integer.toString(binder.getCount()));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
//                startService(intent);

                // serviceConnection자리에는 서비스와 연결했다는 정보를 넘겨야한다

                if(!isServicerunning){
                    bindService(intent, connection, BIND_AUTO_CREATE);
                    isServicerunning = !isServicerunning;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (running){

                            if(binder != null){
                                // 새로운 쓰레드가 ui에 접근하려면 핸들러가 필요하다
                                // 핸들러 쓰는법 3가지 핸들러 / on ~~ / asynctask
                                //
                                handler.post(new Runnable() { // ui쓰레드 대기열에 요청을 보냄
                                    @Override
                                    public void run() {
                                        try {
                                            tvCount.setText(Integer.toString(binder.getCount()));
                                            Log.d(TAG, "run: 그림그려짐 "+binder.getCount());
                                        } catch (RemoteException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CounterService.class);
//                stopService(intent);
                if(isServicerunning){
                    unbindService(connection); // 인텐트 없이 커넥션을 넘긴다
                    isServicerunning = !isServicerunning;
                }
                running = false;
            }
        });
    }


}