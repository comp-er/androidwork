package com.jaybon.servicecounterapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class CounterService extends Service {

    private static final String TAG = "CounterService";
    private int count = 0;
    private boolean isStop = false;

    InterfaceCounterService.Stub binder = new InterfaceCounterService.Stub() {
        @Override
        public int getCount() throws RemoteException {
            return count;
        }
    };

    public CounterService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: 서비스 시작");

        Thread counterThread = new Thread(new Counter());
        counterThread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isStop = true;
    }

    // 액티비티와 생명주기를 같이한다
    @Override
    public IBinder onBind(Intent intent) {
        if(intent.getStringExtra("count") != null){
            count = Integer.parseInt(intent.getStringExtra("count"));
        }
        Log.d(TAG, "onBind: " + count);
        return binder;
    }


    // 멈출때
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: " + count);
        isStop = true;
        return super.onUnbind(intent);
    }

    class Counter implements Runnable {

        @Override
        public void run() {
            while (!isStop){

                count++;
                Log.d(TAG, "run: count : " + count);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
