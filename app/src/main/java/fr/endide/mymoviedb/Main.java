package fr.endide.mymoviedb;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import fr.endide.mymoviedb.data.database.ContentDatabase;
import fr.endide.mymoviedb.data.entity.Content;
import fr.endide.mymoviedb.databinding.MainActivityBinding;


public class Main extends AppCompatActivity {
    static ContentDatabase db;

    MainActivityBinding binding;

    public static ArrayList<String> externalContent;
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
        initExternalContent();
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
    private void initExternalContent(){
        externalContent = new ArrayList<String>();
        externalContent.add("Test");
        externalContent.add("Transformers 4");
    }

    public static List<Content> getPersonalContent(){
        return db.contentDAO().getAll();
    }

    public static Content getContent(String name){
        return db.contentDAO().findByName(name);
    }
    public static void insertContent(Content content){
        db.contentDAO().insertUser(content);
    }
    public static void deleteContent(Content content){
        db.contentDAO().delete(content);
    }
}
