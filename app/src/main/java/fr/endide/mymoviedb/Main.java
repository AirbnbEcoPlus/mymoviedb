package fr.endide.mymoviedb;

import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.endide.mymoviedb.data.database.ContentDatabase;
import fr.endide.mymoviedb.data.entity.Content;
import fr.endide.mymoviedb.data.entity.SearchEntity;
import fr.endide.mymoviedb.data.entity.moviedbEntity;
import fr.endide.mymoviedb.data.externalApi.apiClient;
import fr.endide.mymoviedb.databinding.MainActivityBinding;


public class Main extends AppCompatActivity {
    static ContentDatabase db;
    public static int viewPosition;
    MainActivityBinding binding;
    public static List<SearchEntity> externalContent = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new DashboardFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.dashboard_menu:
                    replaceFragment(new DashboardFragment());
                    break;

                case R.id.list_menu:
                    replaceFragment(new ListFragment());
                    break;


                case R.id.watching_menu:
                    replaceFragment(new WatchingFragment());
                    break;

                case R.id.add_menu:
                    replaceFragment(new AddFragment());
                    break;
            }
            return true;
        });
        initDatabase();
        initCoverFolder();
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
    private void initDatabase(){
        db = ContentDatabase.getInstance(this);
    }
    public void initCoverFolder(){
        String folderPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyMovieDB/Covers";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
    public static List<Content> getPersonalContent(){
        return db.contentDAO().getAll();
    }
    public static Content getContent(String name){
        return db.contentDAO().findByName(name);
    }
    public static Content getContentById(int id){
        return db.contentDAO().findById(id);
    }
    public static void insertContent(Content content){
        db.contentDAO().insertContent(content);
    }
    public static void deleteContent(Content content){
        db.contentDAO().delete(content);
    }
    public static void updateContent(Content content){
        db.contentDAO().updateContent(content);
    }
}
