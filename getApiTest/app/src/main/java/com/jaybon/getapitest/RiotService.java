package com.jaybon.getapitest;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RiotService {

    @GET("summoner/v4/summoners/by-name/hide%20on%20bush?api_key=RGAPI-6260a2c1-eb61-44f9-9de0-35d07b76c0dd")
    Call<UserInfo> userInfo();
}

