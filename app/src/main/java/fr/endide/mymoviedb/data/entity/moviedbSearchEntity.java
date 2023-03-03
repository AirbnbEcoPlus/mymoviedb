package fr.endide.mymoviedb.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class moviedbSearchEntity {
    @SerializedName("page")
    public String page;
    @SerializedName("results")
    public List<result> results;
    @SerializedName("total_pages")
    public int total_pages;
    @SerializedName("total_results")
    public int total_results;

    public moviedbSearchEntity(String page, List<result> results, int total_pages, int total_results){
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public static class result{
        @SerializedName("adult")
        public boolean adult;
        @SerializedName("backdrop_path")
        public String backdrop_path;
        @SerializedName("genre_id")
        public List<Integer> genre_id;
        @SerializedName("id")
        public int id;
        @SerializedName("original_language")
        public String original_language;

        @SerializedName("original_title")
        public String original_title;
        @SerializedName("overview")
        public String overview;
        @SerializedName("popularity")
        public Float popularity;
        @SerializedName("poster_path")
        public String poster_path;
        @SerializedName("release_date")
        public String release_date;
        @SerializedName("title")
        public String title;
        @SerializedName("video")
        public boolean video;

        @SerializedName("vote_average")
        public float vote_average;
        @SerializedName("vote_count")
        public int vote_count;
        public result(boolean adult, String backdrop_path, List<Integer> genre_id, int id, String original_language, String original_title, String overview, Float popularity, String poster_path, String release_date, String title, boolean video, float vote_average, int vote_count){
            this.adult = adult;
            this.backdrop_path = backdrop_path;
            this.genre_id = genre_id;
            this.id = id;
            this.original_language = original_language;
            this.original_title = original_title;
            this.overview = overview;
            this.popularity = popularity;
            this.poster_path = poster_path;
            this.release_date = release_date;
            this.title = title;
            this.video = video;
            this.vote_average = vote_average;
            this.vote_count = vote_count;

        }

    }

}
