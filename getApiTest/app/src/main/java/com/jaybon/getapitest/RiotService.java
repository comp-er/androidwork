package com.jaybon.getapitest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RiotService {

    @GET("summoner/v4/summoners/by-name/hide%20on%20bush?api_key=RGAPI-8f2ab161-b201-4d25-a846-17abf656e8e7")
    Call<UserInfo> userInfo();

    // 함수의 매개변수로 주소값을 설정 할 수있다
    @GET("summoner/v4/summoners/by-name/{summoner_name}?api_key=RGAPI-8f2ab161-b201-4d25-a846-17abf656e8e7")
    Call<UserInfo> userInfo(@Path(value = "summoner_name", encoded = true) String summonerName);


}

