package fr.endide.mymoviedb.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class moviedbSearchTvEntity {
    @SerializedName("page")
    public String page;
    @SerializedName("results")
    public List<result> results;
    @SerializedName("total_pages")
    public int total_pages;
    @SerializedName("total_results")
    public int total_results;

    public moviedbSearchTvEntity(String page, List<result> results, int total_pages, int total_results){
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
        @SerializedName("origin_country")
        public List<String> origin_country;
        @SerializedName("original_language")
        public String original_language;

        @SerializedName("original_name")
        public String original_name;
        @SerializedName("overview")
        public String overview;
        @SerializedName("popularity")
        public Float popularity;
        @SerializedName("poster_path")
        public String poster_path;
        @SerializedName("first_air_date")
        public String first_air_date;
        @SerializedName("name")
        public String name;
        @SerializedName("vote_average")
        public float vote_average;
        @SerializedName("vote_count")
        public int vote_count;
        public result(boolean adult, String backdrop_path, List<Integer> genre_id, int id, String original_language, String original_name, String overview, Float popularity, String poster_path, String first_air_date, String name, float vote_average, int vote_count){
            this.adult = adult;
            this.backdrop_path = backdrop_path;
            this.genre_id = genre_id;
            this.id = id;
            this.original_language = original_language;
            this.original_name = original_name;
            this.overview = overview;
            this.popularity = popularity;
            this.poster_path = poster_path;
            this.first_air_date = first_air_date;
            this.name = name;
            this.vote_average = vote_average;
            this.vote_count = vote_count;

        }

    }

}
