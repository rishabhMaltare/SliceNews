package com.rishabh.slicenews.model;

import android.support.annotation.Nullable;

/**
 * Created by Rishabh on 04-08-2018.
 */
public class DataModel {

    private String title;

    private String description;

    private String imageUrl;

    public DataModel() {
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
