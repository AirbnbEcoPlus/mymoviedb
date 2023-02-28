package fr.endide.mymoviedb.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fr.endide.mymoviedb.data.entity.Content;
import fr.endide.mymoviedb.data.interfaces.ContentDAO;

@Database(entities = {Content.class}, version = 6)
public abstract class ContentDatabase extends RoomDatabase {
    public abstract ContentDAO contentDAO();

    private static ContentDatabase INSTANCE;

    public static ContentDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ContentDatabase.class, "DB_CONTENT").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
