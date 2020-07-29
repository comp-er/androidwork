package com.jaybon.retrofit2ex01;

        import java.util.List;
        import java.util.Map;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("list_movies.json&sort_by=rating")
    Call<Yts> getYts();

    @GET("userdata")
    Call<List<User>> getUsers();
}
