package fr.endide.mymoviedb.data.interfaces;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import fr.endide.mymoviedb.data.entity.Content;

@Dao
public interface ContentDAO {
    @Query("SELECT * FROM content")
    List<Content> getAll();

    @Query("SELECT * FROM content WHERE name LIKE :name")
    Content findByName(String name);
}
