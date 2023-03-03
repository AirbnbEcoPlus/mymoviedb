package fr.endide.mymoviedb.data.entity;

import com.google.gson.annotations.SerializedName;

public class moviedbEntity {
    @SerializedName("adult")
    public boolean adult;
    @SerializedName("backdrop_path")
    public String backdrop_path;
    @SerializedName("budget")
    public int budget;
    @SerializedName("original_language")
    public String original_language;
    @SerializedName("original_title")
    public String original_title;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public float popularity;
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("release_date")
    public String release_date;
    @SerializedName("revenue")
    public float revenue;
    @SerializedName("status")
    public String status;
    @SerializedName("title")
    public String title;
    @SerializedName("vote_average")
    public float vote_average;
    @SerializedName("vote_count")
    public int vote_count;

    public moviedbEntity(boolean adult, String backdrop_path, int budget, String original_language, String original_title, String overview, float popularity, String poster_path, String release_date, float revenue, String status, String title, float vote_average, int vote_count){
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.budget = budget;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.revenue = revenue;
        this.status = status;
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }
}
