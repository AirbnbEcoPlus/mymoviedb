package fr.endide.mymoviedb.data.externalApi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import fr.endide.mymoviedb.Main;
import fr.endide.mymoviedb.adapter.SearchListAdapter;
import fr.endide.mymoviedb.data.entity.Content;
import fr.endide.mymoviedb.data.entity.SearchEntity;
import fr.endide.mymoviedb.data.entity.moviedbEntity;
import fr.endide.mymoviedb.data.entity.moviedbSearchEntity;

import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    public void search(String name, SearchListAdapter adapter) {

        Call<moviedbSearchEntity> searchEntity = service.searchEntity(API_KEY, name, "fr");

        searchEntity.enqueue(new retrofit2.Callback<moviedbSearchEntity>() {
            @Override
            public void onResponse(Call<moviedbSearchEntity> call, retrofit2.Response<moviedbSearchEntity> response) {
                if (response.isSuccessful()) {
                    moviedbSearchEntity entity = response.body();
                    for (int i = 0; i < entity.results.size(); i++) {
                        if(!containsName(Main.externalContent, entity.results.get(i).id)){
                            Main.externalContent.add(new SearchEntity(entity.results.get(i).title, entity.results.get(i).overview, null, entity.results.get(i).id, entity.results.get(i).poster_path));
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<moviedbSearchEntity> call, Throwable t) {
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
