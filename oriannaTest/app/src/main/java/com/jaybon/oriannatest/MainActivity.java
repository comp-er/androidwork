package com.jaybon.oriannatest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
import com.merakianalytics.orianna.types.core.league.Leagues;
import com.merakianalytics.orianna.types.core.match.MatchHistory;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {

                Orianna.setRiotAPIKey("RGAPI-8f2ab161-b201-4d25-a846-17abf656e8e7");
                Orianna.setDefaultRegion(Region.KOREA);

                Summoner summoner = Orianna.summonerNamed("hide on bush").get();
                Log.d(TAG, "run: "+summoner.getId());



            }
        }).start();


    }
}