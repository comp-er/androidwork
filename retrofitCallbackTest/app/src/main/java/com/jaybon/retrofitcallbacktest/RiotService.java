package com.jaybon.retrofitcallbacktest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RiotService {

    @GET("summoner/v4/summoners/by-name/{summoner_name}?api_key=RGAPI-d7a9376a-c71a-4e32-bed4-394ba07acf28")
    Call<Summoner> getSummonerByName(@Path(value = "summoner_name", encoded = true) String summonerName);

    @GET("league/v4/entries/by-summoner/{summoner_id}?api_key=RGAPI-d7a9376a-c71a-4e32-bed4-394ba07acf28")
    Call<Entry> getEntryBySummonerId(@Path(value = "summoner_id", encoded = true) String summonerId);

    @GET("match/v4/matchlists/by-account/{account_id}?api_key=RGAPI-d7a9376a-c71a-4e32-bed4-394ba07acf28")
    Call<Matchlist> getMatchListByAccountId(@Path(value = "account_id", encoded = true) String accountId);

    @GET("match/v4/matches/{match_id}?api_key=RGAPI-d7a9376a-c71a-4e32-bed4-394ba07acf28")
    Call<MatchSpec> getMatchSpecByMatchId(@Path(value = "match_id", encoded = true) String matchId);


}


