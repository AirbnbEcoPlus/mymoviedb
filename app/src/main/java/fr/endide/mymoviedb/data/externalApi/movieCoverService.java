package fr.endide.mymoviedb.data.externalApi;


import fr.endide.mymoviedb.data.entity.moviedbEntity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface movieCoverService {

    @GET("t/p/w500/{cover_id}")
    Call<ResponseBody> getCover(@Path("cover_id") String cover_path);
}
