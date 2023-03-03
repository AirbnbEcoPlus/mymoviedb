package fr.endide.mymoviedb.data.entity;

import android.media.Image;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Content {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "stars")
    public int stars;

    @ColumnInfo(name = "review")
    public String review;

    @ColumnInfo(name = "content_type")
    public ContentType contentType;

    @ColumnInfo(name = "season")
    public int season;

    @ColumnInfo(name = "ep")
    public int ep;

    @ColumnInfo(name = "finish")
    public boolean finish;

    @ColumnInfo(name = "link")
    public String link;

    @ColumnInfo(name = "extId")
    public int extId;

    @ColumnInfo(name = "cover_path")
    public String coverPath;

}
