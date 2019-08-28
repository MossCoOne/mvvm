package com.mossco.za.mvvm.database;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "cat",indices = @Index(value = {"imageId"}, unique = true))
public class CatEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String imageUrl;
    private String title;
    private String description;
    private String imageId;

    public CatEntry(String imageUrl, String title, String description, String imageId) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
