package fr.endide.mymoviedb.data.externalApi;

import android.media.Image;

import java.io.ByteArrayInputStream;

import fr.endide.mymoviedb.data.entity.moviedbEntity;
import fr.endide.mymoviedb.data.entity.moviedbSearchEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface moviedbService {
    @GET("3/search/movie")
    Call<moviedbSearchEntity> searchEntity(@Query("api_key") String key, @Query("query") String name, @Query("language") String lang);

    @GET("3/movie/{movie_id}")
    Call<moviedbEntity> getMovie(@Path("movie_id") int id, @Query("api_key") String key, @Query("language") String lang);
}
