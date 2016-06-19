package com.lbbento.androidarchitecture.data;

import android.support.annotation.Nullable;

import java.util.UUID;

/**
 * Created by lbbento on 20/06/2016.
 */

public final class Discussion {

    private final String id;
    private final String title;
    private final String description;
    private final String imageUrl;


    /**
     * Use this constructor to create a new active Task.
     *
     * @param title
     * @param description
     */
    public Discussion(String title, @Nullable String description, @Nullable String imageUrl) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
