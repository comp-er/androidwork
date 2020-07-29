package com.jaybon.retrofit2ex01;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Yts {

    public String status;
    public String statusMessage;
    public Data data;
    public Meta meta;

    @lombok.Data
    public class Data {

        public long movieCount;
        public long limit;
        public long pageNumber;
        public List<Movie> movies = null;

        @lombok.Data
        public class Movie {

            public long id;
            public String url;
            public String imdbCode;
            public String title;
            public String titleEnglish;
            public String titleLong;
            public String slug;
            public long year;
            public double rating;
            public long runtime;
            public List<String> genres = null;
            public String summary;
            public String descriptionFull;
            public String synopsis;
            public String ytTrailerCode;
            public String language;
            public String mpaRating;
            public String backgroundImage;
            public String backgroundImageOriginal;
            public String smallCoverImage;
            public String mediumCoverImage;
            public String largeCoverImage;
            public String state;
            public List<Torrent> torrents = null;
            public String dateUploaded;
            public long dateUploadedUnix;

            @lombok.Data
            public class Torrent {

                public String url;
                public String hash;
                public String quality;
                public String type;
                public long seeds;
                public long peers;
                public String size;
                public long sizeBytes;
                public String dateUploaded;
                public long dateUploadedUnix;

            }

        }

    }

    @lombok.Data
    public class Meta {

        public long serverTime;
        public String serverTimezone;
        public long apiVersion;
        public String executionTime;

    }

}