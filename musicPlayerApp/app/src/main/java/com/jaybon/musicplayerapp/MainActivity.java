package com.jaybon.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    // xml 연결
    private ImageView btnPlayStop;
    private SeekBar seekBar;
    private TextView tvTime;

    // 서비스 레퍼런스
    private MusicService musicService;
    private MediaPlayer mp;

    // 토글 버튼 만드는 방법 1
//    private Boolean isPlay = true;  // isPlay = !isPlay
    // 토글 버튼 만드는 방법 2
//    private int isPlay = 1;  // isPlay = -1 * isPlay

    private int isPlaying = -1; // 1은 음악재생, -1은 음악멈춤

    private Handler handler = new Handler();

    Thread uiHandleThread;

    ServiceConnection serviceConnection = new ServiceConnection() {

        // 서비스에서 onBind가 호출되면 IBinder가 들어온다
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            // 내부클래스에 접근
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            musicService = binder.getService(); // 서비스 레퍼런스를 가져온다
            mp = musicService.getMp();
            seekBarInit();

        }

        // 서비스의 binder가 null이면 이 메서드를 탄다
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mp.stop(); // 미디어를 멈춤
            mp.release(); // 시스템 자원을 해제
        }
    };

    // seekBar를 초기화하는 메서드
    private void seekBarInit() {
        seekBar.setMax(mp.getDuration()); // 바의 최대길이를 만들어줌
        seekBar.setProgress(0); // 시크바의 초기 위치 0초
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml 연결
        initObject();
        initBinding();
        initListener();

    }

    private void initListener() {

        // seekBar 위치를 옮겼을 때 변경
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                //유저에게서 입력된 값이라면 변경
                if(fromUser){
                    mp.seekTo(progress); // progress = seekBar.getProgress()
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlaying = isPlaying * -1;
                if (isPlaying == 1) {
                    musicStart();
                } else {
                    musicPause();
                }

                // ui 변경(seekBar 그림 그리기)
                // 스레드를 컨트롤 하기위해 변수에 저장
                // 스레드는 스택이 종료되도 바로 날아가지 않는다(interrupt사용해야됨)
                uiHandleThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isPlaying == 1) {
                            // 이벤트 핸들러에게 데이터 던져주기
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d(TAG, "run: " + mp.getCurrentPosition());
                                    Log.d(TAG, "run: " + mp.getDuration());

                                    int tempTime = mp.getCurrentPosition() / 1000;

                                    int tempMinute = tempTime / 60;
                                    String tempMinuteString = (tempMinute+"").length()==1?"0"+tempMinute:""+tempMinute;

                                    int tempSecond = tempTime % 60;
                                    String tempSecondString = (tempSecond+"").length()==1?"0"+tempSecond:""+tempSecond;

                                    tvTime.setText(tempMinuteString + ":" + tempSecondString);
                                    seekBar.setProgress(mp.getCurrentPosition());
                                    //getCurrentPosition은 1/1000초로 리턴되기 때문에 >= 사용
                                    if (mp.getCurrentPosition() >= mp.getDuration()) {
                                        musicStop();
                                    }
                                }
                            });
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } // end of while

                        // 스레드를 강제종료하려면 0.001초라도 멈춰야 interrupt종료가능
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        uiHandleThread.interrupt(); // 스레드 강제종료
                    }
                });
                uiHandleThread.start();
            }
        });
    }

    // 음악 재생하고 버튼 모양변경
    private void musicStart() {
        mp.start();
        btnPlayStop.setImageResource(R.drawable.ic_pause);
    }

    // 음악 재생하고 버튼 모양변경
    private void musicPause() {
        mp.pause();
        btnPlayStop.setImageResource(R.drawable.ic_play);
    }

    // 음악이 끝까지 재생되면 실행
    private void musicStop() {
        mp.seekTo(0);
        seekBar.setProgress(0);
        tvTime.setText("00:00");
        btnPlayStop.setImageResource(R.drawable.ic_play);
        isPlaying = -1;
    }

    private void initObject() {
        btnPlayStop = findViewById(R.id.btn_play_stop);
        seekBar = findViewById(R.id.seekBar);
        tvTime = findViewById(R.id.tv_time);
    }

    private void initBinding() {
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }
}