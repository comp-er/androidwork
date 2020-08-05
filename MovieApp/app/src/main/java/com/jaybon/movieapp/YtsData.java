package com.jaybon.movieapp;

import java.util.List;

import lombok.Data;

@Data
public class YtsData {
    private String status;
    private String status_message;
    private MyData data;

    @Data
    public class MyData { // 외부에서 접근할 필요가 있는 것은 public

        private int movie_count;
        private int limit;
        private int page_number;
        private List<Movie> movies;

        @Data
        class Movie{
            private String title;
            private float rating;
            private String medium_cover_image;
        }
    }
}