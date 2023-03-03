package fr.endide.mymoviedb.data.externalApi;

import fr.endide.mymoviedb.data.entity.moviedbEntity;
import fr.endide.mymoviedb.data.entity.moviedbSearchMovieEntity;
import fr.endide.mymoviedb.data.entity.moviedbSearchTvEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface moviedbService {
    @GET("3/search/movie")
    Call<moviedbSearchMovieEntity> searchMovie(@Query("api_key") String key, @Query("query") String name, @Query("language") String lang);

    @GET("3/search/tv")
    Call<moviedbSearchTvEntity> searchSeries(@Query("api_key") String key, @Query("query") String name, @Query("language") String lang);


    @GET("3/movie/{movie_id}")
    Call<moviedbEntity> getMovie(@Path("movie_id") int id, @Query("api_key") String key, @Query("language") String lang);
}
