package fr.endide.mymoviedb.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import fr.endide.mymoviedb.data.entity.Content;
import fr.endide.mymoviedb.data.interfaces.ContentDAO;

@Database(entities = {Content.class}, version = 1)
public abstract class ContentDatabase extends RoomDatabase {
    public abstract ContentDAO contentDAO();
}
