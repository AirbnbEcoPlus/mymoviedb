package fr.endide.mymoviedb.data.entity;

public class SearchEntity {
    public String name;
    public String description;
    public String link;
    public int extId;

    public String cover_path;

    public SearchEntity(String name, String description, String link, int extId,String cover_path){
        this.name = name;
        this.description = description;
        this.link = link;
        this.extId = extId;
        this.cover_path = cover_path;
    }
}
