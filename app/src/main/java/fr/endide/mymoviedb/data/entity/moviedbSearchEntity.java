package fr.endide.mymoviedb.data.entity;

import java.util.ArrayList;
import java.util.List;

public class moviedbSearchEntity {
    public String page;
    public List<result> results;
    public int total_pages;
    public int total_results;

    public moviedbSearchEntity(String page, List<result> results, int total_pages, int total_results){
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }


    public static class result{
        public boolean adult;
        public String backdrop_path;
        public List<Integer> genre_id;
        public int id;
        public String original_language;
        public String original_title;
        public String overview;
        public Float popularity;
        public String poster_path;
        public String release_date;
        public String title;
        public boolean video;
        public float vote_average;
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
