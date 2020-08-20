package com.jaybon.oriannatest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jaybon.oriannatest.model.ApiEntry;
import com.jaybon.oriannatest.model.ApiSummoner;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.LeaguePositions;
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

                Orianna.setRiotAPIKey("RGAPI-d7a9376a-c71a-4e32-bed4-394ba07acf28");
                Orianna.setDefaultRegion(Region.KOREA);

                // 소환사정보
                Summoner oriSummoner = Orianna.summonerNamed(name).get();

                ApiSummoner apiSummoner = gson.fromJson(oriSummoner.toJSON(), ApiSummoner.class);

                SummonerModel summonerModel = SummonerModel.builder()
                        .name(apiSummoner.getName())
                        .summonerLevel((int)apiSummoner.getSummonerLevel())
                        .profileIconId((int)apiSummoner.getProfileIconId())
                        .build();

                LeaguePositions leaguePositions = oriSummoner.getLeaguePositions();

                List<ApiEntry> apiEntries = gson.fromJson(leaguePositions.toJSON(), List.class);

//                Log.d(TAG, "run: "+summoner);

//                Gson gson = new Gson();
//
//                com.jaybon.oriannatest.model.Summoner summoner1 = gson.fromJson(summoner.toJSON(), com.jaybon.oriannatest.model.Summoner.class);
//                Log.d(TAG, "run: "+summoner1.getAccountId());


//                Log.d(TAG, "run: "+summoner.getId());
//
//                Log.d(TAG, "run: "+summoner.getLeaguePositions().get(0));

//                Log.d(TAG, "run: "+MatchHistory.forSummoner(summoner).get().get(0).getDuration().getStandardSeconds());

//                MatchHistory matchHistory = MatchHistory.forSummoner(summoner).get();

//                Log.d(TAG, "run: "+matchHistory.get(0).getParticipants().get(0).getSummoner().getName());
//                for (Participant participant :matchHistory.get(0).getParticipants()){
//                    if(participant.getSummoner().getName().equals("Hide on bush")){
//
//                    }
//                }


            }
        }).start();


    }
}