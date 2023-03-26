package fr.endide.mymoviedb.data.externalApi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executors;

import fr.endide.mymoviedb.Main;
import fr.endide.mymoviedb.adapter.SearchListAdapter;
import fr.endide.mymoviedb.data.entity.Content;
import fr.endide.mymoviedb.data.entity.SearchEntity;
import fr.endide.mymoviedb.data.entity.moviedbSearchMovieEntity;

import fr.endide.mymoviedb.data.entity.moviedbSearchTvEntity;
import okhttp3.Dispatcher;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiClient {
    private final String BASE_URL = "https://api.themoviedb.org/";
    private final String API_KEY = "15d2ea6d0dc1d476efbca3eba2b9bbfb";

    private final String IMAGE_URL = "https://image.tmdb.org/";


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    moviedbService service = retrofit.create(moviedbService.class);

    private Call<moviedbSearchMovieEntity> currentMovieCall;

    private Call<moviedbSearchTvEntity> currentTvCall;

    public void searchMovie(String name, SearchListAdapter adapter) {

        if(currentMovieCall != null && currentMovieCall.isExecuted()){
            currentMovieCall.cancel();
        }
        Call<moviedbSearchMovieEntity> searchEntity = service.searchMovie(API_KEY, name, "fr");
        currentMovieCall = searchEntity;
        searchEntity.enqueue(new retrofit2.Callback<moviedbSearchMovieEntity>() {
            @Override
            public void onResponse(Call<moviedbSearchMovieEntity> call, retrofit2.Response<moviedbSearchMovieEntity> response) {
                if (response.isSuccessful()) {
                    moviedbSearchMovieEntity entity = response.body();
                    for (int i = 0; i < entity.results.size(); i++) {
                        if(!containsName(Main.externalContent, entity.results.get(i).id)){
                            Main.externalContent.add(new SearchEntity(entity.results.get(i).title, entity.results.get(i).overview, null, entity.results.get(i).id, entity.results.get(i).poster_path));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<moviedbSearchMovieEntity> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });


    }
    public void searchTv(String name, SearchListAdapter adapter) {

        if(currentTvCall != null && currentTvCall.isExecuted()){
            currentTvCall.cancel();
        }
        Call<moviedbSearchTvEntity> searchEntity = service.searchSeries(API_KEY, name, "fr");
        currentTvCall = searchEntity;
        searchEntity.enqueue(new retrofit2.Callback<moviedbSearchTvEntity>() {
            @Override
            public void onResponse(Call<moviedbSearchTvEntity> call, retrofit2.Response<moviedbSearchTvEntity> response) {
                if (response.isSuccessful()) {
                    moviedbSearchTvEntity entity = response.body();
                    for (int i = 0; i < entity.results.size(); i++) {
                        if(!containsName(Main.externalContent, entity.results.get(i).id)){
                            Main.externalContent.add(new SearchEntity(entity.results.get(i).name, entity.results.get(i).overview, null, entity.results.get(i).id, entity.results.get(i).poster_path));
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<moviedbSearchTvEntity> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });


    }
    public boolean containsName(final List<SearchEntity> list, final Integer extId){
        return list.stream().anyMatch(o -> extId.equals(o.extId));
    }
    public void getPoster(Content content) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IMAGE_URL)
                .client(new okhttp3.OkHttpClient())
                .build();

        movieCoverService service = retrofit.create(movieCoverService.class);

        Call<ResponseBody> getCover = service.getCover(content.coverPath);

        getCover.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    ResponseBody body = response.body();
                    InputStream is = body.byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    File cover = new File(Environment.getExternalStorageDirectory() + "/MyMovieDB/Covers",content.extId + ".jpg");
                    try {
                        FileOutputStream fos = new FileOutputStream(cover);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        fos.flush();
                        fos.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }
}
