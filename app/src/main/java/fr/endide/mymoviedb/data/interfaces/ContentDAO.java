package fr.endide.mymoviedb.data.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.endide.mymoviedb.data.entity.Content;

@Dao
public interface ContentDAO {
    @Query("SELECT * FROM content")
    List<Content> getAll();

    @Query("SELECT * FROM content WHERE name LIKE :name")
    Content findByName(String name);

    @Query("SELECT * FROM content WHERE uid LIKE :uid")
    Content findById(int uid);

    @Update
    void updateContent(Content... content);

    @Insert
    void insertContent(Content... content);

    @Delete
    void delete(Content content);

}
