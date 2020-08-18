package com.jaybon.retrofitcallbacktest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RiotService {

    @GET("summoner/v4/summoners/by-name/{summoner_name}?api_key=RGAPI-8f2ab161-b201-4d25-a846-17abf656e8e7")
    Call<Summoner> getSummonerByName(@Path(value = "summoner_name", encoded = true) String summonerName);

    @GET("league/v4/entries/by-summoner/{summoner_id}?api_key=RGAPI-8f2ab161-b201-4d25-a846-17abf656e8e7")
    Call<Entry> getEntryBySummonerId(@Path(value = "summoner_id", encoded = true) String summonerId);

    @GET("match/v4/matchlists/by-account/{account_id}?api_key=RGAPI-8f2ab161-b201-4d25-a846-17abf656e8e7")
    Call<Matchlist> getMatchListByAccountId(@Path(value = "account_id", encoded = true) String accountId);

    @GET("match/v4/matches/{match_id}?api_key=RGAPI-8f2ab161-b201-4d25-a846-17abf656e8e7")
    Call<MatchSpec> getMatchSpecByMatchId(@Path(value = "match_id", encoded = true) String matchId);


}


