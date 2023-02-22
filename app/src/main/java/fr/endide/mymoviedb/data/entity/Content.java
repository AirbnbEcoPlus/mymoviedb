package fr.endide.mymoviedb.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Content {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "stars")
    public float stars;

    @ColumnInfo(name = "content_type")
    public ContentType contentType;

    @ColumnInfo(name = "season")
    public int season;

    @ColumnInfo(name = "ep")
    public int ep;

    @ColumnInfo(name = "link")
    public String link;
}
